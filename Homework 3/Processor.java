package edu.sjsu.cs249.one;

import java.util.*;

/**
 * Created by tphadke on 8/29/17.
 */
public class Processor implements Observer {
    //Each processsor has a message Buffer to store messages
    Buffer messageBuffer ;
    Integer id ;
    Processor parent;
    List<Processor> children;

    //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
    List<Processor> unexplored ;

    public Processor() {
        messageBuffer = new Buffer();
        //Initially a processor doesn't have a parent
        parent = null;
        id = Integer.MIN_VALUE; //This is an invalid value. Since only +ve values are acceptable as processor Ids.
        children = new ArrayList<>();
        //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
        unexplored = new ArrayList<>();
        //Each processor is observing itself;
        messageBuffer.addObserver(this);
    }
    
    public Processor(int id) {
    	this();
    	this.id = id;
    }

    //This method will only be used by the Processor
    private void removeFromUnexplored(Processor p){
        //TODO: implement removing one processor from the list of Children
        getUnexplored().remove(p);
    }

    //This method will add a message to this processors buffer.
    //Other processors will invoke this method to send a message to this Processor
    public void sendMessgeToMyBuffer(Message message, Processor sender){
        messageBuffer.setMessage(message, sender);
    }


    public Processor getParent() {
		return parent;
	}

	public Integer getId() {
        return id;
    }

    //This is analogous to recieve method.Whenever a message is dropped in its buffer this Pocesssor will respond
    //TODO: implement the logic of receive method here
    //      Hint: Add switch case for each of the conditions given in receive
    public void update(Observable observable, Object arg) {
    	Processor sender = messageBuffer.getSender();
    	switch(messageBuffer.getMessage()) {
    		case M:
    			if (parent == null) {
    				parent = sender;
    				removeFromUnexplored(parent);
    				explore();
    				parent.sendMessgeToMyBuffer(Message.PARENT, this);
    			} else {
    				removeFromUnexplored(sender);
    				sender.sendMessgeToMyBuffer(Message.ALREADY, this);
    			}
    			break;
    		case PARENT:
    			removeFromUnexplored(sender);
    			if(sender != this) {
    				getChildren().add(sender);
    			}
    			explore();
    			break;
    		case ALREADY:
    			explore();
    			break;
    	}
    }

    public List<Processor> getChildren() {
		return children;
	}

	private void explore(){
        //TODO: implement this method.
		Iterator<Processor> it = getUnexplored().iterator();
		if(it.hasNext()) {
			Processor Pi = it.next();
			removeFromUnexplored(Pi);
			Pi.sendMessgeToMyBuffer(Message.M, this);
		} 		
    }

	@Override
	public String toString() {
		return " " + id;
	}

	public List<Processor> getUnexplored() {
		return unexplored;
	}

	public void setUnexplored(List<Processor> unexplored) {
		this.unexplored = unexplored;
	}

}
