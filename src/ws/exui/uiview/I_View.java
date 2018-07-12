package ws.exui.uiview;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import ws.exui.event.I_UIExEvent;
import ws.exui.uibase.I_GraphicsPort;
import ws.exui.uibase.I_Margin;
import ws.exui.uibase.I_Point;
import ws.exui.uibase.I_Size;
import ws.exui.uibase.I_Transform;
import ws.exui.uibase.WGraphicsPort;

public interface I_View {

	/**
	 * 获取基本尺寸
	 * @return 返回的基础尺寸实例*/
	I_Size size_GetBasicSize();

	/**
	 * 设置可视化尺寸
	 * @param vSize 新的可视化尺寸*/
	void size_SetVisibleSize(I_Size vSize);

	/**
	 * 获取当前可视化尺寸
	 * @return 返回的可视化尺寸*/
	I_Size size_GetVisibleSize();

	/**
	 * 设置视图的外边距
	 * @param nMargin 新的外边界*/
	void margin_SetMargin(I_Margin nMargin);

	/**
	 * 获取视图的外边距
	 * @return 返回当前视图的外边距*/
	I_Margin margin_GetMargin();

	/**
	 * 如果原点发生变化，设置原点，此原点是父视图的逻辑坐标点
	 * @param origin 新的原点值*/
	void point_SetOriginPoint(I_Point origin);

	/**
	 * 获取当前视图的原点值，此原点值利用父视图的逻辑坐标点表示
	 * @return 视图的原点*/
	I_Point point_GetOriginPoint();

	/**
	 * 在特定位置插入视图
	 * @param view 待插入视图
	 * @param index 位序*/
	void view_InsertViewAtIndex(I_View view, int index);

	/**
	 * 获取子视图的数量
	 * @return 数量*/
	int view_GetChildCount();

	/**
	 * 获取特定位序上的视图
	 * @param index 位序
	 * @return 返回视图*/
	I_View view_GetChildAtIndex(int index);

	/**
	 * 移除此视图下的特定子视图
	 * @param view 特定视图*/
	void view_RemoveViewAtIndex(I_View view);

	/**
	 * 设置坐标变换单元，在利用绘图端口进行绘图的时候，会自动调用变换单元进行坐标变换
	 * @param trans 变换单元*/
	void transform_SetTransform(I_Transform trans);

	/**
	 * 获取Transform单元
	 * @return 本单元的Transform*/
	AffineTransform transform_GetTransformInner();
	
	
	/**
	 * 获取父视图的图形绘制端口，用于递归绘制子视图
	 * @return 返回的父视图接口*/
	I_GraphicsPort gport_getGraphicsPort();

	/**
	 * 设置长度宽度是否自适应，默认是自适应的
	 * @param width 宽度自适应
	 * @param height 高度自适应*/
	void adjust_SetAutoAdjust(boolean width, boolean height);

	/**
	 * 检测是否宽度自适应
	 * @return 结果*/
	boolean adjust_IsAutoWidth();

	/**
	 * 检测是否高度自适应
	 * @return 结果*/
	boolean adjust_IsAutoHeight();

	/**
	 * 设置组件颜色
	 * @param c 指定颜色颜色*/
	void color_SetColor(Color c);
	/**
	 * 获取组件颜色
	 * @return 组件颜色*/
	Color color_GetColor();


	/**
	 * 通过外部组件接口调用，重绘组件*/
	void operate_RePaint();

	/**
	 * 重置View状态为新鲜
	 * @param isfresh 新鲜标志*/
	void fresh_SetFresh(boolean isfresh);
	/**
	 * 测试状态是否新鲜
	 * @return 新鲜与否*/
	boolean fresh_IsFresh();
	/**
	 * 配送UI事件，从子视图一路上溯
	 * @param e UI事件*/
	void event_DispatchUIEvent(I_UIExEvent e);

	/**
	 * 获取clip图形
	 * @return 层层处理获取到的可见图形区域*/
	Area clip_getClipShape();
	
	
	/**
	 * 重设子视图尺寸，此方法为内部方法，不建议外部调用*/
	void __operate_ResizeSubviewSize();

	/**
	 * 获取次视图的父视图
	 * @return 父视图实例*/
	I_View __view_GetParentView();

	/**
	 * 内部函数，设置父视图
	 * @param wView 父视图*/
	void __view_SetParentView(WView wView);

	void __paintItSelf(I_GraphicsPort port);

	/**
	 * 设置基本尺寸
	 * @param bSize 基础尺寸*/
	void size_SetBasicSize(I_Size bSize);

}
