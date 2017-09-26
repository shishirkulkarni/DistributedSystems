package edu.sjsu.cs249.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Processor extends Observable implements Observer {
	private int val;
	private List<Processor> children;
	private Processor parent;
	private int counter; // maintain message count assuming single message by every child
	String sequence, tempSequence;
	
	public Processor() {
		this.children = null;
		this.val = -1; // Assuming all values are going to be positive
		this.parent = null;
		this.addObserver(this);//Every processor observes itself
		this.counter = 0; // No messages received initially
		this.sequence = "";
		this.tempSequence = ""; //Store the child sequence here
	}
	
	public Processor(int val) {
		this();
		this.val = val;
		this.children = new ArrayList<>();
	}
	
	public List<Processor> getChildern() {
		return children;
	}
	
	public void setParent(Processor parent) {
		this.parent = parent;
	}
	
	public Processor getParent() {
		return parent;
	}
	
	public String toString() {
		return "Value: " + this.val + "\nSequence: " + sequence;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(this.isFirstMessage()) { // getting a message first time, store my current val to sequence
			this.sequence = "" + this.val;
		}
		
		this.counter++;
		
		//Building sequence
		this.tempSequence = this.tempSequence + ((Processor)arg).sequence + ", ";
		
		// Root is not going to send message to its parent. Hence root sequence needs to be handled here
		if(this.isRoot() && this.isMessageFromLastChild()) {
			this.sequence = this.tempSequence + this.sequence;
		}
		
		//Update the value
		this.val = Math.max(this.val, ((Processor)arg).getVal());

		if(!this.isRoot() && (this.isLeaf() || this.isMessageFromLastChild())) {
			
			// Update the sequence
			if(!this.isLeaf()) {
				this.sequence = this.tempSequence + this.sequence ;	
			}
			
			this.tempSequence = "";
			this.parent.sendMessageToMyBuffer(this);
			this.counter = 0;
		}
		
	}
	
	public void sendMessageToMyBuffer(Processor sender) {
		setChanged();
		notifyObservers(sender);
	}
	
	public int getVal() {
		return val;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public boolean isLeaf() {
		return this.getChildern().size() == 0;
	}
	
	public boolean isRoot() {
		return this.parent == null;
	}
	
	public boolean isMessageFromLastChild() {
		return this.counter == this.getChildern().size();
	}
	
	public boolean isFirstMessage() {
		return this.counter == 0;
	}
	
}
