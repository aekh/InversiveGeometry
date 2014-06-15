package backend;

/**
 * Point class
 * @author Gojs
 *
 */

public class Point {
	
	// === Fields === //
	
	protected double originX;
	protected double originY;
	private boolean isAlive;
	
	// === Constructors === //
	
	public Point(double originX, double originY) {
		this.originX = originX;
		this.originY = originY;
		isAlive = true;
	}
	
	// === Accessors === //
	
	/** @return the originX */
	public double getOriginX() {return originX;}
	/** @return the originY*/
	public double getOriginY() {return originY;}
	/** @return the isAlive */
	public boolean isAlive() {return isAlive;}
	
	// === Mutators === //
	
	/** @param originX the originX to set */
	public void setOriginX(double originX) {this.originX = originX;}
	/** @param originY the originY to set */
	public void setOriginY(double originY) {this.originY = originY;}
	/** @param isAlive the isAlive to set */
	public void setAlive(boolean isAlive) {this.isAlive = isAlive;}
	
	// === Misc. Methods === //
	
	public void render(java.awt.Graphics g) {
		g.setColor(new java.awt.Color(0, 127, 127));
		g.fillRect((int)originX-1, (int)originY-1, 3, 3);
	}
}
