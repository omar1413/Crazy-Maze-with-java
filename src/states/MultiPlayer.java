package states;

import network.Client;
import network.Server;

public class MultiPlayer {
	public MultiPlayer(int size) {

		Server server = new Server(8888, size);

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Client client = new Client(8888);

	}
}
