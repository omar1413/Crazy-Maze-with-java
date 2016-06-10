package game;

import java.io.Serializable;
import java.util.ArrayList;


public class Cell implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private boolean visited;
	private int number;
	private ArrayList<Integer> connections;
	
	public Cell(int number) {
		this.number = number;
		this.visited = false;
		this.connections = new ArrayList<Integer>();
	
	}
	
	public void addConnection(int number) {
		this.connections.add(number);
	}
	
	public boolean isConnected(int number){
		return this.connections.contains(number);
	}
	
	public void visit() {
		this.visited = true;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	
}
