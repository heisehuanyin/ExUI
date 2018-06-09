package ws.exui.uibase;

import java.awt.Color;

import ws.exui.uiview.I_View;

public interface I_GraphicsPort {

	/**
	 * 设置坐标变换单元，在利用绘图端口进行绘图的时候，会自动调用变换单元进行坐标变换
	 * @param trans 变换单元*/
	void setTransform(I_Transform trans);

	/**
	 * 配置图形绘制端口
	 * @param view 拥有本端口的视图*/
	void initGraphicsPort(I_View view);

	/**
	 * 绘制一个像素点，传入颜色值，如果颜色值为null，表明使用本端口已设定颜色
	 * @param c 像素点的颜色
	 * @param point 像素点逻辑坐标*/
	void setPixel(Color c, I_Point point);

	/**
	 * 设置约束区域，对于在此端口上绘制的所有图形进行检查，超出约束区域的图形不予绘制
	 * @param nClip 约束区域*/
	void setClip(I_Clip nClip);

	/**
	 * 绘制一条线段
	 * @param origin 线段的起点
	 * @param end 线段的终点*/
	void drawLine(I_Point origin, I_Point end);

	/**
	 * 绘制一个矩形边框
	 * @param origin 矩形原点
	 * @param size 尺寸*/
	void drawRectangle(I_Point origin, I_Size size);

	/**
	 * 填充一个实体矩形
	 * @param origin 矩形原点
	 * @param size 尺寸*/
	void fillRectangle(I_Point origin, I_Size size);

	/**
	 * 设置绘制和填充的颜色
	 * @param c 指定的颜色*/
	void setColor(Color c);

}
