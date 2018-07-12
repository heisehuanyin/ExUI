package ws.exui.event;

import java.util.ArrayList;

public class DataEventReport implements I_DataEventReport{
	private Object source = null;
	private String msg = "valueChange";
	private ArrayList<String> invokeList = new ArrayList<>();
	
	public DataEventReport(Object source, String msg) {
		this.source = source;
		this.msg = msg;
	}

	@Override
	public void addInvokePath(Object source) {
		this.invokeList.add(""+source.hashCode());
	}

	@Override
	public boolean isPathContains(Object source) {
		return this.invokeList.contains(""+source.hashCode());
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