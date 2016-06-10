package player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entities.Entity;
import game.Cell;
import game.CrazyMaze;
import graphics.ImageLoader;

public class Player extends Entity {

	private CrazyMaze crazyMaze;

	public Player(CrazyMaze crazyMaze, int current) {
		super(current);
		this.crazyMaze = crazyMaze;
	}

	@Override
	public synchronized void render(Graphics2D g2d) {

		int cellWidth = crazyMaze.getDisplay().getCanvas().getWidth() / crazyMaze.getMazeRoute().getN();
		int cellHeight = crazyMaze.getDisplay().getCanvas().getHeight() / crazyMaze.getMazeRoute().getN();
		x = current % crazyMaze.getMazeRoute().getN();
		y = current / crazyMaze.getMazeRoute().getN();
		

		BufferedImage img = ImageLoader.loadImage("images/p1.png");
		g2d.drawImage(img, x * cellWidth, y * cellHeight, cellWidth, cellHeight, null);

	}

	@Override
	public synchronized void update() {
		Cell cell = crazyMaze.getMazeRoute().getCell(current);
		if (crazyMaze.getKeyManager().left) {
			if (cell.isConnected(current - 1)) {
				current--;
			}
			crazyMaze.getKeyManager().left = false;
		}

		if (crazyMaze.getKeyManager().right) {
			if (cell.isConnected(current + 1)) {
				current++;
			}
			crazyMaze.getKeyManager().right = false;
		}

		if (crazyMaze.getKeyManager().up) {
			if (cell.isConnected(current - crazyMaze.getMazeRoute().getN())) {
				current -= crazyMaze.getMazeRoute().getN();
			}
			crazyMaze.getKeyManager().up = false;
		}

		if (crazyMaze.getKeyManager().down) {
			if (cell.isConnected(current + crazyMaze.getMazeRoute().getN())) {
				current += crazyMaze.getMazeRoute().getN();
			}
			crazyMaze.getKeyManager().down = false;
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[] getdata() {

		int data[] = { x, y, current, 0 };

		return data;
	}

}
