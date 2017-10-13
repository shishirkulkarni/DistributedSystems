package edu.sjsu.cs249.five;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tphadke on 9/27/17.
 */
public class Main {

    public static void main(String args[]) throws InterruptedException {


        /**
         * Choose one processor to initiale a snapshot. Please note that any processor has the capability to
         * initiate a snapshot.
         * //TODO: Homework: initiate snapshot
         * [Hint: call the initiateSnapshot method ]
         */
    	
    	Processor processor1 = new Processor(1),
    			processor2 = new Processor(2),
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


    	Algorithm cl = new Algorithm(processor1, processor2, processor3);
    	processor1.initiateSnapShot();
    	try {
			cl.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//Currently putting a longer delay to print the state of channels after the algorithm
    	// since the marker delay threads aren't joined to main thread
    	Thread.sleep(20000);
    	cl.processor1.printSnapshotState();
    	cl.processor2.printSnapshotState();
    	cl.processor3.printSnapshotState();

    }


}
