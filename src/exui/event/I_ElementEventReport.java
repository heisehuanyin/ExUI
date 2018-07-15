package exui.event;

public interface I_ElementEventReport<K,V> extends I_DataEventReport{

	void setTargetChild(V child);
	void setKeyChild(K ckey);

	V getTargetChild();
	K getKeyChild();

}
