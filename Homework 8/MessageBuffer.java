package edu.sjsu.cs249.seven;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

public class MessageBuffer extends Observable {
	private Queue<Message> messages;
	
	public MessageBuffer() {
		this.messages= new LinkedList<>();
	}
	
	public Message getMessage() {
		return messages.remove();
	}
	
	public void saveMessage(Message message) {
		this.setChanged();
		this.notifyObservers(message); // Sending message as argument because of concurrency issues for now
	}
	
}
