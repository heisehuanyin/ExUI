package exui.binding;

import java.util.ArrayList;
import java.util.List;

import exui.event.DataEventReport;
import exui.event.ElementEventReport;
import exui.event.I_DataEventReport;
import exui.event.I_ElementEventReport;

/**
 * 被类型专门为view添加了一个控制位，可以控制这个list是否响应绑定数据变动，</br>
 * 即使不响应数据变动，绑定数据源的变动也可以随着绑定链条传递下去。*/
public abstract class List4View<E> implements I_ListCommon<E> {
	private ArrayList<I_ListChangeListener> l_list = new ArrayList<>();
	private ArrayList<E> trueList = new ArrayList<E>();
	private boolean syncSwitch = true;

	@Override
	public void addChangeListener(I_ListChangeListener l) {
		if (l != null)
			this.l_list.add(l);
	}

	@Override
	public void removeChangeListener(I_ListChangeListener l) {
		if (this.l_list.contains(l))
			this.l_list.remove(l);
	}

	private void __invokeAll_Remove(I_ElementEventReport<Integer, ?> report) {
		report.addInvokePath(this);
		for (I_ListChangeListener l : l_list) {
			l.targetRemove(report);
		}
	}

	private void __invokeAll_Insert(I_ElementEventReport<Integer, ?> report) {
		report.addInvokePath(this);
		for (I_ListChangeListener l : l_list) {
			l.targetInsert(report);
		}
	}

	@Override
	public void insertChildAtIndex(E x, int index) {
		this.trueList.add(index, x);

		I_ElementEventReport<Integer, E> report = new ElementEventReport<>(this,
				this.hashCode() + "insert:position=" + index + ",obj=" + x.hashCode());
		report.setTargetKey(index);
		report.setTargetValue(x);
		this.__invokeAll_Insert(report);
	}

	@Override
	public void removeChild(E x) {
		if (!this.trueList.contains(x))
			return;
		int index = this.trueList.indexOf(x);
		this.trueList.remove(x);

		I_ElementEventReport<Integer, E> report = new ElementEventReport<>(this,
				this.hashCode() + "remove:position=" + index + ",obj=" + x.hashCode());
		report.setTargetValue(x);
		report.setTargetKey(index);
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
		for (E a : bakList) {
			this.insertChildAtIndex(a, getChildCount());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void targetRemove(I_ElementEventReport<?, ?> report) {
		if (report.isPathContains(this))
			return;

		if(this.syncSwitch) {
			int index = (int) report.getTargetKey();

			this.trueList.remove(index);
		}
		this.__invokeAll_Remove((I_ElementEventReport<Integer, ?>) report);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void targetInsert(I_ElementEventReport<?, ?> report) {
		if (report.isPathContains(this))
			return;

		if(this.syncSwitch) {
			E tobj = this.dataProcAtInsert(report);
			int index = (int) report.getTargetKey();

			this.trueList.add(index, tobj);
		}
		this.__invokeAll_Insert((I_ElementEventReport<Integer, ?>) report);
	}

	@Override
	public abstract E dataProcAtInsert(I_ElementEventReport<?, ?> report);

	@Override
	public void clearAll() {
		if(this.syncSwitch)
			this.trueList.clear();
	}

	@Override
	public String toString() {
		String val = this.getClass().getName() + "[num=" + this.getChildCount() + "::";
		for (int i = 0; i < this.getChildCount(); ++i) {
			E vi = this.getChildAtIndex(i);
			val += "v_" + i + "=" + vi.toString() + ",";
		}
		return val + "]";
	}

	@Override
	public boolean contains(E o) {
		return this.trueList.contains(o);
	}

	public void passiveSwitch(boolean flag) {
		this.syncSwitch = flag;
	}
	
	public boolean getPassiveStatue() {
		return this.syncSwitch;
	}
}
