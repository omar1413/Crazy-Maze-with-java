package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.CrazyMaze;
import graphics.ImageLoader;

public class GoalEntity extends Entity{

	private CrazyMaze crazyMaze;

	public GoalEntity(CrazyMaze crazyMaze, int current) {
		super(current);
		this.crazyMaze = crazyMaze;
	}

	@Override
	public void render(Graphics2D g2d) {
		int cellWidth = crazyMaze.getDisplay().getCanvas().getWidth() / crazyMaze.getMazeRoute().getN();
		int cellHeight = crazyMaze.getDisplay().getCanvas().getHeight() / crazyMaze.getMazeRoute().getN();
		x = current % crazyMaze.getMazeRoute().getN();
		y = current / crazyMaze.getMazeRoute().getN();
		//g2d.setColor(Color.RED);
		
		BufferedImage img=ImageLoader.loadImage("images/door.png");
		g2d.drawImage(img,x* cellWidth , y * cellHeight, cellWidth, cellHeight,null);
		//g2d.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
