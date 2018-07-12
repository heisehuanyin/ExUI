package ws.exui.binding;

public interface I_ValueCommon extends I_ValueChangeListener{
	/**
	 * 添加Listener
	 * @param l Listener*/
	void addChangeListener(I_ValueChangeListener l);

	/**
	 * 移除Listener，如果存在的话
	 * @param l Listener*/
	void removeChangeListener(I_ValueChangeListener l);
}
