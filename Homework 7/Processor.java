package edu.sjsu.cs249.six;

import java.util.Observable;
import java.util.Observer;

public class Processor implements Observer {
	private int id, value;
	private Processor left, right;
	private MessageBuffer buffer;
	private boolean isLeader;
	
	public Processor(int id, int value) {
		this.id = id;
		this.value = value;
		isLeader = false;
		this.buffer = new MessageBuffer();
		this.buffer.addObserver(this);
	}
	
	public void setNeighbous(Processor left, Processor right) {
		this.left = left;
		this.right = right;
	}
	
	public void sendMessageToMyBuffer(Message m) {
		buffer.saveMessage(m);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + id;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Message m = this.buffer.getMessage();
		Processor sender = m.getSender();
		System.out.println("Processor " + this + " got a " + m.getType() + " message from " + m.getSender() + " with a value of " + m.getValue());
				
		if(m.getSender() == this && !isLeader) { // If you get your own message, you are the leader!!!
			isLeader = true;
			left.sendMessageToMyBuffer(new Message(this, value, MessageType.TERMINATE));
			System.out.println("-------------------------------------------");
			System.out.println("Processor " + this + " has elected itself as leader!!!");
		}
		
		if(value >= m.getValue()) { // Consume the message
			if(m.getType() == MessageType.TERMINATE) {
				isLeader = false;
				return;
			}
			return;
		}
		
		
//		try {
			left.sendMessageToMyBuffer(m);
//		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public int getId() {
		return id;
	}
	
	public int getValue() {
		return value;
	}
	
	public Processor getRight() {
		return right;
	}
	
	public Processor getLeft() {
		return left;
	}
}
