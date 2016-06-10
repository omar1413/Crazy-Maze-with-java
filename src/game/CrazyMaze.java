package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.SliderUI;

import audio.Sound;
import display.Display;
import entities.GoalEntity;
import graphics.ImageLoader;
import input.KeyManager;
import network.Client;
import network.ClientInfo;
import player.Player;
import player.Player2;

public class CrazyMaze implements Runnable, Serializable {

	private int size = 5;

	private Display display;
	private boolean running;

	private Player player1;
	private GoalEntity goal;
	private MazeRoute mazeRoute;

	private KeyManager keyManager;

	private Sound tick;

	private Thread run;

	private Graphics g;
	private BufferStrategy bs;
	private Graphics2D g2d;
	private HashMap<Integer, Player2> player2 = new HashMap();

	private long last;

	public CrazyMaze(int width, int height, String title, MazeRoute mazeRoute) {
		tick = new Sound("3.wav");
		display = new Display(width, height, title);
		keyManager = new KeyManager();
		display.getCanvas().addKeyListener(keyManager);
		running = true;

		this.mazeRoute = mazeRoute;
		player1 = new Player(this, 0);
		goal = new GoalEntity(this, mazeRoute.getGoal());

		run = new Thread(this);
		run.start();

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public MazeRoute getMazeRoute() {
		return mazeRoute;
	}

	public Display getDisplay() {
		return display;
	}

	public void run() {

		last = System.currentTimeMillis();
		while (running) {
			render();
			update();

		}
		System.exit(0);
	}

	public void update() {
		player1.update();
	}

	public Player getPlayer1() {
		return player1;
	}

	public synchronized void render() {

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		g2d = (Graphics2D) g.create();

		int width = display.getCanvas().getWidth();
		int height = display.getCanvas().getHeight();
		int n = mazeRoute.getN();
		int cellWidth = width / n;
		int cellHeight = height / n;

		g2d.setColor(new Color(150, 150, 150));
		g2d.fillRect(0, 0, display.getCanvas().getWidth(), display.getCanvas().getHeight());

		long now = System.currentTimeMillis();
		if (now - last >= 1000) {
			tick.PlaySound();
			last = now;
		}

		String time = "" + Client.time;
		if (time.length() == 1) {
			time = "0" + time;
		}

		g2d.setColor(new Color(100, 100, 100));
		int font = height - 30;
		g2d.setFont(new Font("arial", Font.PLAIN, font));
		int fontWidth = g2d.getFontMetrics().stringWidth(time);

		g2d.drawString(time, width / 2 - fontWidth / 2 - 10, height / 2 + fontWidth / 2 - 100);

		goal.render(g2d);

		

		for (Entry<Integer, Player2> player : player2.entrySet()) {

			player.getValue().render(g2d);

		}
		player1.render(g2d);

		wall(n, cellWidth, cellHeight);
		end();

		bs.show();

	}

	public void wall(int n, int cellWidth, int cellHeight) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cur = mazeRoute.getN() * i + j;
				Cell cell = mazeRoute.getCell(cur);

				g2d.setColor(Color.white);

				int x1 = j * cellWidth;
				int x2 = j * cellWidth + cellWidth;
				int y1 = i * cellHeight;
				int y2 = i * cellHeight + cellHeight;
				if (!cell.isConnected((cur + 1))) {

					// g2d.fill3DRect(x2, y1, 5, cellHeight + 5, true);
					g2d.drawLine(x2, y1, x2, y2); // right line
				}
				if (!cell.isConnected((cur - 1))) {

					// g2d.fill3DRect(x1, y1, 5, cellHeight + 5, true);
					g2d.drawLine(x1, y1, x1, y2); // left line
				}
				if (!cell.isConnected(cur - mazeRoute.getN())) {

					// g2d.fill3DRect(x1, y1, cellWidth, 5, true);

					g2d.drawLine(x1, y1, x2, y1);
				}
				if (!cell.isConnected(cur + mazeRoute.getN())) {

					// g2d.fill3DRect(x1, y2, cellWidth, 5, true);

					g2d.drawLine(x1, y2, x2, y2);
				}

			}
		}
	}

	public void end() {

		if (goal.getCurrent() == player1.getCurrent()) {
			BufferedImage img = ImageLoader.loadImage("images/winner.png");
			g.drawImage(img, 10, 10, null);

			bs.show();
			bs.show();
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			running = false;
		}

		else {

			for (Entry<Integer, Player2> player : player2.entrySet()) {

				if (goal.getCurrent() == player.getValue().getCurrent()) {

					BufferedImage img = ImageLoader.loadImage("images/loser.png");
					g.drawImage(img, 10, 10, null);

					bs.show();
					bs.show();
					try {
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					running = false;

				}
			}
		}
	}

	public HashMap<Integer, Player2> getPlayer2() {
		return player2;
	}

	public void setPlayer2(HashMap<Integer, Player2> player2) {
		this.player2 = player2;
	}

	public Graphics getG2d() {
		return g;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public void setMazeRoute(MazeRoute mazeRoute) {
		this.mazeRoute = mazeRoute;
	}

}
