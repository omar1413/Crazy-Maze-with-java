package entities;

import java.awt.Graphics2D;

public abstract class Entity {
	protected int current;
	protected int x,y;
	
	public Entity(int current){
		this.current = current;
	}
	
	public abstract void render(Graphics2D g2d);
	
	public abstract void update();

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
}
