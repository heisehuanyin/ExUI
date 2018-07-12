package ws.exui.binding;

import java.util.ArrayList;
import java.util.List;

import ws.exui.event.DataEventReport;
import ws.exui.event.I_DataEventReport;

public class ExListWap<E> implements I_ListCommon<E>{
	private ArrayList<I_ListChangeListener> l_list = new ArrayList<>();
	private ArrayList<E> trueList = new ArrayList<E>();

	@Override
	public void addChangeListener(I_ListChangeListener l) {
		if(l != null)
			this.l_list.add(l);
	}

	@Override
	public void removeChangeListener(I_ListChangeListener l) {
		if(this.l_list.contains(l))
			this.l_list.remove(l);
	}
	
	private void __invokeAll_Remove(I_DataEventReport report) {
		report.addInvokePath(this);
		for(I_ListChangeListener l:l_list) {
			l.targetRemove(report);
		}
	}
	private void __invokeAll_Insert(I_DataEventReport report) {
		report.addInvokePath(this);
		for(I_ListChangeListener l:l_list) {
			l.targetInsert(report);
		}
	}

	@Override
	public void insertChildAtIndex(E x, int index) {
		this.trueList.add(index, x);
		
		I_DataEventReport report = new DataEventReport(this, 
				this.hashCode()+ "insert:position="+ index+ ",obj="+ x.hashCode());
		this.__invokeAll_Insert(report);
	}

	@Override
	public void removeChild(E x) {
		if(!this.trueList.contains(x))
			return;
		this.trueList.remove(x);

		I_DataEventReport report = new DataEventReport(this, 
				this.hashCode()+ "remove:obj="+ x.hashCode());
		this.__invokeAll_Remove(report);
	}

	@Override
	public int getChildCount() {
		return this.trueList.size();
	}

	@Override
	public E getChildAtIndex(int index) {
		return this.trueList.get(index);
	}

	@Override
	public void targetRemove(I_DataEventReport report) {
		if(report.isPathContains(this))
			return;
	
		this.__invokeAll_Remove(report);
	}

	@Override
	public void targetInsert(I_DataEventReport report) {
		if(report.isPathContains(this))
			return;
	
		this.__invokeAll_Insert(report);
	}
	

}
