package exui.event;

import exui.uiview.I_View;

public interface I_UIExEvent extends I_ExEvent{
	@Override
	I_View getPrimarySource();
}
