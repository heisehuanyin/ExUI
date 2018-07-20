package exui.uibuilder;

import exui.binding.I_Object4BindingCommon;
import exui.uiview.I_View;

public interface I_ViewBuilder<E extends I_Object4BindingCommon> {
	/**
	 * 构建一个视图单元，基本上作为自动调用部分
	 * @param view2Res 父视图，用于获取已有的注册资源
	 * @return 构造好的视图单元*/
	I_View generateViewUnitAs(I_View view2Res);
}
