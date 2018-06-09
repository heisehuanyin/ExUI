package ws.exui.event;

public class ValueChangeEvent implements I_ExEvent{
	private Object source = null;
	private String msg = "valueChange";
	
	public ValueChangeEvent(Object source, String msg) {
		this.source = source;
		this.msg = msg;
	}

	@Override
	public Object getSource() {
		return this.source;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}
}
