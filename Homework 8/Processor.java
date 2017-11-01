package edu.sjsu.cs249.seven;

import java.util.Observable;
import java.util.Observer;

public class Processor implements Observer {
	private int id, value;
	private Processor left, right;
	private MessageBuffer buffer;
	private boolean didWin, active;
	private boolean leftReply, rightReply;
	
	public Processor(int id, int value) {
		this.id = id;
		this.value = value;
		this.didWin = false; // initially all processors are participating in the algorithm
		this.active = true;
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
		Message m = (Message)arg;
				
		//Consume message if active
		if(m.getValue() < value && active) {
			System.out.println(this + " consuming message from " + m.getSender());
			return;
		}
		
		if(m.getMessageType() == MessageType.REPLY && m.getSender() == this) {
			if(m.getForwarder() == left) {
				System.out.println(this + " got reply from " + m.getForwarder());
				leftReply = true;
			} else if(m.getForwarder() == right) {
				System.out.println(this + " got reply from " + m.getForwarder());
				rightReply = true;
			}
			
			didWin = leftReply && rightReply;

			return;
		}
		
		
		if(m.getMessageType() == MessageType.PROBE) {
			m.setHopCount(m.getHopCount() - 1);
			
			//Start replying
			if(m.getHopCount() == 0) {
				m.reverseDirection();
				m.toggleMessageType();
			}
			
			m.setForwarder(this);
			
			if(m.getDir() == MessageDirection.LEFT) {
				left.sendMessageToMyBuffer(m);
			} else {
				right.sendMessageToMyBuffer(m);
			}
		} else {
			//Check if its an intermmediate reply
			m.setForwarder(this);
			
			if(m.getDir() == MessageDirection.LEFT) {
				left.sendMessageToMyBuffer(m);
			} else {
				right.sendMessageToMyBuffer(m);
			}
		}
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
	
	public boolean won() {
		return didWin;
	}
	
	public void resetReplyFlags() {
		leftReply = false;
		rightReply = false;
		didWin = false;
	}
	
	public void setInactive() {
		this.active = false;
	}
	
}
