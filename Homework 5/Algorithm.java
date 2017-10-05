package edu.sjsu.cs249.three;

public class Algorithm {
	int noOfProcessors;
	Processor p0, p1, p2; // Assume there are three processors.

	public Algorithm() {
		super();
		this.noOfProcessors = 3;
		// TODO : initialize all the processors
		p0 = new Processor(0, 3, 0, 1);
		p1 = new Processor(1, 3, 0, 1);
		p2 = new Processor(2, 3, 0, 1);
		p0.setExecutionPlan(null, p1, null, null, p2, p0, null, null, null, p0);
		p1.setExecutionPlan(null, null, null, null, null, p2, p0);
		p2.setExecutionPlan(p2, p2, null, p1, null, p1, p2);
	}
	
	public void execute() {
		p0.start();
		p1.start();
		p2.start();
		p0.join();
		p1.join();
		p2.join();
		
		System.out.println();
		System.out.println("+-------------------------------------------+");
		System.out.println("| P0: " + p0.getVc() + " P1: " + p1.getVc() + " P2: " + p2.getVc() + " |");
		System.out.println("+-------------------------------------------+");
	}
}
