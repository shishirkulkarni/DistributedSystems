package edu.sjsu.cs249.two;

import java.util.Iterator;

public class Tree {
	private Processor root;
	
	public Tree() {
		this.root = null;
	}
	
	//Inserts @pi as a child of @parent
	public void insert(Processor pi, Processor parent) {
		
		if(parent == null) {
			setRoot(pi);
		} else {
			parent.getChildern().add(pi);
			pi.setParent(parent);
		}

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


	public Processor getRoot() {
		return root;
	}
	
	public void setRoot(Processor root) {
		this.root = root;
	}
}
