package ws.exui.binding;

public class ExBindingBridge {
	public static final int FRONT_2_END = 0;
	public static final int POINT_2_POINT = 1;
	private int bridgeType = 0;
	
	public ExBindingBridge(int type) {
		this.bridgeType = type;
	}
	
	public void Binding(I_ValueCommon front, I_ValueCommon end) {
		front.addChangeListener(end);
		if(this.bridgeType == ExBindingBridge.POINT_2_POINT) {
			end.addChangeListener(front);
		}
	}
	
	public void Binding(I_ListCommon<?> front, I_ListCommon<?> end) {
		front.addChangeListener(end);
		if(this.bridgeType == ExBindingBridge.POINT_2_POINT) {
			end.addChangeListener(front);
		}
	}
	
}