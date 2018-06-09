package ws.exui.binding;

import java.util.ArrayList;

import ws.exui.event.ValueChangeEvent;

public abstract class ExDouble implements I_ValueChangeListener, I_BindingObject {
	private double value = 0;
	private ArrayList<I_ValueChangeListener> listeners = new ArrayList<>();
	
	public void setValue(double value) {
		this.setValueInner(value, null);
	}
	
	private void setValueInner(double value2, I_ValueChangeListener object) {
		this.value = value2;
		this.notifyAll(object);
	}
	
	public double getValue() {
		return this.value;
	}

	@Override
	public void valueChanged(ValueChangeEvent e) {
		this.setValueInner(value, (I_ValueChangeListener) e.getSource());
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
		for(I_ValueChangeListener i:listeners ) {
			if(i == except)
				continue;
			ValueChangeEvent e = new ValueChangeEvent(this, "exint value change");
			i.valueChanged(e);
		}
	}

}
