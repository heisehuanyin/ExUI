package exui.binding;

import java.util.ArrayList;
import java.util.List;

import exui.event.DataEventReport;
import exui.event.ElementEventReport;
import exui.event.I_DataEventReport;
import exui.event.I_ElementEventReport;

public abstract class ExList<E> implements I_ListCommon<E>{
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
	
	private void __invokeAll_Remove(I_ElementEventReport<Integer, ?> report) {
		report.addInvokePath(this);
		for(I_ListChangeListener l:l_list) {
			l.targetRemove(report);
		}
	}
	private void __invokeAll_Insert(I_ElementEventReport<Integer, ?> report) {
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
		int index = this.trueList.indexOf(x);
		this.trueList.remove(x);

		I_ElementEventReport<Integer,E> report = new ElementEventReport<>(this, 
				this.hashCode()+ "remove:position="+ index+ ",obj="+ x.hashCode());
		report.setTargetChild(x);
		report.setKeyChild(index);
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
		if(this.trueList.size() == 0)
			return;
		ArrayList<E> bakList = new ArrayList<>();
		bakList.addAll(trueList);
		trueList.clear();
		for(E a:bakList) {
			this.insertChildAtIndex(a, getChildCount());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void targetRemove(I_ElementEventReport<?, ?> report) {
		if(report.isPathContains(this))
			return;
	
		int index = (int) report.getKeyChild();
		
		this.trueList.remove(index);
		this.__invokeAll_Remove((I_ElementEventReport<Integer, ?>) report);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void targetInsert(I_ElementEventReport<?, ?> report) {
		if(report.isPathContains(this))
			return;
		
		E tobj = this.dataProcAtInsert(report);
		int index = (int) report.getKeyChild();
		
		this.trueList.add(index, tobj);
		this.__invokeAll_Insert((I_ElementEventReport<Integer, ?>) report);
	}
	@Override
	public abstract E dataProcAtInsert(I_ElementEventReport<?, ?> report);

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
