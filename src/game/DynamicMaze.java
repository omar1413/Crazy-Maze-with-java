package game;

public class DynamicMaze implements Runnable {

	private long bmil;
	private long amil;
	private MazeRoute mazeRoute;
	private static int time = 30;

	public DynamicMaze(MazeRoute mazeRoute) {

		this.mazeRoute = mazeRoute;
		bmil = amil = System.currentTimeMillis();

		new Thread(this).start();
	}

	public  long getBmil() {
		return bmil;
	}

	public void setBmil(long bmil) {
		this.bmil = bmil;
	}

	public long getAmil() {
		return amil;
	}

	public void setAmil(long amil) {
		this.amil = amil;
	}

	public void run() {
		long bmil, amil;

		bmil = System.currentTimeMillis();

		while (true) {
			amil = System.currentTimeMillis();

			if (amil - bmil >= 1000) {

				if ((time--) == 0) {

					mazeRoute = new MazeRoute(mazeRoute.getN());
					time = 30;
				}
				bmil = amil;

			}
		}

	}

	public static int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public MazeRoute getMazeRoute() {
		return mazeRoute;
	}

}
