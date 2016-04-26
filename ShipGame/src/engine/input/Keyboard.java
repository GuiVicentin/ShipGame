package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	/**
	 * 
	 */
	private static final int NUM_KEYS = 256;
	
	/**
	 * 
	 */
	private boolean[] keys;
	private int[] polled;
	
	/**
	 * 
	 */
	public Keyboard() {
		keys = new boolean[NUM_KEYS];
		polled = new int[NUM_KEYS];
	}
	
	/**
	 * 
	 */
	public void poll() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keys[i]) {
				polled[i]++;
			}
			else {
				polled[i] = 0;
			}
		}
	}
	
	/**
	 * 
	 * @param keyCode
	 * @return
	 */
	public boolean keyDown(int keyCode) {
		return polled[keyCode] > 0;
	}
	
	/**
	 * 
	 * @param keyCode
	 * @return
	 */
	public boolean keyDownOnce(int keyCode) {
		return polled[keyCode] == 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode >=0 && keyCode < NUM_KEYS) {
			keys[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode >=0 && keyCode < NUM_KEYS) {
			keys[keyCode] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
