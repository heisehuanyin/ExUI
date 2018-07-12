package ws.exui;

import ws.exui.uibase.WSize;
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



	@Override
	public void run() {
		// TODO 处理各种UI相关事件
		
	}
	

	public static void main(String[] args) {
		ExManager vm = new ExManager();
		
		//创建窗体，初始化窗体
		ExWindow exw = new ExWindow("ExUI_Window", null);
		exw.setSize(new WSize(800,600));

		//打开窗体
		vm.openWindow(exw);
		
		//manager自行运行
		new Thread(vm).start();
	}


}
