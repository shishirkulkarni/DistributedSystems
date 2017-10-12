package edu.sjsu.cs249.five;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Performs all the processor related tasks
 *
 * @author Sample
 * @version 1.0
 */


public class Processor extends Thread implements Observer {
	
	private int id;
	
    private List<Channel> inChannels = null;
    
    private Processor[] executionPlan = null;
    
    private int pc = 0;

    /**
     * List of output channels
     * //TODO: Homework: Use appropriate list implementation and replace null assignment with that
     *
     */
    private Map<Processor, Channel> outChannels = null;

    /**
     * This is a map that will record the state of each incoming channel and all the messages
     * that have been received by this channel since the arrival of marker and receipt of duplicate marker
     * //TODO: Homework: Use appropriate Map implementation.
     */
    private Map<Channel, List<Message>> channelState = null;

    /**
     * This map can be used to keep track of markers received on a channel. When a marker arrives at a channel
     * put it in this map. If a marker arrives again then this map will have an entry already present from before.
     * Before  doing a put in this map first do a get and check if it is not null. ( to find out if an entry exists
     * or not). If the entry does not exist then do a put. If an entry already exists then increment the integer value
     * and do a put again.
     */
    boolean seenMarker = false;
    
    private ChannelRecorder recorder = null;


    /**
     * @param id of the processor
     */
    public Processor(int id) {
        this.id = id;
        recorder = new ChannelRecorder(this);
        channelState = new HashMap<>();
    }
    
    public void initializeInChannels(List<Channel> inChannels) {
    	//TODO: Homework make this processor as the observer for each of its inChannel
        //Hint [loop through each channel and add Observer (this) . Feel free to use java8 style streams if it makes
        // it look cleaner]
    	this.inChannels = inChannels;
    	this.inChannels.stream()
        .forEach(channel -> channel.addObserver(this));
    }
    
    public void initializeOutChannels(Map<Processor, Channel> outChannels) {
    	this.outChannels = outChannels;
    }


    /**
     * This is a dummy implementation which will record current state
     * of this processor
     */
    public void recordMyCurrentState() {
    	System.out.println("Processor " + this + " recording its state: Recording my registers...\tRecording my program counters...\tRecording my local variables...");
    }

    
    /**
     * You should send a message to this recording so that recording is stopped
     * //TODO: Homework: Move this method recordChannel(..) out of this class. Start this method in a
     *                  separate thread. This thread will start when the marker arrives and it will stop
     *                  when a duplicate marker is received. This means that each processor will have the
     *                  capability to start and stop this channel recorder thread. The processor manages the
     *                  record Channel thread. Processor will have the ability to stop the thread.
     *                  Feel free to use any java concurrency  and thread classes to implement this method
     *
     *
     * @param channel The input channel which has to be monitered
     */

    public void recordChannel(Buffer channel) {
        //Here print the value stored in the inChannels to stdout or file
        //TODO:Homework: Channel will have messages from before a marker has arrived. Record messages only after a
        //               marker has arrived.
        //               [hint: Use the getTotalMessageCount () method to get the messages received so far.
        int lastIdx = channel.getTotalMessageCount();
        List<Message> recordedMessagesSinceMarker = new ArrayList<>();
            //TODO: Homework: Record messages
            // [Hint: Get the array that is storing the messages from the channel. Remember that the Buffer class
            // has a member     private List<Message> messages;  which stores all the messages received.
            // When a marker has arrived sample this List of messages and copy only those messages that
            // are received since the marker arrived. Copy these messages into recordedMessagesSinceMarker
            // and put it in the channelState map.
            //
            // ]

//        channelState.put(channel, recordedMessagesSinceMarker);

    }

    /**
     * Overloaded method, called with single argument
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     *
     * @param message Message to be sent
     */
    public void sendMessgeTo(Message message, Processor processor) {
    	outChannels.get(processor).sendMessage(message);
    }

    /**
     *
     * @param fromChannel channel where marker has arrived
     * @return true if this is the first marker false otherwise
     */
    public boolean isFirstMarker(Channel fromChannel) {
        //TODO: Implemetent this method
        return seenMarker;
    }

    /**
     * Gets called when a Processor receives a message in its buffer
     * Processes the message received in the buffer
     */
    public void update(Observable observable, Object arg) {
        Channel c = (Channel)observable;
        Message message = (Message) arg;
        if (message.getMessageType().equals(MessageType.MARKER) && !seenMarker) { //First Marker
        	System.out.println("Processor " + this + " got a " + message.getMessageType() + " from " + message.getFrom() + " on " + c);
        	recordMyCurrentState();
        	
        	recorder.markChannelAsEmpty(c); //Initialize the state of that channel
        	
        	//Start Observing all other incoming channels except the one which we got marker from
        	inChannels.stream()
        	.forEach(channel -> {
        		if(channel != c)
        			channel.addObserver(recorder);
        	});
        		
       		//Delay the forwarding of markers
       		new Thread(new Runnable() {
					
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(10000); //Putting a longer delay to get some values in the final list
						//forward marker on all outgoing channels
						Iterator<Channel> it = outChannels.values().iterator();
						while(it.hasNext()) {
							Channel tmp = it.next();
								tmp.sendMessage(message);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}).start();
        } else {
            if (message.getMessageType().equals(MessageType.ALGORITHM)) {
            	System.out.println("Processor " + this + " got a " + message.getMessageType() + " from " + message.getFrom() + " on " + c);
            }  //There is no other type
        }


    }

    public void initiateSnapShot() {
    	//TODO: Follow steps from Chandy Lamport algorithm. Send out a marker message on outgoing channel
        //[Hint: Use the sendMessgeTo method
        recordMyCurrentState();
		//start Recording all incoming channels
        recorder.startAll();
		seenMarker = true;
        outChannels.values().stream()
		.forEach(channel -> channel.sendMessage(new Message(MessageType.MARKER, this)));
		
    }
    
    public void setExecutionPlan(Processor ...executionPlan) {
		this.executionPlan = executionPlan;
	}
    
    @Override
    public void run() {
    	while(true) {
    		try {
    			Processor p = executionPlan[pc++];
    			if(p == this) { //Computation event
    				compute();
    				Thread.sleep(500);
    			} else {
    				System.out.println("Sending message to " + p);
    				this.sendMessgeTo(new Message(MessageType.ALGORITHM, this), p);
    				Thread.sleep(500);
    			}
    		} catch(ArrayIndexOutOfBoundsException e) {
    			break;
    		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
    	}
    }
    
    /**
     * A dummy computation.
     */
    public void compute() {
    	System.out.println("Doing some computation on Processor " + this);
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "" + id;
    }
    
    public void printPendingMessages() {
    	inChannels.stream()
    	.forEach(channel -> System.out.println(channel.getCountOfPendingMessages()));
    }
    
    public List<Channel> getInChannels() {
		return inChannels;
	}
    
    public Map<Channel, List<Message>> getChannelState() {
		return channelState;
	}
    
    public void setSeenMarker() {
		this.seenMarker = true;
	}
    
    public void printSnapshotState() {
    	StringBuilder s = new StringBuilder();
    	channelState.keySet().stream()
    	.forEach(channel -> {
    		s.append(channel + ": ");
    		s.append(channelState.get(channel));
    		s.append(", ");
    	});
    	System.out.println(s.toString());
    }

}
