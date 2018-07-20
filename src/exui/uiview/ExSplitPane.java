package exui.uiview;

import java.awt.geom.Rectangle2D;

import exui.binding.I_Object4BindingCommon;
import exui.uibase.I_GraphicsPort;
import exui.uibase.I_Point;
import exui.uibase.I_Size;
import exui.uibase.WPoint;
import exui.uibase.WSize;

public class ExSplitPane extends ExView {
	public final static int HORIZONTAL = 0;
	public final static int VERTICAL = 1;
	
	private int oriented = HORIZONTAL; 
	
	public ExSplitPane(int oriented) {
		this.oriented = oriented;
	}
	
	@Override
	public void __operate_ResizeSubviewSize() {
		int count = this.view_GetChildCount();
		
		if(this.oriented == HORIZONTAL) {
			int adjnum = 0;
			double basicWidths = 0;
			
			//统计basicWidth总占用，统计AutoWidth==ture设置子视图数量
			for(int i=0;i<count;++i) {
				I_View one = this.view_GetChildAtIndex(i);
				if(one.adjust_IsAutoWidth()) {
					++adjnum;
				}
				basicWidths += one.size_GetBasicSize().getWidth() + 
						one.margin_GetMargin().getLeftSpace() + one.margin_GetMargin().getRightSpace();
			}
			//获取组件可调空间
			double voidWidth = this.size_GetVisibleSize().getWidth() - basicWidths;
			double everyAdj = voidWidth / adjnum;
			double offSet = 0;
			
			//调整每个组件的尺寸
			for(int i=0;i<count;++i) {
				I_View one = this.view_GetChildAtIndex(i);
				if(one.adjust_IsAutoWidth()) {
					I_Size vSize = new WSize(one.size_GetBasicSize().getWidth() + everyAdj,
							one.size_GetVisibleSize().getHeight());
					one.size_SetVisibleSize(vSize);
				}
				I_Size sizev = new WSize(one.size_GetVisibleSize().getWidth(),
						this.size_GetVisibleSize().getHeight() - one.margin_GetMargin().getTopSpace() - 
						one.margin_GetMargin().getBottomSpace());
				one.size_SetVisibleSize(sizev);
				
				offSet += one.margin_GetMargin().getLeftSpace();
				I_Point point = new WPoint(offSet ,one.margin_GetMargin().getTopSpace());
				one.point_SetOriginPoint(point);
				offSet += one.size_GetVisibleSize().getWidth() + one.margin_GetMargin().getRightSpace();
			}
			
		}else {
			int adjnum = 0;
			double basicHeights = 0;
			for(int i=0;i<count;++i) {
				I_View one = this.view_GetChildAtIndex(i);
				if(one.adjust_IsAutoHeight()) {
					++adjnum;
				}
				basicHeights += one.size_GetBasicSize().getHeight()+
						one.margin_GetMargin().getTopSpace() + one.margin_GetMargin().getBottomSpace();
			}
			double voidHeight = this.size_GetVisibleSize().getHeight() - basicHeights;
			double everyAdj = voidHeight / adjnum;
			double offSet = 0;
			for(int i=0; i< count; ++i) {
				I_View one = this.view_GetChildAtIndex(i);
				if(one.adjust_IsAutoHeight()) {
					I_Size vSize = new WSize(one.size_GetVisibleSize().getWidth(),
							one.size_GetBasicSize().getHeight() + everyAdj);
					one.size_SetVisibleSize(vSize);
				}
				I_Size sizel = new WSize(this.size_GetVisibleSize().getWidth() 
						- one.margin_GetMargin().getLeftSpace() - one.margin_GetMargin().getRightSpace(),
						one.size_GetVisibleSize().getHeight());
				
				one.size_SetVisibleSize(sizel);
				
				offSet += one.margin_GetMargin().getTopSpace();
				I_Point point = new WPoint(one.margin_GetMargin().getLeftSpace(), offSet);
				one.point_SetOriginPoint(point);
				offSet += one.size_GetVisibleSize().getHeight() + one.margin_GetMargin().getBottomSpace();
			}
		}
	}

	@Override
	public void __operate_paintItSelf(I_GraphicsPort port) {
		Rectangle2D.Double varea = new Rectangle2D.Double(0, 0,
				this.size_GetVisibleSize().getWidth(), this.size_GetVisibleSize().getHeight());
		
		port.fillShape(varea, null);
		
	}



}
