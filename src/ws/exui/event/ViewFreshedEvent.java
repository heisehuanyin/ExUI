package ws.exui.event;

import ws.exui.uiview.I_View;

public class ViewFreshedEvent implements I_UIExEvent {
	private I_View source = null;
	private String msg = "";
	
	public ViewFreshedEvent(I_View source, String msg) {
		this.source = source;
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}

	@Override
	public I_View getSource() {
		return this.source;
	}

}
