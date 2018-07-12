package ws.exui.binding;

import java.util.ArrayList;

import ws.exui.event.ValueChangeReport;
import ws.exui.event.I_ValueChangeReport;

public class ExDouble implements I_ValueChangeListener, I_ValueCommon {
	private ArrayList<I_ValueChangeListener> l_list = new ArrayList<>();
	private double val = 0;

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
		
		this.val = ((ExDouble)e.getSource()).getValue();
		this.__invokeAll(e);
	}
	
	public void setValue(Double d) {
		this.val = d;
		I_ValueChangeReport report = new ValueChangeReport(this, this.hashCode()+"赋值："+this.val);
		this.__invokeAll(report);
	}
	public double getValue() {
		return this.val;
	}

}
