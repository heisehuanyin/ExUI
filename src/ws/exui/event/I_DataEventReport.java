package ws.exui.event;

public interface I_DataEventReport extends I_ExEvent {

	/**
	 * 记录下该事件至今调用的对象
	 * @param source 该对象的hashcode将被记录到report中*/
	void addInvokePath(Object source);

	/**
	 * 测试是否掉用过该对象，路径集中是否包含该对象
	 * @param source 指定对象*/
	boolean isPathContains(Object source);

}
