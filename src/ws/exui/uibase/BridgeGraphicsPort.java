package ws.exui.uibase;

import java.awt.Color;
import java.awt.image.WritableRaster;

/**
 * 作为窗口使用的特殊绘制端口，需要传入一个图片绘制接口，将像素点全部绘制到缓冲图片上*/
public class BridgeGraphicsPort extends WGraphicsPort {
	private WritableRaster port = null;
	
	public BridgeGraphicsPort(WritableRaster port) {
		this.port = port;
	}
	
	@Override
	protected void setPixelInner(Color c, I_Point point) {
		double[] dArray = {c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()};
		port.setPixel((int)point.getX(), (int)point.getY(), dArray);
	}
}
