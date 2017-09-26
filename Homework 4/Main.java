package edu.sjsu.cs249.two;

import java.util.Iterator;

public class Main {
	
	Tree t;
	
	public Main() {
		t = new Tree();		
	}
	
	public static void main(String[] args) {
		
		Main driver = new Main();
		
		Processor p0 = new Processor(2);
		Processor p1 = new Processor(7);
		Processor p2 = new Processor(5);
		Processor p3 = new Processor(2);
		Processor p4 = new Processor(6);
		Processor p5 = new Processor(9);
		Processor p6 = new Processor(5);
		Processor p7 = new Processor(11);
		Processor p8 = new Processor(4);
		
		driver.init(p0, p1, p2, p3, p4, p5, p6, p7, p8);
				
		driver.trigger(p0); //Traverse the tree using DFS to find out leaf nodes and send messages
		System.out.println(p0);	
	}
	
	public void trigger(Processor root) {

		if(root.getChildern().size() == 0) {
			root.sendMessageToMyBuffer(root);
			return;
		}
		
		Iterator<Processor> it = root.getChildern().iterator();
		while(it.hasNext()) {
			trigger(it.next());
		}
		
	}
	
	public void init(Processor... p) {
		t.insert(p[0], null);
		t.insert(p[1], p[0]);
		t.insert(p[2], p[0]);
		t.insert(p[3], p[1]);
		t.insert(p[4], p[1]);
		t.insert(p[5], p[2]);
		t.insert(p[6], p[4]);
		t.insert(p[7], p[4]);
		t.insert(p[8], p[5]);
	}
	
}
