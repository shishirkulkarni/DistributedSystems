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
	
	public Processor getRoot() {
		return root;
	}
	
	public void setRoot(Processor root) {
		this.root = root;
	}
}
