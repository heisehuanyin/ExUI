package ws.exui;

import java.awt.Color;

import ws.exui.event.I_ExEvent;
import ws.exui.uibase.WMargin;
import ws.exui.uibase.WSize;
import ws.exui.uiview.I_View;
import ws.exui.uiwin.ExSplitPane;
import ws.exui.uiwin.ExWindow;
import ws.exui.uiwin.I_Window;

public class ExManager implements Runnable{
	public ExManager() {}
	
	/**
	 * 打开指定窗口
	 * @param exw 指定窗口实例*/
	public void openWindow(I_Window exw) {
		exw.__display();
	}
	
	public void event_AcceptEvent(I_ExEvent e) {
		
	}



	@Override
	public void run() {
		// TODO 处理各种UI相关事件
		
	}
	

	public static void main(String[] args) {
		ExManager vm = new ExManager();
		
		//创建窗体，初始化窗体
		ExWindow exw = new ExWindow("ExUI_Window", null, vm);
		exw.setSize(new WSize(800,600));
		
		//添加子视图
		I_View v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.blue);
		v_one.margin_SetMargin(new WMargin(2,2,2,2));
		
		v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.green);
		v_one.margin_SetMargin(new WMargin(2,2,2,2));
		
		v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.color_SetColor(Color.orange);
		v_one.margin_SetMargin(new WMargin(2,2,2,2));
		
		v_one = new ExSplitPane(ExSplitPane.HORIZONTAL);
		exw.view_InsertViewAtIndex(v_one, exw.view_GetChildCount());
		v_one.margin_SetMargin(new WMargin(2,2,2,2));
		

		//打开窗体
		vm.openWindow(exw);
		
		//manager自行运行
		new Thread(vm).start();
	}


}
