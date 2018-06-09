package ws.exui.uiview;

import java.awt.Color;
import java.util.ArrayList;

import ws.exui.event.I_UIExEvent;
import ws.exui.event.ViewFreshedEvent;
import ws.exui.uibase.I_Clip;
import ws.exui.uibase.I_GraphicsPort;
import ws.exui.uibase.I_Margin;
import ws.exui.uibase.I_Point;
import ws.exui.uibase.I_Size;
import ws.exui.uibase.I_Transform;
import ws.exui.uibase.ViewRectClip;
import ws.exui.uibase.WGraphicsPort;
import ws.exui.uibase.WMargin;
import ws.exui.uibase.WPoint;
import ws.exui.uibase.WSize;

public abstract class WView implements I_View {
	private I_Size basicSize = new WSize(0, 0);
	private I_Size visibleSize = new WSize(0, 0);
	private I_Point origin = new WPoint(0, 0);
	private I_Margin margin = new WMargin(0, 0, 0, 0);
	private ArrayList<I_View> children = new ArrayList<>();
	private I_GraphicsPort port = new WGraphicsPort();
	private I_View parent = null;
	private boolean width_auto = true;
	private boolean height_auto = true;
	private I_Clip clip = new ViewRectClip(this);
	private boolean isFresh = true;

	public WView() {
		//设置绘制约束，一般不会变了
		this.port.setClip(clip);
		this.port.initGraphicsPort(this);
	}
	@Override
	public void bool_SetFresh(boolean isfresh) {
		this.isFresh = isfresh;
	}
	@Override
	public boolean bool_IsFresh() {
		return this.isFresh;
	}
	
	@Override
	public void bool_SetAdjustAuto(boolean width, boolean height) {
		this.width_auto = width;
		this.width_auto = height;
		this.bool_SetFresh(true);
		I_UIExEvent e = new ViewFreshedEvent(this, "设置了自适应");
		this.event_DispatchUIEvent(e);
	}
	@Override
	public boolean bool_IsAutoWidth() {return this.width_auto;}
	@Override
	public boolean bool_IsAutoHeight() {return this.height_auto;}

	
	@Override
	public void color_SetColor(Color c) {
		if(c != null) {
			this.port.setColor(c);
			this.bool_SetFresh(true);
			I_UIExEvent e = new ViewFreshedEvent(this, "设置了Color："+c.toString());
			this.event_DispatchUIEvent(e);
		}
	}
	
	@Override
	public void transform_SetTransform(I_Transform trans) {
		if(trans != null) {
			this.port.setTransform(trans);
			this.bool_SetFresh(true);
			I_UIExEvent e = new ViewFreshedEvent(this, "设置了转换器："+trans.toString());
			this.event_DispatchUIEvent(e);
		}
	}

	public void size_SetBasicSize(I_Size bSize) {
		this.basicSize = bSize;
	}
	
	@Override
	public I_Size size_GetBasicSize() {
		return this.basicSize;
	}

	@Override
	public void size_SetVisibleSize(I_Size vSize) {
		if(vSize.getWidth() > this.visibleSize.getWidth() &&
				vSize.getHeight() > this.visibleSize.getHeight()) {
			this.visibleSize = vSize;
			this.bool_SetFresh(true);
			I_UIExEvent e = new ViewFreshedEvent(this, "重置了视图尺寸："+vSize.toString());
			this.event_DispatchUIEvent(e);
		}
	}

	@Override
	public I_Size size_GetVisibleSize() {
		return this.visibleSize;
	}

	@Override
	public void margin_SetMargin(I_Margin nMargin) {
		if(nMargin != null) {
			this.margin = nMargin;
			this.bool_SetFresh(true);
			I_UIExEvent e = new ViewFreshedEvent(this, "重设了视图边距："+nMargin.toString());
			this.event_DispatchUIEvent(e);
		}
	}

	@Override
	public I_Margin margin_GetMargin() {
		return this.margin;
	}

	@Override
	public void point_SetOriginPoint(I_Point origin) {
		if(origin != null) {
			this.origin = origin;
			this.bool_SetFresh(true);
			I_UIExEvent e = new ViewFreshedEvent(this, "重置了原点："+origin.toString());
			this.event_DispatchUIEvent(e);
		}
	}

	@Override
	public I_Point point_GetOriginPoint() {
		return this.origin;
	}
	
	
	@Override
	public void view_InsertViewAtIndex(I_View view, int index) {
		view.__view_SetParentView(this);
		this.children.add(index, view);
		this.bool_SetFresh(true);
		I_UIExEvent e = new ViewFreshedEvent(this, "插入了子视图："+view.toString());
		this.event_DispatchUIEvent(e);
	}
	
	@Override
	public int view_GetChildCount() {
		return this.children.size();
	}
	
	@Override
	public I_View view_GetChildAtIndex(int index) {
		return this.children.get(index);
	}
	
	@Override
	public void view_RemoveViewAtIndex(I_View view) {
		if(this.children.contains(view))
			this.children.remove(view);
		this.bool_SetFresh(true);
		I_UIExEvent e = new ViewFreshedEvent(this, "删除了子视图:"+view.toString());
		this.event_DispatchUIEvent(e);
	}
	
	@Override
	public void __view_SetParentView(WView wView) {
		this.parent = wView;
	}

	@Override
	public I_View __view_GetParentView() {
		return this.parent;
	}
	
	@Override
	public I_GraphicsPort getGraphicsPort() {
		return this.port;
	}
	
	
	@Override
	public void operate_RePaint() {
		//绘制组件自身
		this.__paintItSelf(this.port);
	}
	
	@Override
	public abstract void __operate_ResizeSubviewSize();
	
	/**
	 * 绘制组件自身，传入的绘图端口是通过rePaint函数配置好的实例
	 * @param port 配置完成的端口*/
	protected abstract void __paintItSelf(I_GraphicsPort port);
	
	/**
	 * 派送UI事件*/
	@Override
	public void event_DispatchUIEvent(I_UIExEvent e) {
		this.__view_GetParentView().event_DispatchUIEvent(e);
	}
}
