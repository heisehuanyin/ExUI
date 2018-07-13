package exui.testcase;

import ws.exui.binding.ExBindingBridge;
import ws.exui.binding.ExBoolean;
import ws.exui.binding.ExDouble;
import ws.exui.binding.ExListWap;
import ws.exui.binding.ExO4Binding;
import ws.exui.binding.ExString;
import ws.exui.binding.I_ListCommon;
import ws.exui.binding.I_Object4BindingCommon;
import ws.exui.event.I_ElementEventReport;

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
	public void testString() {
		ExBindingBridge bridge = new ExBindingBridge(ExBindingBridge.POINT_2_POINT);
		ExString a = new ExString();
		a.setValue("a初始无值");
		ExString b = new ExString();
		
		bridge.Binding(a, b);
		
		System.out.println(b.getValue());
		a.setValue(":"+22.0);
		System.out.println(b.getValue());
		a.setValue(":"+33.0);
		System.out.println(b.getValue());
		
		System.out.println(a.getValue());
		b.setValue(":"+302.0);
		System.out.println(a.getValue());
		b.setValue(":"+150.0);
		System.out.println(a.getValue());
		
	}
	public void testList() {
		ExBindingBridge bridge = new ExBindingBridge(ExBindingBridge.POINT_2_POINT);
		ExListWap<String> a = new ExListWap<>() {

			@Override
			public String dataProcAtInsert(I_ElementEventReport<?, ?> report) {
				// TODO Auto-generated method stub
				return (String) report.getTargetChild();
			}};
		ExListWap<String> b = new ExListWap<>() {

			@Override
			public String dataProcAtInsert(I_ElementEventReport<?, ?> report) {
				// TODO Auto-generated method stub
				return (String) report.getTargetChild();
			}};
		
		bridge.Binding(a, b);
		
		System.out.println(b.toString());
		a.insertChildAtIndex("f_1", a.getChildCount());
		System.out.println(b.toString());
		a.insertChildAtIndex("f_0", 0);
		System.out.println(b.toString());
	}
	
	public static void main(String[] args) {
		BindingTest t = new BindingTest();
		t.testBindBool();
		t.testDouble();
		t.testString();
		t.testList();
		t.testObject();
	}
	private void testObject() {
		// TODO Auto-generated method stub
		
	}
}

class ExampleObject extends ExO4Binding{
	public ExString name = new ExString();
	public ExDouble number = new ExDouble();
	@Override
	public I_ListCommon<I_Object4BindingCommon> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
