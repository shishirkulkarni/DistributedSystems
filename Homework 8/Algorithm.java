package edu.sjsu.cs249.seven;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Algorithm {
	public List<Processor> processors;
	int phase;
	
	public Algorithm(Processor ...processors) {
		this.processors = new ArrayList<>();
		for(Processor p: processors) {
			this.processors.add(p);
		}
	}
	
	public void start() throws InterruptedException {
		List<Thread> threads = new ArrayList<>();
		
		//Initially all of them will send messages parallely so create
		//two threads per processor to send initial message
		phase = 1;
		
		do {
			// remove threads from all the previous iterations
			threads.clear();
			CountDownLatch latch = new CountDownLatch(processors.size() * 2);
			
			for(Processor p: processors) {
				// Reset reply flags so that it can again probe
				p.resetReplyFlags();
				
				// Create 2 threads so that both the left and right messages are sent in simultaneously			
				threads.add(new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						p.getLeft().sendMessageToMyBuffer(new Message(p, p.getValue(), MessageType.PROBE, MessageDirection.LEFT, phase));
						latch.countDown();
					}
				}));
				
				threads.add(new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						p.getRight().sendMessageToMyBuffer(new Message(p, p.getValue(), MessageType.PROBE, MessageDirection.RIGHT, phase));
						latch.countDown();
					}
				}));
			}
			
			for(Thread t: threads) {
				t.start();
			}
			
			latch.await();
			
			phase++;

			//Get rid of those processors who lost
			Iterator<Processor> it = this.processors.iterator();
			while(it.hasNext()) {
				Processor p = it.next();
				if(!p.isActive()) {
					it.remove();
				}
			}
			
		} while(processors.size() > 1);
		System.out.println("-------------------");
		System.out.println("Leader Processor: " + processors.get(0));
	}
}
