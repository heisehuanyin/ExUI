package ws.exui.uiview;

import ws.exui.binding.I_Object4BindingCommon;

public interface I_UIBuilder<E extends I_Object4BindingCommon> {
	I_View generateViewUnitAs(E d_unit);
}
