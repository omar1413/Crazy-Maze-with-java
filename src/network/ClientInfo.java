package network;

import java.net.InetAddress;

public class ClientInfo {
	private InetAddress ip;
	private int port;
	private int id;
	

	public ClientInfo(InetAddress ip, int port,int id){
		this.ip = ip;
		this.port = port;
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public InetAddress getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	
	
}
