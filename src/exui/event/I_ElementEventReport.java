package exui.event;

public interface I_ElementEventReport<K,V> extends I_DataEventReport{

	void setTargetValue(V child);
	void setTargetKey(K ckey);

	V getTargetValue();
	K getTargetKey();

}
