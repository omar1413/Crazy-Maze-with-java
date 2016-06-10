package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import network.*;
import display.*;
import input.*;
import player.*;
import game.*;

public class Server extends Connection {
	private int port;
	private InetAddress ip;
	private static game.MazeRoute mazeRoute;
	private ClientInfo clientInfo;
	private HashMap<InetAddress, ClientInfo> Clients = new HashMap();
	private Object ob = null;
	private int time = 30;
	private DynamicMaze dm;
	private int id = 0;
	private int size = 5;

	public Server(int port, int size) {
		try {

			mazeRoute = new MazeRoute(size);

			this.port = port;
			socket = new DatagramSocket(port);

			diamond();
			process();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void diamond() {

		new Thread() {

			public void run() {
				while (true) {
					ob = receive();

					if (ob == null) {
						addClient();
						InetAddress ip = null;
						try {
							ip = InetAddress.getLocalHost();
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
						
						send(ip, packetr.getAddress(), packetr.getPort());
						System.out.println(packetr.getAddress());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (Clients.size() == 1) {

							dm = new DynamicMaze(mazeRoute);
						}

					}
					send(dm.getTime(), packetr.getAddress(), packetr.getPort());

					if (ob instanceof int[]) {

						int[] data = (int[]) ob;
						data[3] = Clients.get(packetr.getAddress()).getId();

						sendToAll(ob);
					}
				}
			}

		}.start();

	}

	public void process() {

		new Thread() {

			public void run() {
				while (true) {

					if (dm != null)
						mazeRoute = dm.getMazeRoute();
					sendToAll(mazeRoute);

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}

		}.start();

	}

	public synchronized void addClient() {

		id++;
		clientInfo = new ClientInfo(packetr.getAddress(), packetr.getPort(), id);
		Clients.put(clientInfo.getIp(), clientInfo);

	}

	public synchronized void sendToAll(Object ob) {

		if (ob instanceof MazeRoute) {

			for (Map.Entry<InetAddress, ClientInfo> client : Clients.entrySet()) {
				send(ob, client.getKey(), ((ClientInfo) client.getValue()).getPort());

			}
		}

		else {
			for (Map.Entry<InetAddress, ClientInfo> client : Clients.entrySet()) {

				if (!(packetr.getAddress().equals(client.getKey()))) {

					send(ob, client.getKey(), ((ClientInfo) client.getValue()).getPort());
				}
			}
		}
	}



}
