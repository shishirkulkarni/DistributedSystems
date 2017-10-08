package edu.sjsu.cs249.four;

import java.util.Observable;

public class Clock extends Observable implements Runnable{
	private int jiffy, interval;
	private Thread t;

	public Clock(int jiffy, int interval) {
		// TODO Auto-generated constructor stub
		this.jiffy = jiffy;
		this.interval = interval;
		t = new Thread(this);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Generate clock ticks
		while(true) {
			try {
				Thread.sleep(interval * 1000);
				jiffy += 1;
				setChanged();
				notifyObservers(jiffy);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}
	
	//Proxy methods for threads
	public void start() {
		t.start();
	}
	
	public void join() {
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		t.interrupt();
	}
	
	public int getJiffy() {
		return jiffy;
	}
	
	public void setJiffy(int jiffy) {
		this.jiffy = jiffy;
	}
	

}
