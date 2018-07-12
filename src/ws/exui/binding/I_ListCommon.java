package ws.exui.binding;

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
}
