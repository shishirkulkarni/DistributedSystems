package edu.sjsu.cs249.two;

import java.util.ArrayList;
import java.util.List;

public class Processor {
	private int val;
	private List<Processor> children;
	private Processor parent;
	String sequence, tempSequence;
	
	public Processor() {
		this.children = null;
		this.val = -1; // Assuming all values are going to be positive
		this.parent = null;
		this.sequence = "";
		this.tempSequence = ""; //Store the child sequence here
	}
	
	public Processor(int val) {
		this();
		this.val = val;
		this.children = new ArrayList<>();
	}
	
	public List<Processor> getChildern() {
		return children;
	}
	
	public void setParent(Processor parent) {
		this.parent = parent;
	}
	
	public Processor getParent() {
		return parent;
	}
	
	public int getVal() {
		return val;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public boolean isLeaf() {
		return this.getChildern().size() == 0;
	}
	
	public boolean isRoot() {
		return this.parent == null;
	}
	
}
