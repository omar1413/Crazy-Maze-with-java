package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import display.Display;
import graphics.ImageLoader;

public class MenuState extends State {

	private BufferedImage background;
	private BufferedImage single, multi, exit;
	private String gameName = "CRAZY MAZE";
	int font = 30;
	private long last;
	private boolean small = true;
	private int xB, yB, yOffest, widthB, heightB;
	private Handeler handeler;

	private BufferStrategy bs;
	private Graphics g;

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

	public MenuState(int width, int height, String title) {
		super(width, height, title);
		display = new Display(width, height, title);
		background = ImageLoader.loadImage("images/background1.jpg");
		single = ImageLoader.loadImage("images/single.png");
		multi = ImageLoader.loadImage("images/multi.png");
		exit = ImageLoader.loadImage("images/exit.png");
		last = System.currentTimeMillis();
		handeler = new Handeler(this);
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

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		int width = display.getCanvas().getWidth();
		int height = display.getCanvas().getHeight();

		g.drawImage(background, 0, 0, null);

		g.setColor(Color.green);
		g.setFont(new Font("", Font.BOLD, font));
		g.drawString(gameName, 40, 40);

		xB = width / 2 - 100;
		yB = 50;
		yOffest = 150;
		widthB = 200;
		heightB = 80;
		g.drawImage(single, xB, yB, widthB, heightB, null);
		g.drawImage(multi, xB, yB + yOffest, widthB, heightB, null);
		g.drawImage(exit, xB, yB + 2 * yOffest, widthB, heightB, null);
		
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

	public static void main(String[] argv) {
		State menu = new MenuState(300, 500, "CRAZYMAZE");
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

}
