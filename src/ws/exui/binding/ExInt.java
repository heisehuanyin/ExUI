package ws.exui.binding;

import java.util.ArrayList;

import ws.exui.event.ValueChangedReport;

public abstract class ExInt implements I_ValueChangeListener, I_BindingObject{
	private int value = 0;
	private ArrayList<I_ValueChangeListener> listeners = new ArrayList<>();
	
	public void setValue(int i) {
		this.setValueInner(i, null);
	}
	private void setValueInner(int i, I_ValueChangeListener except) {
		this.setValue(i);
		notifyAll(except);
	}
	
	public int getValue() {
		return this.value;
	}

	@Override
	public void valueChanged(ValueChangedReport e) {
		this.setValueInner(((ExInt)e.getSource()).getValue(), (I_ValueChangeListener) e.getSource());
		this.externalChange();
	}
	
	protected abstract void externalChange();

	@Override
	public void addValueChangeListener(I_ValueChangeListener l) {
		this.listeners.add(l);
	}

	@Override
	public void removeValueChangeListener(I_ValueChangeListener l) {
		if(this.listeners.contains(l)) {
			this.listeners.remove(l);
		}
	}
	
	private void notifyAll(I_ValueChangeListener except) {
		for(I_ValueChangeListener i:listeners) {
			if(i == except)
				continue;
			ValueChangedReport e = new ValueChangedReport(this, "exint value change");
			i.valueChanged(e);
		}
	}

}
