package edu.sjsu.cs249.seven;

public class Message {
	private Processor sender, forwarder;
	private int value;
	private MessageType messageType;
	private int hopCount;
	private MessageDirection dir;

	public Message(Processor sender, int value, MessageType messageType, MessageDirection dir, int hopCount) {
		this.sender = sender;
		this.value = value;
		this.messageType = messageType;
		this.hopCount = hopCount;
		this.dir = dir;
	}
	
	public Message(Message m) {
		this(m.getSender(), m.getValue(), m.getMessageType(), m.getDir(), m.getHopCount());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<" + this.sender + ", " + this.value + ", " + this.messageType + ", " + this.dir + ">";
	}
	
	public Processor getSender() {
		return sender;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setMessageType(MessageType type) {
		this.messageType = messageType;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	
	public void setHopCount(int hopCount) {
		this.hopCount = hopCount;
	}
	
	public int getHopCount() {
		return hopCount;
	}
	
	public MessageDirection getDir() {
		return dir;
	}
	
	public void toggleMessageType() {
		messageType = messageType == MessageType.PROBE ? MessageType.REPLY: MessageType.PROBE;
	}
	
	public void reverseDirection() {
		dir = dir == MessageDirection.LEFT ? MessageDirection.RIGHT: MessageDirection.LEFT;
	}
	
	public Processor getForwarder() {
		return forwarder;
	}
	
	public void setForwarder(Processor forwarder) {
		this.forwarder = forwarder;
	}
}
