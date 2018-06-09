package ws.exui.uibase;

import ws.exui.uiview.I_View;

/**
 * 一般使用在WView的基础配置上,所有绘图点不超过视图可视区域*/
public class ViewRectClip implements I_Clip {
	private I_View view;
	
	public ViewRectClip(I_View view) {
		this.view = view;
	}
	
	@Override
	public boolean checkPoint(I_Point npoint) {
		I_Size s = this.view.size_GetVisibleSize();
		if(npoint.getX() < s.getWidth() && npoint.getY() < s.getHeight())
			return true;
		return false;
	}

}
