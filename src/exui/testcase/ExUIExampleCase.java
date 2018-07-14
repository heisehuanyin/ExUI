package exui.testcase;

import java.awt.Color;

import ws.exui.ExManager;
import ws.exui.binding.ExBindingBridge;
import ws.exui.binding.ExList;
import ws.exui.binding.ExO4Binding;
import ws.exui.binding.I_ListCommon;
import ws.exui.binding.I_Object4BindingCommon;
import ws.exui.event.I_ElementEventReport;
import ws.exui.uibase.WMargin;
import ws.exui.uibase.WSize;
import ws.exui.uiview.ExSplitPane;
import ws.exui.uiview.I_UIBuilder;
import ws.exui.uiview.I_View;
import ws.exui.uiwin.ExWindow;

public class ExUIExampleCase {

	public static void main(String[] args) {
ExManager vm = new ExManager();
		
		//创建窗体，初始化窗体
		ExWindow exw = new ExWindow("ExUI_Window", null, vm);
		exw.setSize(new WSize(800,600));
		
		//添加子视图
		I_View v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.LIGHT_GRAY);
		v_one.size_SetBasicSize(new WSize(20,20));
		v_one.adjust_SetAutoAdjust(true, false);
		
		v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.blue);
		v_one.margin_SetMargin(new WMargin(2,2,2,0));
		
		for(int i=0;i<8;++i) {
			I_View x = new ExSplitPane(ExSplitPane.HORIZONTAL);
			v_one.view_InsertViewAtIndex(x, v_one.view_GetChildCount());
			x.margin_SetMargin(new WMargin(2,2,2,2));
			x.color_SetColor(new Color(200,100, 30 * i));
		}
		
		v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.green);
		v_one.margin_SetMargin(new WMargin(2,2,2,0));
		v_one.setUIBuilder(new I_UIBuilder<I_Object4BindingCommon>() {
			@Override
			public I_View generateViewUnitAs(I_Object4BindingCommon d_unit) {
				I_View view = new ExSplitPane(ExSplitPane.HORIZONTAL);
				view.color_SetColor(Color.WHITE);
				view.margin_SetMargin(new WMargin(3,3,3,3));
				return view;
			}
			
		});
		
		ExBindingBridge bridge = new ExBindingBridge(ExBindingBridge.POINT_2_POINT);
		bridge.Binding(new ExO4Binding() {
			private I_ListCommon<I_Object4BindingCommon> strCon = new TemplateExListWap();
			@Override
			public I_ListCommon<I_Object4BindingCommon> getChildren() {
				return this.strCon;
			}
			
		}, v_one);
		
		
		
		
		v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.orange);
		v_one.margin_SetMargin(new WMargin(2,2,2,0));
		
		v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.CYAN);
		v_one.margin_SetMargin(new WMargin(2,2,2,2));
		

		//打开窗体
		vm.openWindow(exw);
		
		//manager自行运行
		new Thread(vm).start();
	}


	static class TemplateExListWap extends ExList<I_Object4BindingCommon>{
		public TemplateExListWap() {
			this.insertChildAtIndex(new ExSplitPane(0), 0);
			this.insertChildAtIndex(new ExSplitPane(0), 0);
			this.insertChildAtIndex(new ExSplitPane(0), 0);
		}

		@Override
		public I_Object4BindingCommon dataProcAtInsert(I_ElementEventReport<?, ?> report) {
			return (I_Object4BindingCommon) report.getTargetChild();
		}}
}
