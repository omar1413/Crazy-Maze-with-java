package states;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import display.Display;
import graphics.ImageLoader;

public class Level extends State {

	private BufferedImage background;
	private BufferedImage easy, hard, expert, name;
	private int xB, yB, yOffest, widthB, heightB;
	private HandelerLevel handeler;
	BufferStrategy bs;
	Graphics g;

	public int getxB() {
		return xB;
	}

	public void setxB(int xB) {
		this.xB = xB;
	}

	public int getyB() {
		return yB;
	}

	public int getWidthB() {
		return widthB;
	}

	public int getHeightB() {
		return heightB;
	}

	public Level(int width, int height, String title) {
		super(width, height, title);
		display = new Display(width, height, title);
		background = ImageLoader.loadImage("images/background1.jpg");
		easy = ImageLoader.loadImage("images/easy.png");
		hard = ImageLoader.loadImage("images/hard.png");
		expert = ImageLoader.loadImage("images/expert.png");
		name = ImageLoader.loadImage("images/crazy.png");
		handeler = new HandelerLevel(this);
		display.getCanvas().addMouseListener(handeler);

		new Thread() {
			public void run() {
				while (true) {
					render();
				}
			}
		}.start();
	}

	public void render() {
		int width = display.getCanvas().getWidth();
		int height = display.getCanvas().getHeight();

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.drawImage(background, 0, 0, null);

		g.drawImage(name, 10, 10, null);

		xB = width / 2 - 100;
		yB = 50;
		yOffest = 150;
		widthB = 200;
		heightB = 80;
		g.drawImage(easy, xB, yB, widthB, heightB, null);
		g.drawImage(hard, xB, yB + yOffest, widthB, heightB, null);
		g.drawImage(expert, xB, yB + 2 * yOffest, widthB, heightB, null);

		bs.show();
	}

	public int getyOffest() {
		return yOffest;
	}

	public void setyOffest(int yOffest) {
		this.yOffest = yOffest;
	}

	@Override
	public void update() {

	}

	public static void main(String[] args) {
		new Level(300, 500, "CRAZYMAZE");
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

}
