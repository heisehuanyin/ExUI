package exui.testcase;

import ws.exui.binding.ExBindingBridge;
import ws.exui.binding.ExBoolean;
import ws.exui.binding.ExDouble;

public class BindingTest {
	public void testBindBool() {
		ExBindingBridge bridge = new ExBindingBridge(ExBindingBridge.POINT_2_POINT);
		ExBoolean a = new ExBoolean();
		ExBoolean b = new ExBoolean();
		
		bridge.Binding(a, b);
		
		System.out.println(b.getValue());
		a.setValue(true);
		System.out.println(b.getValue());
		a.setValue(false);
		System.out.println(b.getValue());
		
		System.out.println(a.getValue());
		b.setValue(true);
		System.out.println(a.getValue());
		b.setValue(false);
		System.out.println(a.getValue());
	}
	public void testDouble() {
		ExBindingBridge bridge = new ExBindingBridge(ExBindingBridge.POINT_2_POINT);
		ExDouble a = new ExDouble();
		a.setValue(3008.0);
		ExDouble b = new ExDouble();
		
		bridge.Binding(a, b);
		
		System.out.println(b.getValue());
		a.setValue(22.0);
		System.out.println(b.getValue());
		a.setValue(33.0);
		System.out.println(b.getValue());
		
		System.out.println(a.getValue());
		b.setValue(302.0);
		System.out.println(a.getValue());
		b.setValue(150.0);
		System.out.println(a.getValue());
	}
	
	public static void main(String[] args) {
		BindingTest t = new BindingTest();
		t.testBindBool();
		t.testDouble();
	}
}