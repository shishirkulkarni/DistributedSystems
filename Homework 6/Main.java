package edu.sjsu.cs249.five;



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

    	Algorithm cl = new Algorithm();
    	try {
			cl.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//Currently putting a longer delay to print the state of channels after the algorithm
    	Thread.sleep(20000);
    	cl.processor1.printSnapshotState();
    	cl.processor2.printSnapshotState();
    	cl.processor3.printSnapshotState();

    }


}
