package player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entities.Entity;
import game.CrazyMaze;
import graphics.ImageLoader;

public class Player2 extends Entity {

	
	private  CrazyMaze crazyMaze;
	public Player2(CrazyMaze crazyMaze, int current) {
		super(current);
		this.x=-1;
		this.y=-1;
		this.crazyMaze = crazyMaze;
	}

	public  void render(Graphics2D g2d ) {
		
		int cellWidth = crazyMaze.getDisplay().getCanvas().getWidth() / crazyMaze.getMazeRoute().getN();
		int cellHeight = crazyMaze.getDisplay().getCanvas().getHeight() / crazyMaze.getMazeRoute().getN();
		BufferedImage img=ImageLoader.loadImage("images/p2.png");
		g2d.drawImage(img,x * cellWidth , y * cellHeight, cellWidth, cellHeight,null);

	}


	
	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	

	public void update() {
		
	}

	

}
