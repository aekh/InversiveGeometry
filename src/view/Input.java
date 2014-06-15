package view;

import java.io.*;
import java.awt.MouseInfo; 
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Reading input
 * @author Gojs
 *
 */
public class Input implements KeyListener {
	// === Fields === //
	
	private boolean keySave;
	private boolean keyCircle;
	private boolean keyCircleOfInv;
	private boolean keyReset;
	
	// === Constructors === //
	
	public Input() {
		keySave = false;
		keyCircle = false;
		keyCircleOfInv = false;
		keyReset = false;
	}
	
	// === Accessors === //
	
	/** @return the keySave */
	public boolean isKeySave() {return keySave;}
	/** @return the keyCircle */
	public boolean isKeyCircle() {return keyCircle;}
	/** @return the keyCircleOfInv */
	public boolean isKeyCircleOfInv() {return keyCircleOfInv;}
	/** @return the keyReset */
	public boolean isKeyReset() {return keyReset;}
	
	// === Mutators === //
	
	/** @param keySave the keySave to set */
	public void setKeySave(boolean keySave) {this.keySave = keySave;}
	/** @param keyCircle the keyCircle to set */
	public void setKeyCircle(boolean keyCircle) {this.keyCircle = keyCircle;}
	/** @param keyCircleOfInv the keyCircleOfInv to set */
	public void setKeyCircleOfInv(boolean keyCircleOfInv) {
		this.keyCircleOfInv = keyCircleOfInv;}
	/** @param keyReset the keyReset to set */
	public void setKeyReset(boolean keyReset) {this.keyReset = keyReset;}
	
	// === Misc. Methods === //
	
	public model.Point readPoint(javax.swing.JFrame frame) {
		Point mouse = frame.getMousePosition();
		if(mouse == null) {
			mouse = new Point(0, 0);
		} return new model.Point(mouse.x, mouse.y - 24);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(KeyEvent.VK_SPACE == keyCode) {
			setKeySave(true);
		} if(KeyEvent.VK_C == keyCode) {
			setKeyCircle(true);
		} if(KeyEvent.VK_V == keyCode) {
			setKeyCircleOfInv(true);
		} if(KeyEvent.VK_R == keyCode) {
			setKeyReset(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(KeyEvent.VK_SPACE == keyCode) {
			setKeySave(false);
		} if(KeyEvent.VK_C == keyCode) {
			setKeyCircle(false);
		} if(KeyEvent.VK_V == keyCode) {
			setKeyCircleOfInv(false);
		} if(KeyEvent.VK_R == keyCode) {
			setKeyReset(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
