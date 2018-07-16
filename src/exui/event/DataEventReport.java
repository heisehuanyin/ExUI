package exui.event;

import java.util.ArrayList;

public class DataEventReport implements I_DataEventReport{
	private Object source = null;
	private String msg = "valueChange";
	private ArrayList<Object> invokeList = new ArrayList<>();
	
	public DataEventReport(Object source, String msg) {
		this.source = source;
		this.msg = msg;
	}

	@Override
	public void addInvokePath(Object source) {
		this.invokeList.add(source);
	}

	@Override
	public boolean isPathContains(Object source) {
		boolean val = this.invokeList.contains(source);
		return val;
	}

	@Override
	public Object getPrimarySource() {
		return this.source;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}
}
