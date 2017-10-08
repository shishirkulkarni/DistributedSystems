package edu.sjsu.cs249.four;

public class Algorithm {
	int noOfProcessors;
	Processor p0, p1; // Assume there are three processors.

	public Algorithm() {
		super();
		this.noOfProcessors = 2;
		// TODO : initialize all the processors
		p0 = new Processor(0, 2, 0, 1);
		p1 = new Processor(1, 2, 0, 1);
		p0.setExecutionPlan(null, null, p1, null, null, p0, null, null, null, p0, null, p1);
		p1.setExecutionPlan(p1, p1, p1, null, null, null, p1);
	}
	
	public void execute() {
		p0.start();
		p1.start();
		
		p0.join();
		p1.join();
		
		System.out.println();
		System.out.println("+-----------------------+");
		System.out.println("| P0: " + p0.getVc() + " P1: " + p1.getVc() + " |");
		System.out.println("+-----------------------+");
	}
	
	public Processor getP0() {
		return p0;
	}
	
	public Processor getP1() {
		return p1;
	}
}
