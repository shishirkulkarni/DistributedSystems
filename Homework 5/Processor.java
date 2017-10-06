package edu.sjsu.cs249.three;

import java.util.Observable;
import java.util.Observer;

/**
 * Performs all the processor related tasks
 * @author Sample
 * @version 1.0
 *
 */
public class Processor implements Observer {
	//TODo : add appropriate visibility indicators to each member variable
    private Buffer messageBuffer ;
    private Integer id ;
    //TODO: Think through when would you need a list of vector clocks
    private VectorClock vc; //This is the current vector clock 
    private Clock logicalClock;
    private Processor []executionPlan;
    private int pc; //Program counter

    /**
     * Initializes the processor with id, children and unexplored lists. Adds himself in the observers list.
     * @param id of the processor
     */
    public Processor(int id, int totalProcesors, int jiffy, int interval) {
        messageBuffer = new Buffer();
        
        this.id = id; 
        
        messageBuffer.addObserver(this);
        
        this.logicalClock = new Clock(jiffy, interval);
        this.logicalClock.addObserver(this);
        this.vc = new VectorClock(totalProcesors);
        pc = 0;
    }
    
    //proxy methods for threads
    public void start() {
    	this.logicalClock.start();
    }
    
    public void join() {
    	this.logicalClock.join();
    }
   
    
    /**
     * Overloaded method, called with single argument
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     * @param message Message to be sent
     */
    public void sendMessgeToMyBuffer(Message message){
    	messageBuffer.setMessage(message);
    }


    /**
     * Gets called when a node receives a message in it buffer
     * Processes the message received in the buffer
     */
    public void update(Observable observable, Object arg) {
    	//If a clock tick
    	if(observable == this.logicalClock) {
    		try {
    			Processor k = executionPlan[pc++];
    			if(k == null) {
    				this.logicalClock.setJiffy(((Integer)arg) - 1);
    				return;
    			}
    			else if(k == this) {
					this.vc.updateAt(id, this.vc.valueAt(id) + 1);
					System.out.println("[Processor = P" + this + "] [COMPUTATION] [VC = " + this.vc + "]");
    			} else {
    				this.vc.updateAt(id, this.vc.valueAt(id) + 1);
    				System.out.println("[Processor = P" + this + "] [SEND] [VC =  " + this.vc + "]");
    				k.sendMessgeToMyBuffer(new Message(MessageType.SEND, vc));
    			}
    		} catch (ArrayIndexOutOfBoundsException e) { // Done with the Execution
				this.logicalClock.stop();
			}
    	}
    	
    	//If a message
    	if(observable == messageBuffer) {
				this.vc.updateAt(id, this.vc.valueAt(id) + 1);
				this.vc.updateVectorClock(messageBuffer.getMessage().getVc());
	    		System.out.println("[Processor = P"+ id +"] [RECIEVE] [VC = " + this.vc + "]");
    	}
    }
    
    public void setExecutionPlan(Processor... executionPlan) {
		this.executionPlan = executionPlan;
	}
    
    public VectorClock getVc() {
		return vc;
	}
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "" + id;
    }
}
