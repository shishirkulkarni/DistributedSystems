package edu.sjsu.cs249.seven;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Initialize Processors
		Processor p1 = new Processor(1, 10),
				p2 = new Processor(2, 22),
				p3 = new Processor(3, 11),
				p4 = new Processor(4, 66),
				p5 = new Processor(5, 50),
				p6 = new Processor(6, 44);
		
		//Create Topology
		p1.setNeighbous(p6,  p2);
		p2.setNeighbous(p1, p3);
		p3.setNeighbous(p2, p4);
		p4.setNeighbous(p3, p5);
		p5.setNeighbous(p4, p6);
		p6.setNeighbous(p5, p1);
		
		Algorithm asyncRing = new Algorithm(p1, p2, p3, p4, p5, p6);
		try {
			asyncRing.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
