package ws.exui.binding;

import ws.exui.event.I_DataEventReport;

public interface I_ListChangeListener {
	/**
	 * 被绑定对象remove动作时，自动调用该方法
	 * @param report 对象详细动作报告*/
	void targetRemove(I_DataEventReport report);

	/**
	 * 被绑定对象insert动作时，自动调用该方法
	 * @param report 对象详细动作报告*/
	void targetInsert(I_DataEventReport report);

}