package ws.exui.uibase;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Area;

public interface I_GraphicsPort {

	/**
	 * 绘制一条线段
	 * @param origin 线段的起点
	 * @param end 线段的终点*/
	void drawLine(I_Point origin, I_Point end);

	/**
	 * 绘制一个图形边框
	 * @param s 图形
	 * @param c TODO*/
	void drawShape(Shape s, Color c);

	/**
	 * 填充一个实体图形
	 * @param s 图形
	 * @param c TODO*/
	void fillShape(Shape s, Color c);
	
	/**
	 * 在指定坐标绘制文字，待完善。
	 * @param s 内容
	 * @param point 坐标位置
	 * @param format 格式
	 * @param clipShape 可视区域
	 * */
	void drawString(String s, I_Point point, Font format, Area clipShape);
	
}
