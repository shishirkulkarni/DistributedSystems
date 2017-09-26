package edu.sjsu.cs249.one;

import java.util.Observable;

/**
 * Created by tphadke on 8/29/17.
 */
public class Buffer {
    private Message message;

    public Buffer(){
        //Create an empty Buffer
    }
    
    public Buffer(Message message) {
        this.message = message;
    }

    public Message  getMessage() {
        return message;
    }

	public void setMessage(Message message ) {
        this.message = message;
       
    }
}

