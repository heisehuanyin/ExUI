package exui.event;

public class ElementEventReport<K,V> extends DataEventReport implements I_ElementEventReport<K,V>{
	private V childTarget = null;
	private K childKey = null;

	public ElementEventReport(Object source, String msg) {
		super(source, msg);
	}
	@Override
	public void setTargetValue(V child) {
		this.childTarget = child;
	}
	@Override
	public V getTargetValue() {
		return this.childTarget;
	}
	@Override
	public void setTargetKey(K ckey) {
		this.childKey = ckey;
	}
	@Override
	public K getTargetKey() {
		return this.childKey;
	}

}
