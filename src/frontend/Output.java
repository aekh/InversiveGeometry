package frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Graphically drawing output
 * @author Gojs
 *
 */
public class Output extends JPanel{
	// === Fields === //
	
	private static final long serialVersionUID = 6343688028943429454L;
	private LinkedList<backend.Point> objects;
	public JFrame frame;
	
	// === Constructors === //
	
	public Output(java.awt.event.KeyListener input) {
		frame = new JFrame("Inversive Geometry");
		frame.pack();
		frame.add(this);
		frame.addKeyListener(input);
		//frame.addKeyListener(input);
		frame.setSize(800, 800);
		frame.setVisible(true);

		//GraphicsEnvironment.getLocalGraphicsEnvironment().
	//	getDefaultScreenDevice().setFullScreenWindow(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		objects = new LinkedList<>();
	}
	
	// === Accessors === //
	// === Mutators === //
	
	/** @param object Object to add to render list */
	public void addObject(backend.Point object) {
		synchronized(objects) {
			objects.add(object);
			}
		}
	public void remObject(backend.Point object) {
		synchronized(objects) {
			objects.remove(object);
			}
		}
	public void clearObjects() {
		synchronized(objects) {
			objects.clear();
		}
	}
		
	// === Misc. Methods === //
	
	/** Paints the Graphics
	 * @param g The Graphics we paint to
	 */
	@Override
	public void paint(Graphics g) {
		synchronized(objects) {
			g.setColor(new Color(200,200,200,255));
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			for (int i = 0; i < objects.size(); i++) {
				backend.Point p = objects.get(i);
				p.render(g);
			}
			g.setColor(new Color(0, 50, 110, 255));
			g.drawString("Space: Attach shape(s)", 10, 20);
			g.drawString("C: Create cirlce", 10, 35);
			g.drawString("V: Create cirlce of inversion", 10, 50);
			g.drawString("R: Clear screen", 10, 65);
		}
	}
}
