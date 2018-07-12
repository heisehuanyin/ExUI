package ws.exui.uibase;

import java.awt.geom.AffineTransform;

public interface I_Transform {
	/**
	 * 获得Transform单元中状态数据，替代View中的空白状态
	 * @return 既定的状态数据*/
	AffineTransform getAffineTransform();
}
