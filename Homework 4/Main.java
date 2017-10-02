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
				
		System.out.println("Value: " + driver.converge(p0));
		System.out.println("Sequence: " + p0.getSequence());
	}
	
	public int converge(Processor p) {

		if(p.isLeaf()) {
			p.setSequence("" + p.getVal());
			return p.getVal();
		}
		
		int max = p.getVal();

		Iterator<Processor> it = p.getChildern().iterator();
		while(it.hasNext()) { //Get Max value from all the children
			Processor child = it.next();
			int childMax = converge(child);
			if(max < childMax) {
				max = childMax;
			}
			p.tempSequence += child.getSequence() + ", ";
		}
		
		p.setSequence(p.tempSequence + p.getVal());
		return max;
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
