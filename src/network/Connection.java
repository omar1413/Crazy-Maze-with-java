package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

import javax.xml.crypto.Data;

import network.*;
import display.*;
import input.*;
import player.*;
import game.*;

public class Connection {

	protected DatagramSocket socket;
	protected DatagramPacket packets;
	protected DatagramPacket packetr;

	protected ByteArrayOutputStream out;
	protected ObjectOutputStream os;

	protected ObjectInputStream is;
	protected ByteArrayInputStream in;

	public void send(Object ob, InetAddress ip, int port) {

		try {

			out = new ByteArrayOutputStream();
			os = new ObjectOutputStream(out);
			os.writeObject(ob);

			byte[] data = out.toByteArray();
			packets = new DatagramPacket(data, data.length, ip, port);
			socket.send(packets);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object receive() {

		Object ob = null;
		try {
			byte[] data = new byte[81920];
			packetr = new DatagramPacket(data, data.length);

			socket.receive(packetr);

			in = new ByteArrayInputStream(packetr.getData());
			is = new ObjectInputStream(in);
			ob = is.readObject();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return ob;
	}
}
