package ws.exui.event;

import ws.exui.uiview.I_View;

public interface I_UIExEvent extends I_ExEvent{
	I_View getSource();
}
