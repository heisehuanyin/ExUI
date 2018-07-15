package exui.uibase;

public class WPoint implements I_Point {
	private double x = 0;
	private double y = 0;
	
	public WPoint(double x, double y) {this.x= x; this.y = y;}
	
	@Override
	public double getX() {return this.x;}
	@Override
	public double getY() {return this.y;}
	@Override
	public String toString() {
		return "Point:X="+this.x + ",Y=" + this.y + ";";
	}
}
