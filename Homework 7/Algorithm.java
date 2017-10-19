package edu.sjsu.cs249.six;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
	public Processor[] processors;
	
	public Algorithm(Processor ...processors) {
		this.processors = processors;
	}
	
	public void start() throws InterruptedException {
		List<Thread> threads = new ArrayList<>();
		
		//Initially all of them will send message parallely so create
		//one thread per processor to send initial message
		
		for(Processor p: processors) {
			threads.add(new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					p.getLeft().sendMessageToMyBuffer(new Message(p, p.getValue(), MessageType.ALGORITHM));
				}
			}));
		}
		
		for(Thread t: threads) {
			t.start();
		}
		
		for(Thread t: threads) {
			t.join();
		}		
	}
}
