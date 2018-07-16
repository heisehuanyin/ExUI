package exui.uibuilder;

import exui.binding.I_Object4BindingCommon;
import exui.uiview.I_View;

public interface I_ViewBuilder<E extends I_Object4BindingCommon> {
	I_View generateViewUnitAs(E d_unit);
}
