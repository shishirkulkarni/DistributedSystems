package edu.sjsu.cs249.six;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Initialize Processors
		Processor p1 = new Processor(1, 53),
				p2 = new Processor(2, 19),
				p3 = new Processor(3, 43),
				p4 = new Processor(4, 25),
				p5 = new Processor(5, 23);
		
		//Create Topology
		p1.setNeighbous(p5,  p2);
		p2.setNeighbous(p1, p3);
		p3.setNeighbous(p2, p4);
		p4.setNeighbous(p3, p5);
		p5.setNeighbous(p4, p1);
		
		Algorithm asyncRingElection = new Algorithm(p1, p2, p3, p4, p5);
		try {
			asyncRingElection.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
