package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {
	private boolean keys[];
	public boolean up, down, right, left;

	public KeyManager() {
		keys = new boolean[256];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		tick();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		tick();
	}

	public void tick() {
		if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) up = true;
		else up = false;
		if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) left = true;
		else left = false;
		if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) down = true;
		else down = false;
		if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) right = true;
		else right = false;
	}
}
