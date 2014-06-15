package model;

/**
 * Circle class
 * @author Gojs
 *
 */
public class Circle extends Point{
	
	// === Fields === //
	
	private double radius;
	
	// === Constructors === //
	
	public Circle(double originX, double originY, double radius) {
		super(originX, originY);
		this.radius = radius;
	}
	
	public Circle(Point origin, double radius) {
		super(origin.originX, origin.originY);
		this.radius = radius;
	}
	
	// === Accessors === //
	
	/** @return the radius */
	public double getRadius() {return radius;}
	
	// === Mutators === //
	
	/** @param radius the radius to set */
	public void setRadius(int radius) {this.radius = radius;}

	// === Misc. Methods === //
	
	public Point toPoint() {
		return new Point(originX, originY);
	}
	
	@Override
	public void render(java.awt.Graphics g) {
		g.setColor(new java.awt.Color(127, 0, 127));
		g.drawOval((int)(originX-radius), (int)(originY-radius), 
						(int)(radius*2), (int)(radius*2));
	}
}
