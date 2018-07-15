package exui.uibase;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;

import exui.uiview.I_View;

public class WGraphicsPort implements I_GraphicsPort {
	private I_View view = null;

	
	public WGraphicsPort(I_View viewOwner) {
			this.view = viewOwner;
	}
	
	@Override
	public void drawLine(I_Point origin, I_Point end) {
		System.out.println("尚未实现DrewLine函数");
	}

	@Override
	public void drawShape(Shape s, Color c) {
		Area sSymbo = this.shape_Convert(s);
		if(c == null)
			c = this.view.color_GetColor();
		this.view.__view_GetParentView().gport_getGraphicsPort().drawShape(sSymbo, c);
	}

	@Override
	public void fillShape(Shape s, Color c) {
		Area sSymbo = this.shape_Convert(s);
		if(c == null)
			c = this.view.color_GetColor();
		this.view.__view_GetParentView().gport_getGraphicsPort().fillShape(sSymbo, c);
	}
	
	/**
	 * 将本视图中的图形坐标转换为父视图坐标，相对位置不变。
	 * @param s 图形
	 * @return 父视图中的图形*/
	private Area shape_Convert(Shape s) {
		Area sSymbo = new Area(s);
		Area clip = this.view.clip_getClipShape();
		
		//获取在裁切区域内部的真实图形
		sSymbo.intersect(clip);
		
		//获取转换方式--父坐标中的图形转换为子坐标的图形
		//将字坐标图形变为父坐标图形，以便传递给父视图绘制
		sSymbo.transform(this.view.transform_GetTransformInner());
		
		return sSymbo;
	}

	@Override
	public void drawString(String s, I_Point point, Font format, Area clipShape) {
		//如果没有设定clip，则默认组件的全部可视区域
		if(clipShape == null) {
			clipShape = this.view.clip_getClipShape();
		}
		//转换为父视图中的图形
		clipShape = this.shape_Convert(clipShape);
		Area spoint = new Area(new Rectangle((int)point.getX(), (int)point.getY(),2,2));
		spoint = this.shape_Convert(spoint);
		point = new WPoint(spoint.getBounds().getX(), spoint.getBounds().getY());
		
		this.view.__view_GetParentView().gport_getGraphicsPort().drawString(s, point, format, clipShape);
	}
	
}
