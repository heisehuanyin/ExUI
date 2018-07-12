package ws.exui.uibase;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;

import ws.exui.uiview.I_View;

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
	
	private Area shape_Convert(Shape s) {
		Area sSymbo = new Area(s);
		I_Size size = this.view.size_GetVisibleSize();
		Area clip = new Area(new Rectangle((int)size.getWidth(), (int)size.getHeight()));
		
		//获取在裁切区域内部的真实图形
		sSymbo.intersect(clip);
		
		//获取转换方式--父坐标中的图形转换为子坐标的图形
		I_Transform ft = this.view.transform_GetTransform();
		//将字坐标图形变为父坐标图形，传递给父视图绘制
		sSymbo.transform(ft.getAffineTransform());
		
		return sSymbo;
	}

	@Override
	public void drawString(String s, I_Point point, Font format) {
		// TODO Auto-generated method stub
		
	}
	
}
