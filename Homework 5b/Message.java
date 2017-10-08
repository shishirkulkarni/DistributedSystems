package edu.sjsu.cs249.four;

public class Message {
	MessageType messageType;
	VectorClock vc;
	
	
	public Message(MessageType mt, VectorClock vc) {
		this.messageType=mt;
		this.vc = vc;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + messageType + ", " + vc.toString();
	}
	
	public VectorClock getVc() {
		return vc;
	}
	
}
