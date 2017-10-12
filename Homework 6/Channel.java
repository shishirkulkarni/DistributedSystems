package edu.sjsu.cs249.five;
import java.util.Observable;

public class Channel extends Observable {
	private Buffer buffer;
	private String label;
	
	public Channel(String label) {
		this.label = label;
		this.buffer = new Buffer();
	}
	
	public void sendMessage(Message message) {
		this.buffer.saveMessage(message);
		this.setChanged();
		this.notifyObservers(message);
		getMessage();
	}
	
	public Message getMessage() {
		return buffer.getMessage();
	}
	
	public int getCountOfPendingMessages() {
		return buffer.getTotalMessageCount();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return label;
	}
}
