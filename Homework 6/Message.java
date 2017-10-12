package edu.sjsu.cs249.five;


public class Message {
	MessageType messageType;
	Processor from; //source

	public Message(MessageType mt) {
		this.messageType=mt;
	}

	public Message(MessageType type, Processor from) {
		this(type);
		this.from = from;
	} 
	
	/**
	 * THe processor that is sending a message
	 * @return
	 */
	public Processor getFrom() {
		return from;
	}

	public void setFrom(Processor from) {
		this.from = from;
	}
	

	public MessageType getMessageType() {
		return messageType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + this.messageType + " " + this.from;
	}
}
