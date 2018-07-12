package ws.exui.event;

public class ElementEventReport<K,V> extends DataEventReport implements I_ElementEventReport<K,V>{
	private V childTarget = null;
	private K childKey = null;

	public ElementEventReport(Object source, String msg) {
		super(source, msg);
	}
	@Override
	public void setTargetChild(V child) {
		this.childTarget = child;
	}
	@Override
	public V getTargetChild() {
		return this.childTarget;
	}
	@Override
	public void setKeyChild(K ckey) {
		this.childKey = ckey;
	}
	@Override
	public K getKeyChild() {
		return this.childKey;
	}

}
