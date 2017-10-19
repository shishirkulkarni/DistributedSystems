package edu.sjsu.cs249.six;

import java.util.Observable;

public class MessageBuffer extends Observable {
	private Message message;
	
	public Message getMessage() {
		return message;
	}
	
	public void saveMessage(Message message) {
		this.message = message;
		this.setChanged();
		this.notifyObservers();
	}
	
}
