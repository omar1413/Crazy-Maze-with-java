package states;

import java.awt.Graphics;

import display.Display;

public abstract class State {

	protected int width, height, n;
	protected String title;
	protected Display display;
	
	public State(int width, int height, String title){
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	public abstract void render(Graphics g);
	public abstract void update();
}
