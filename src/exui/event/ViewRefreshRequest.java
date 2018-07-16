package exui.event;

import exui.uiview.I_View;

public class ViewRefreshRequest implements I_UIExEvent {
	private I_View source = null;
	private String msg = "";
	
	public ViewRefreshRequest(I_View source, String msg) {
		this.source = source;
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}

	@Override
	public I_View getPrimarySource() {
		return this.source;
	}

}
