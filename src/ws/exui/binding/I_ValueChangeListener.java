package ws.exui.binding;

import ws.exui.event.I_ValueChangeReport;

public interface I_ValueChangeListener {
	/**
	 * 自动调用接口
	 * @param e 事件报告*/
	void valueChanged(I_ValueChangeReport e);
}
