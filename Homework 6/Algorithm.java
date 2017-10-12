package edu.sjsu.cs249.five;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the simulation of a main algorithm that will run on processors P1, P2, P3
 * This could be a banking application, payroll application or any other distributed application
 */
public class Algorithm {

    /**
     * The processors which will participate in a distributed application
     */
    Processor processor1, processor2, processor3;

    public Algorithm() {
        //TODO: Homeork: Initialize processors so that they represent the topology of 3 processor system
    	processor1 = new Processor(1);
    	processor2 = new Processor(2);
    	processor3 = new Processor(3);
    	
    	Channel p12 = new Channel("c12"),
    			p13 = new Channel("c13"),
    			p21 = new Channel("c21"),
    			p23 = new Channel("c23"),
    			p31 = new Channel("c31"),
    			p32 = new Channel("c32");
    	
    	//Init P1 completely
    	List<Channel> p1InChannels = new ArrayList<>();
    	Map<Processor, Channel> p1OutChannels = new HashMap<>();
    	p1OutChannels.put(processor2, p12);
    	p1OutChannels.put(processor3, p13);
    	p1InChannels.add(p21);
    	p1InChannels.add(p31);
    	processor1.initializeInChannels(p1InChannels);
    	processor1.initializeOutChannels(p1OutChannels);

    	//Init P1 completely    	
    	List<Channel> p2InChannels = new ArrayList<>();
    	Map<Processor, Channel> p2OutChannels = new HashMap<>();
    	p2OutChannels.put(processor1, p21);
    	p2OutChannels.put(processor3, p23);
    	p2InChannels.add(p12);
    	p2InChannels.add(p32);
    	processor2.initializeInChannels(p2InChannels);
    	processor2.initializeOutChannels(p2OutChannels);

    	//Init P1 completely    	
    	List<Channel> p3InChannels = new ArrayList<>();
    	Map<Processor, Channel> p3OutChannels = new HashMap<>();
    	p3OutChannels.put(processor1, p31);
    	p3OutChannels.put(processor2, p32);
    	p3InChannels.add(p13);
    	p3InChannels.add(p23);
    	processor3.initializeInChannels(p3InChannels);
    	processor3.initializeOutChannels(p3OutChannels);
    	
    	// Modelling null as a marker message in the execution plan for now.
    	// gotta think something better
    	processor1.setExecutionPlan(processor1, processor1, processor2, processor3, processor1);
    	processor2.setExecutionPlan(processor2, processor1, processor2, processor2, processor3);
    	processor3.setExecutionPlan(processor3, processor3, processor1, processor2, processor3);
    }
    
    public void start() throws InterruptedException {
    	processor1.initiateSnapShot();
    	processor1.start();
    	processor2.start();
    	processor3.start();
    	processor1.join();
    	processor2.join();
    	processor3.join();
    }
    
    public void printPendingMessages() {
    	processor1.printPendingMessages();
    	processor2.printPendingMessages();
    	processor3.printPendingMessages();
    }

}
