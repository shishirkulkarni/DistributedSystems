package edu.sjsu.cs249.six;

public class Message {
	private Processor sender;
	private int value;
	private MessageType type;

	public Message(Processor sender, int value, MessageType type) {
		this.sender = sender;
		this.value = value;
		this.type = type;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<" + this.sender + ", " + this.value + ">";
	}
	
	public Processor getSender() {
		return sender;
	}
	
	public int getValue() {
		return value;
	}
	
	public MessageType getType() {
		return type;
	}
}
