package exui.binding;

import exui.event.I_DataEventReport;

public interface I_ValueChangeListener {
	/**
	 * 自动调用接口
	 * @param e 事件报告*/
	void valueChanged(I_DataEventReport e);
}