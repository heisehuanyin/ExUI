package ws.exui.uibase;

public class WMargin implements I_Margin {
	private int leftSpace = 0;
	private int rightSpace = 0;
	private int topSpace = 0;
	private int bottomSpace = 0;
	
	public WMargin(int left, int right, int top, int bottom) {
		this.leftSpace=left;
		this.rightSpace = right;
		this.topSpace = top;
		this.bottomSpace = bottom;
	}
	
	@Override
	public int getLeftSpace() {
		return this.leftSpace;
	}

	@Override
	public int getRightSpace() {
		return this.rightSpace;
	}

	@Override
	public int getTopSpace() {return this.topSpace;}

	@Override
	public int getBottomSpace() {return this.bottomSpace;}
	
	@Override
	public String toString() {
		return "Margin:left="+this.leftSpace + ",right=" + this.rightSpace +
				",top=" + this.topSpace + ",bottom=" + this.bottomSpace + ";";
	}
}
