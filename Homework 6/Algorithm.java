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

    public Algorithm(Processor processor1, Processor processor2, Processor processor3) {
        //TODO: Homeork: Initialize processors so that they represent the topology of 3 processor system
    	this.processor1 = processor1;
    	this.processor2 = processor2;
    	this.processor3 = processor3;
    	// Modelling null as a marker message in the execution plan for now.
    	// gotta think something better
    	processor1.setExecutionPlan(processor1, processor1, processor2, processor3, processor1);
    	processor2.setExecutionPlan(processor2, processor1, processor2, processor2, processor3);
    	processor3.setExecutionPlan(processor3, processor3, processor1, processor2, processor3);
    }
    
    public void start() throws InterruptedException {
    	processor1.start();
    	processor2.start();
    	processor3.start();
    	processor1.join();
    	processor2.join();
    	processor3.join();
    }
    
    

}
