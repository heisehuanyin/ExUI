package ws.exui.uibase;

public class WSize implements I_Size {
	private double width = 0;
	private double height = 0;

	public WSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}
	
	@Override
	public String toString() {
		return "Size:width="+ this.width + ",height=" + this.height + ";";
	}
	
}
