package ws.exui.uiview;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import ws.exui.event.I_UIExEvent;
import ws.exui.event.ViewRefreshRequest;
import ws.exui.uibase.WEmptyTransform;
import ws.exui.uibase.I_GraphicsPort;
import ws.exui.uibase.I_Margin;
import ws.exui.uibase.I_Point;
import ws.exui.uibase.I_Size;
import ws.exui.uibase.I_Transform;
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
	private I_GraphicsPort port = new WGraphicsPort(this);
	private I_View parent = null;
	private boolean width_auto = true;
	private boolean height_auto = true;
	private boolean isFresh = true;
	private Color c = Color.white;
	private I_Transform trans = new WEmptyTransform();

	public WView() {}
	
	@Override
	public void fresh_SetFresh(boolean isfresh) {
		this.isFresh = isfresh;
	}
	@Override
	public boolean fresh_IsFresh() {
		return this.isFresh;
	}
	
	@Override
	public void adjust_SetAutoAdjust(boolean width, boolean height) {
		this.width_auto = width;
		this.height_auto = height;
		this.fresh_SetFresh(true);
		
		I_UIExEvent e = new ViewRefreshRequest(this, "设置了自适应");
		this.event_DispatchUIEvent(e);
	}
	@Override
	public boolean adjust_IsAutoWidth() {return this.width_auto;}
	@Override
	public boolean adjust_IsAutoHeight() {return this.height_auto;}

	
	@Override
	public void color_SetColor(Color c) {
		if(c != null) {
			this.c = c;
			this.fresh_SetFresh(true);
			
			I_UIExEvent e = new ViewRefreshRequest(this, "设置了Color："+c.toString());
			this.event_DispatchUIEvent(e);
		}
	}
	
	@Override
	public Color color_GetColor() {
		return this.c;
	}
	
	@Override
	public void transform_SetTransform(I_Transform trans) {
		if(trans != null) {
			this.trans = trans;
			this.fresh_SetFresh(true);
			
			I_UIExEvent e = new ViewRefreshRequest(this, "设置了转换器："+trans.toString());
			this.event_DispatchUIEvent(e);
		}
	}
	@Override
	public AffineTransform transform_GetTransformInner() {
		AffineTransform tf = new AffineTransform();
		tf.translate(origin.getX(), origin.getY());
		this.trans.setTransformInner(tf);
		
		return tf;
	}

	@Override
	public void size_SetBasicSize(I_Size bSize) {
		this.basicSize = bSize;
		if(!this.adjust_IsAutoWidth()) {
			this.visibleSize = new WSize(bSize.getWidth(), this.visibleSize.getHeight());
		}
		if(!this.adjust_IsAutoHeight()) {
			this.visibleSize = new WSize(this.visibleSize.getHeight(), bSize.getHeight());
		}

		System.out.println(this.toString() + "设置基础尺寸："+ bSize.toString());
		
		I_UIExEvent e = new ViewRefreshRequest(this, "basicSize更新");
		this.fresh_SetFresh(true);
		this.event_DispatchUIEvent(e);
	}
	
	@Override
	public I_Size size_GetBasicSize() {
		return this.basicSize;
	}

	@Override
	public void size_SetVisibleSize(I_Size vSize) {
		if(vSize.getWidth() > this.basicSize.getWidth() ||
				vSize.getHeight() > this.basicSize.getHeight()) {
			if(this.adjust_IsAutoWidth() && vSize.getWidth() > this.basicSize.getWidth()) {}
			else {
				vSize = new WSize(this.basicSize.getWidth(), vSize.getHeight());
			}
			
			if(this.adjust_IsAutoHeight() && vSize.getHeight() > this.basicSize.getHeight()) {}
			else {
				vSize = new WSize(vSize.getWidth(), this.basicSize.getHeight());
			}
			
			this.visibleSize = vSize;
			System.out.println(this.toString() + "设置可视尺寸："+ vSize.toString());
			this.fresh_SetFresh(true);
			this.__operate_ResizeSubviewSize();
			
			I_UIExEvent e = new ViewRefreshRequest(this, "重置了视图尺寸："+vSize.toString());
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
			this.fresh_SetFresh(true);
			
			I_UIExEvent e = new ViewRefreshRequest(this, "重设了视图边距："+nMargin.toString());
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
			this.fresh_SetFresh(true);
			
			I_UIExEvent e = new ViewRefreshRequest(this, "重置了原点："+origin.toString());
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
		this.fresh_SetFresh(true);
		
		I_UIExEvent e = new ViewRefreshRequest(this, "插入了子视图："+view.toString());
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
		this.fresh_SetFresh(true);
		
		I_UIExEvent e = new ViewRefreshRequest(this, "删除了子视图:"+view.toString());
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
	public I_GraphicsPort gport_getGraphicsPort() {
		return this.port;
	}
	
	
	@Override
	public void operate_RePaint() {
		this.fresh_SetFresh(true);
		
		//绘制组件自身
		I_UIExEvent e = new ViewRefreshRequest(this, "调用了Repaint方法,请求整体重绘");
		this.event_DispatchUIEvent(e);
	}
	
	@Override
	public abstract void __operate_ResizeSubviewSize();
	
	/**
	 * 绘制组件自身，传入的绘图端口是通过rePaint函数配置好的实例
	 * @param port 配置完成的端口*/
	@Override
	public abstract void __paintItSelf(I_GraphicsPort port);
	
	/**
	 * 派送UI事件*/
	@Override
	public void event_DispatchUIEvent(I_UIExEvent e) {
		this.__view_GetParentView().event_DispatchUIEvent(e);
	}
	

	@Override
	public Area clip_getClipShape() {
		Area thisShape = new Area(new Rectangle2D.Double(0, 0,
				this.visibleSize.getWidth(), this.visibleSize.getHeight()));
		
		return thisShape;
	}
}
