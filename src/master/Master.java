package master;

import backend.Circle;
import backend.Point;
import frontend.Input;
import frontend.Output;

/**
 * Master loop
 * @author Gojs
 *
 */
public class Master {
	// === Fields === //
	
	private Input input;
	private Output output;
	private Circle circleOfInv;
	
	// === Constructors === //
	
	public Master() {
		input = new Input();
		output = new Output(input);
	}
	
	// === Accessors === //
	// === Mutators === //
	// === Misc. Methods === //
	public void run() {
		circleOfInv = new Circle(400, 400, 100);
		
		output.addObject(circleOfInv);
		
		while(true) {
			if(input.isKeyReset()) {
				output.clearObjects();
				output.addObject(circleOfInv);
			} else if(input.isKeyCircleOfInv()) {
				// Read center of circle
				Point origin = input.readPoint(output.frame);
				while(input.isKeyCircleOfInv()) {
					// Read current point
					Point point = input.readPoint(output.frame);
					
					// Create circle
					double radius = distance(origin, point);
					Circle newCircleOfInv = new Circle(origin, radius);
					
					output.addObject(newCircleOfInv);
					
					// Repaint
					output.repaint();
					
					if(input.isKeySave()) {
						output.remObject(circleOfInv);
						circleOfInv = newCircleOfInv;
					} else {
						output.remObject(newCircleOfInv);
					}
				}
			} else if(!input.isKeyCircle()) {
				// Read point
				Point point = input.readPoint(output.frame);
				
				output.addObject(point);
				
				// Find inverted point
				double OtoP = distance(circleOfInv.toPoint(), point);
				double OtoP_ = invDist(OtoP, circleOfInv.getRadius());
				Point invPoint = tracePoint(circleOfInv.toPoint(), point,
						OtoP, OtoP_);
				
				output.addObject(invPoint);
				
				// Repaint
				output.repaint();
				
				
				// Remove or save 
				if(!input.isKeySave()) {
					output.remObject(point);
					output.remObject(invPoint);
				}
			} else {
				// Read center of circle
				Point origin = input.readPoint(output.frame);
				while(input.isKeyCircle()) {
					// Read current point
					Point point = input.readPoint(output.frame);
					
					// Create circle
					double radius = distance(origin, point);
					Circle circle = new Circle(origin, radius);
				
					output.addObject(circle);
					
					// Find inverted circle
					Point closest = findClosestPointOnCircle(circle, 
							circleOfInv.toPoint());
					double closestDist = distance(circleOfInv.toPoint(),
							closest);
					
					// If circle of inversions origin is inside
					// the circle
					
					double farthestDist = 0;
					if(distance(circle.toPoint(), circleOfInv.toPoint())
							< circle.getRadius()) {
						farthestDist = -2 * circle.getRadius();
					} else {
						farthestDist = 2 * circle.getRadius();
					}
					
					Point farthest = tracePoint(circleOfInv.toPoint(), 
							closest, closestDist, 
							closestDist + farthestDist);
					Point invClosest = invertPoint(closest, circleOfInv);
					Point invFarthest = invertPoint(farthest, circleOfInv);
					
					double invRadius = distance(invClosest, invFarthest)/2;
					Point invOrigin = tracePoint(invClosest, invFarthest, 
							2*invRadius, invRadius);
					Circle invCircle = new Circle(invOrigin, invRadius);
					
					output.addObject(invCircle);
					
					// Repaint
					output.repaint();
					
					// Remove or save 
					if(!input.isKeySave()) {
						output.remObject(circle);
						output.remObject(invCircle);
					}
				}
			}
		}
	}
	
	public Point invertPoint(Point a, Circle circleOfInv) {
		double OtoP = distance(circleOfInv.toPoint(), a);
		double OtoP_ = invDist(OtoP, circleOfInv.getRadius());
		Point invPoint = tracePoint(circleOfInv.toPoint(), a,
				OtoP, OtoP_);
		return invPoint;
	}
	
	public double distance(Point a, Point b) {
		return Math.sqrt(Math.pow((b.getOriginX() - a.getOriginX()), 2) 
				+ Math.pow((b.getOriginY() - a.getOriginY()), 2));
	}
	
	public double invDist(double distance, double radius) {
		return Math.pow(radius, 2) / distance;
	}
	
	/**
	 * Solves the unknown point c.
	 * If points a and b is known and point c is unknown,
	 * distances from a to b, and from a to c is known and
	 * the vector from a to c is a non-negative linear combination of 
	 * the vector from a to b.
	 * 
	 * @param a Known point a
	 * @param b Known point b
	 * @param abDist Distance from a to b
	 * @param distance Distance from a to unknown point c
	 * @return Returns point c
	 */
	public Point tracePoint(Point a, Point b, double abDist, double distance) {
		double ax = a.getOriginX(); double ay = a.getOriginY();
		double bx = b.getOriginX(); double by = b.getOriginY();
		
		return new Point(ax + (distance/abDist)*(bx - ax),
				ay + (distance/abDist)*(by - ay));
	}
	
	public Point findClosestPointOnCircle(Circle c, Point a) {
		double cx = c.getOriginX(); double cy = c.getOriginY();
		double radius = c.getRadius();
		double ax = a.getOriginX(); double ay = a.getOriginY();
		
		double vx = ax - cx;
		double vy = ay - cy;
		double magV = Math.sqrt(vx*vx + vy*vy);
		double closestX = cx + vx / magV * radius;
		double closestY = cy + vy / magV * radius;
		
		return new Point(closestX, closestY);
	}
}
