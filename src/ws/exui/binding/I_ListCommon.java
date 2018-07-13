package ws.exui.binding;

import ws.exui.event.I_ElementEventReport;

public interface I_ListCommon<E> extends I_ListChangeListener{
	/**
	 * 添加Listener
	 * @param l Listener*/
	void addChangeListener(I_ListChangeListener l);

	/**
	 * 移除Listener，如果存在的话
	 * @param l Listener*/
	void removeChangeListener(I_ListChangeListener l);

	void insertChildAtIndex(E x, int index);

	void removeChild(E x);

	int getChildCount();

	E getChildAtIndex(int index);

	void autoPush();
	
	void clearAll();

	boolean contains(E o);

	/**
	 * 数据转换和处理环节，两个绑定的List中数据类型不一定相同，因此需要一定转换*/
	E dataProcAtInsert(I_ElementEventReport<?, ?> report);
}
