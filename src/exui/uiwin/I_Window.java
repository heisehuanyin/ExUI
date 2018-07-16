package exui.uiwin;

import exui.ExManager;
import exui.uibase.I_Size;

public interface I_Window {
	/**
	 * 是一个立即执行方法,设置窗口大小
	 * @param size 是一个记录了宽高的实例*/
	void setSize(I_Size size);

	/**
	 * 内部方法，显示窗口。所有内部调用的方法都用"__"前缀标识
	 * @param ctl TODO*/
	void __display(ExManager ctl);

	/**
	 * 一个立即执行方法，设置窗口标题
	 * @param title 标题名称*/
	void setTitle(String title);

	/**
	 * 注册资源
	 * @param id 资源key
	 * @param obj 实例*/
	void registerResource(String id, Object obj);

	/**
	 * 获取已经注册的资源
	 * @param id 资源key
	 * @return 资源*/
	Object getResource(String id);



}
