package states;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import game.CrazyMaze;
import game.DynamicMaze;
import game.MazeRoute;
import network.Client;

public class SingleGame {
	private int time;
	private Thread thread;
	private MazeRoute mazeRoute;
	private DynamicMaze dynamicMaze;
	private CrazyMaze crazyMaze;

	public SingleGame(int n, String level) {

	     mazeRoute = new MazeRoute(n);
	     crazyMaze = new CrazyMaze(700, 700, level, mazeRoute);
		 dynamicMaze = new DynamicMaze(mazeRoute);
		

		new Thread() {
			public void run() {

				while (true) {
					Client.time = dynamicMaze.getTime();

                   try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					crazyMaze.setMazeRoute(dynamicMaze.getMazeRoute());
				}
			}
		}.start();

	}

}
