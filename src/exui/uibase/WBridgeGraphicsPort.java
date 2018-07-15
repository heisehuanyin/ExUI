package exui.uibase;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;

import exui.uiview.I_View;

public class WBridgeGraphicsPort implements I_GraphicsPort {
	private Frame wframe = null;
	private I_View win;
	
	public WBridgeGraphicsPort(I_View win, Frame twin) {
		this.wframe = twin;
		this.win = win;
	}

	@Override
	public void drawLine(I_Point origin, I_Point end) {
		Graphics2D g2d = (Graphics2D) this.wframe.getGraphics();
		g2d.drawLine((int)origin.getX(), (int)origin.getY(), (int)end.getX(), (int)end.getY());
	}

	@Override
	public void drawShape(Shape s, Color c) {
		Graphics2D g2d = (Graphics2D) this.wframe.getGraphics();
		if(c == null)
			c = this.win.color_GetColor();
		g2d.setColor(c);
		s = this.shape_Convert(s);
		g2d.draw(s);
	}

	@Override
	public void fillShape(Shape s, Color c) {
		Graphics2D g2d = (Graphics2D) this.wframe.getGraphics();
		if(c == null)
			c = this.win.color_GetColor();
		g2d.setColor(c);
		s = this.shape_Convert(s);
		g2d.fill(s);
	}

	@Override
	public void drawString(String s, I_Point point, Font format, Area clipShape) {
		Graphics2D g2d = (Graphics2D) this.wframe.getGraphics();
		g2d.setFont(format);
		clipShape = this.shape_Convert(clipShape);
		g2d.setClip(clipShape);
		g2d.drawString(s, (float)point.getX(), (float)point.getY());
	}
	
	/**
	 * 将本视图中的图形坐标转换为父视图坐标，相对位置不变。
	 * @param s 图形
	 * @return 父视图中的图形*/
	private Area shape_Convert(Shape s) {
		Area sSymbo = new Area(s);
		Area clip = this.win.clip_getClipShape();
		
		//获取在裁切区域内部的真实图形
		sSymbo.intersect(clip);
		
		//获取转换方式--父坐标中的图形转换为子坐标的图形
		//将字坐标图形变为父坐标图形，以便传递给父视图绘制
		sSymbo.transform(this.win.transform_GetTransformInner());
		
		return sSymbo;
	}

}
