package ws.exui.binding;

import java.util.ArrayList;

import ws.exui.event.BindingValueChangeReport;
import ws.exui.event.I_ValueChangeReport;

public class ExString implements I_ValueChangeListener,I_BindingObject{
	private ArrayList<I_ValueChangeListener> l_list = new ArrayList<>();
	private String val = "";

	@Override
	public void addChangeListener(I_ValueChangeListener l) {
		if(l != null)
			this.l_list.add(l);
	}
	@Override
	public void removeChangeListener(I_ValueChangeListener l) {
		if(this.l_list.contains(l))
			this.l_list.remove(l);
	}
	
	private void __invokeAll(I_ValueChangeReport report) {
		report.addInvokePath(this);
		for(I_ValueChangeListener l:l_list) {
			l.valueChanged(report);
		}
	}
	
	@Override
	public void valueChanged(I_ValueChangeReport e) {
		if(e.isPathContains(this))
			return;
		
		this.val = ((ExString)e.getSource()).getValue();
		this.__invokeAll(e);
	}
	
	public void setValue(String s) {
		this.val = s;
		I_ValueChangeReport report = new BindingValueChangeReport(this, this.hashCode()+"赋值："+this.val);
		this.__invokeAll(report);
	}
	
	public String getValue() {
		return this.val;
	}

}
