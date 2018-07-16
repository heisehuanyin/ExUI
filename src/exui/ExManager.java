package exui;

import exui.event.I_ExEvent;
import exui.uiwin.I_Window;

public class ExManager implements Runnable{
	public ExManager() {}
	
	/**
	 * 打开指定窗口
	 * @param exw 指定窗口实例*/
	public void openWindow(I_Window exw) {
		exw.__display(this);
	}
	
	public void event_AcceptEvent(I_ExEvent e) {
		System.out.println("&&&&::MsgProc======"+e.getPrimarySource().toString() +"《《"+ e.getMsg());
	}



	@Override
	public void run() {
		// TODO 处理各种UI相关事件
		
	}
}
