package ws.exui.binding;

import java.util.ArrayList;
import java.util.List;

import ws.exui.event.DataEventReport;
import ws.exui.event.ElementEventReport;
import ws.exui.event.I_DataEventReport;
import ws.exui.event.I_ElementEventReport;

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
	
	private void __invokeAll_Remove(I_ElementEventReport report) {
		report.addInvokePath(this);
		for(I_ListChangeListener l:l_list) {
			l.targetRemove(report);
		}
	}
	private void __invokeAll_Insert(I_ElementEventReport report) {
		report.addInvokePath(this);
		for(I_ListChangeListener l:l_list) {
			l.targetInsert(report);
		}
	}

	@Override
	public void insertChildAtIndex(E x, int index) {
		this.trueList.add(index, x);
		
		I_ElementEventReport<Integer, E> report = new ElementEventReport<>(this, 
				this.hashCode()+ "insert:position="+ index+ ",obj="+ x.hashCode());
		report.setKeyChild(index);
		report.setTargetChild(x);
		this.__invokeAll_Insert(report);
	}

	@Override
	public void removeChild(E x) {
		if(!this.trueList.contains(x))
			return;
		this.trueList.remove(x);

		I_ElementEventReport<Integer,E> report = new ElementEventReport<>(this, 
				this.hashCode()+ "remove:obj="+ x.hashCode());
		report.setTargetChild(x);
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
	public void autoPush() {
		ArrayList<E> bakList = new ArrayList<>();
		bakList.addAll(trueList);
		trueList.clear();
		for(E a:bakList) {
			this.insertChildAtIndex(a, getChildCount());
		}
	}

	@Override
	public void targetRemove(I_ElementEventReport<?, ?> report) {
		if(report.isPathContains(this))
			return;
	
		this.__invokeAll_Remove(report);
		
		E tobj = (E) report.getTargetChild();
		if(this.trueList.contains(tobj))
			this.trueList.remove(tobj);
	}

	@Override
	public void targetInsert(I_ElementEventReport<?, ?> report) {
		if(report.isPathContains(this))
			return;
	
		this.__invokeAll_Insert(report);
		
		E tobj = (E) report.getTargetChild();
		int index = (int) report.getKeyChild();
		this.trueList.add(index, tobj);
	}

	@Override
	public void clearAll() {
		this.trueList.clear();
	}
	
	@Override
	public String toString() {
		String val = this.getClass().getName() + "[num=" + this.getChildCount() + "::" ;
		for(int i=0;i<this.getChildCount();++i) {
			E vi = this.getChildAtIndex(i);
			val += "v_" + i + "=" + vi.toString() + "," ;
		}
		return val +"]";
	}

	@Override
	public boolean contains(E o) {
		return this.trueList.contains(o);
	}
	

}
