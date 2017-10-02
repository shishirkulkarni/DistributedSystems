package edu.sjsu.cs249.one;

import java.util.Observable;

/**
 * Created by tphadke on 8/29/17.
 */
public class Buffer extends Observable {
    private Message message;
    private Processor sender;

    public Buffer(){
        //Create an empty Buffer
	this.message = null;
	this.sender = null;
    }
    
    public Buffer(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

	public void setMessage(Message message, Processor sender) {
		this.message = message;
		this.sender = sender;
		setChanged();
        notifyObservers();
    }

	public Processor getSender() {
		return this.sender;
	}
}

