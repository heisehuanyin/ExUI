package exui.binding;

/**
 * 本类是一个简单类型，用于数据绑定，可应用数据类型为两大类：Value值类型、Object对象类型。</br>
 * 对于值类型，要求只能同种类型之间绑定，对于对象类型，需要定制转换器，便于数据转换。</br>
 * 因为绑定过程中，所有绑定对象收到的事件统一由最初的数据源发出，因此平行绑定与串行绑定无区别。*/
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
		front.autoPush();
	}
	
	public void Binding(I_ListCommon<?> front, I_ListCommon<?> end) {
		front.addChangeListener(end);
		if(this.bridgeType == ExBindingBridge.POINT_2_POINT) {
			end.addChangeListener(front);
		}
		end.clearAll();
		front.autoPush();
	}
	
	public void Binding(I_Object4BindingCommon front, I_Object4BindingCommon end) {
		this.Binding(front.getChildren(), end.getChildren());
	}
	
}
