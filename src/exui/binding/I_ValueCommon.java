package exui.binding;

public interface I_ValueCommon extends I_ValueChangeListener{
	/**
	 * 添加Listener
	 * @param l Listener*/
	void addChangeListener(I_ValueChangeListener l);

	/**
	 * 移除Listener，如果存在的话
	 * @param l Listener*/
	void removeChangeListener(I_ValueChangeListener l);
	
	/**
	 * 第一次自动调用本函数同步数据 front->end*/
	void autoPush();
}
