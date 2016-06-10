package network;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.HashMap;

import network.*;
import display.*;
import input.*;
import player.*;
import game.*;

import game.*;

public class Client extends Connection {

	public InetAddress ipServer;
	public int portServer;
	private game.MazeRoute mazeRoute;
	private CrazyMaze crazyMaze;
	private Object ob = null;

	public static int time;

	public Client(int portServer) {

		try {
			this.portServer = portServer;
			socket = new DatagramSocket();
			/*
			 * try { Thread.sleep(200); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */

			respnse();
			//Thread.sleep(200);
			request();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public void request() {
		new Thread() {
			public void run() {
				try {
					send(null, InetAddress.getByName("255.255.255.255"),
							portServer);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while (true) {
					if (crazyMaze != null) {
						send(crazyMaze.getPlayer1().getdata(), ipServer,
								portServer);
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void respnse() {
		new Thread() {
			public void run() {
				boolean run_first = true;

				while (true) {

					ob = receive();

					if (ob instanceof InetAddress) {
						ipServer = (InetAddress) ob;

					}

					else if (ob instanceof MazeRoute) {

						mazeRoute = (MazeRoute) ob;

						if (run_first) {
							crazyMaze = new CrazyMaze(500, 500, "crazyMaze",
									mazeRoute);
							run_first = false;
						} else {
							crazyMaze.setMazeRoute(mazeRoute);
						}

					} else if (ob instanceof int[] && crazyMaze != null) {

						int[] data = (int[]) ob;

						if (crazyMaze.getPlayer2().get(data[3]) == null)
							crazyMaze.getPlayer2().put(data[3],
									new Player2(crazyMaze, -1));
						crazyMaze.getPlayer2().get(data[3]).setX(data[0]);
						crazyMaze.getPlayer2().get(data[3]).setY(data[1]);
						crazyMaze.getPlayer2().get(data[3]).setCurrent(data[2]);
					}

					else if (ob instanceof Integer) {
						time = (int) ob;

					}
				}

			}
		}.start();

	}

	public InetAddress getIpServer() {
		return ipServer;
	}

	public int getPortServer() {
		return portServer;
	}

}