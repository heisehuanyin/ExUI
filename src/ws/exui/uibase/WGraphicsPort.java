package ws.exui.uibase;

import java.awt.Color;

import ws.exui.uiview.I_View;

public class WGraphicsPort implements I_GraphicsPort {
	private I_Transform transform = new EmptyTransform();
	private I_View view = null;
	private I_Clip clip = new EmptyClip();
	private Color color = Color.gray;
	
	@Override
	public void setTransform(I_Transform trans) {
		if(trans != null)
			this.transform = trans;
	}
	@Override
	public void setClip(I_Clip nClip) {
		if(nClip != null)
			this.clip = nClip;
	}
	
	@Override
	public void setColor(Color c) {
		if(c != null)
			this.color = c;
	}

	@Override
	public void initGraphicsPort(I_View view) {
		if(view != null)
			this.view = view;
	}
	
	/**
	 * 绘制一个像素点,像素点的坐标以本视图的逻辑原点为基准
	 * @param point 目标像素点*/
	@Override
	public void setPixel(Color c, I_Point point) {
		if(c == null)
			c = this.color;
		
		//应用变换，将逻辑坐标的像素点转换为实际坐标的像素点
		I_Point npoint = this.transform.translate(point);
		
		//范围校验，是否在正确范围
		if(this.clip.checkPoint(npoint)) {
			this.setPixelInner(c, npoint);
		}
	}
	/**
	 * 设置一个像素，本方法用来覆盖
	 * @param c 指定颜色
	 * @param point 像素坐标*/
	protected void setPixelInner(Color c, I_Point point) {
		I_Point ppoint = this.view.point_GetOriginPoint();
		//获取父视图的逻辑坐标
		I_Point fpoint = new WPoint(ppoint.getX()+point.getX(),ppoint.getY()+point.getY());
		this.view.__view_GetParentView().getGraphicsPort().setPixel(c, fpoint);
	}
	
	@Override
	public void drawLine(I_Point origin, I_Point end) {
		if(origin.getX() > end.getX() ) {
			I_Point temp = end;
			end = origin;
			origin = temp;
		}
		
		double xl = end.getX() - origin.getX();
		double yl = end.getY() - origin.getY();
		if(xl >= 0 && xl < 2) {
			if(yl > 0) {
				for(double ysteps=origin.getY(); ysteps < end.getY(); ++ysteps) {
					I_Point resultPoint = new WPoint(origin.getX(), ysteps);
					this.setPixel(null, resultPoint);
				}
			}else {
				for(double ysteps=origin.getY(); ysteps > end.getY(); --ysteps) {
					I_Point resultPoint = new WPoint(origin.getX(), ysteps);
					this.setPixel(null, resultPoint);
				}
			}
		}
		double times = yl / xl;
		double z = origin.getY() - origin.getX() * times;
		
		for(double xsteps = origin.getX(); xsteps < end.getX(); ++xsteps) {
			double ysteps = times * xsteps + z ;
			I_Point resultPoint = new WPoint(xsteps, ysteps);
			this.setPixel(null, resultPoint);
		}
	}
	@Override
	public void drawRectangle(I_Point origin, I_Size size) {
		I_Point p2 = new WPoint(origin.getX(), origin.getY() + size.getHeight());
		I_Point p3 = new WPoint(origin.getX()+size.getWidth(), origin.getY()+size.getHeight());
		I_Point p4 = new WPoint(origin.getX()+size.getWidth(), origin.getY());
		
		this.drawLine(origin, p2);
		this.drawLine(p2, p3);
		this.drawLine(p3, p4);
		this.drawLine(p4, origin);
	}
	
	@Override
	public void fillRectangle(I_Point origin, I_Size size) {
		I_Point p = new WPoint(origin.getX()+size.getWidth(), origin.getY()+size.getHeight());
		
		for(double xsteps = origin.getX(); xsteps < p.getX(); ++xsteps) {
			for(double ysteps = origin.getY(); ysteps < p.getY(); ++ysteps) {
				I_Point pn = new WPoint(xsteps, ysteps);
				this.setPixel(null, pn);
			}
		}
	}
	
}
