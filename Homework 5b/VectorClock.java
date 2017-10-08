package edu.sjsu.cs249.four;


public class VectorClock implements Comparable<VectorClock>{
	//TODO: read up how to use a comparable and a comparator
	//TODO: Do you see an advantage in making it an Integer ?? 
	private int[] vc;

	public VectorClock( int noOfProcesses ) {
		vc = new int [noOfProcesses];
		//TODO : Set all to 0.  Do you need to explicitly initilize to 0
	}
	
	public VectorClock(int[] clk) {
		this.vc = clk;
	}
	
		
	/**
	 * Based on a event vector clock will be incremented, changed or updated.
	 * Which index should be updated will be decided by a processor
	 * @param index
	 * @param value
	 */
	public void updateAt(int index, int value){
		//TODO : Apply Vector clock algorithm 
		vc[index]= value;
	}
	
	public int length() {
		return vc.length;
	}
	
	public VectorClock clone() {
		VectorClock c = new VectorClock(this.length());
		c.vc = this.vc.clone();
		return c;
	}
	
	public int valueAt(int i) {
		return vc[i];
	}
	
	public void updateVectorClock(VectorClock senderVc) {
		for(int i = 0; i < this.length(); i++) {
    		this.updateAt(i, Math.max(this.valueAt(i), senderVc.valueAt(i)));
    	}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		s.append("<");
		for(int i = 0; i < vc.length - 1; i++) {
			s.append(vc[i]);
			s.append(", ");
		}
		s.append(vc[vc.length - 1]);
		s.append(">");
		return s.toString();
	}

	@Override
	public int compareTo(VectorClock o) {
		// TODO Auto-generated method stub
		int i;
		
		//Less than or equal to
		for(i = 0; i < vc.length; i++) {
			if(vc[i] <= o.valueAt(i)) {
				continue;
			} else {
				break;
			}
		}
		
		if(i == vc.length) {
			return -2;
		}
		
		//Greater than or equal to
		for(i = 0; i < vc.length; i++) {
			if(vc[i] >= o.valueAt(i)) {
				continue;
			} else {
				break;
			}
		}
				
		if(i == vc.length) {
			return 2;
		}
				
		//Strictly less
		for(i = 0; i < vc.length; i++) {
			if(vc[i] < o.valueAt(i)) {
				continue;
			} else {
				break;
			}
		}
		

		if(i == vc.length) {
			return -1;
		}

		//Strictly equal
		for(i = 0; i < vc.length; i++) {
			if(vc[i] == o.valueAt(i)) {
				continue;
			} else {
				break;
			}
		}
		
		if(i == vc.length) {
			return 0;
		}
		
		//Strictly greater
		for(i = 0; i < vc.length; i++) {
			if(vc[i] > o.valueAt(i)) {
				continue;
			} else {
				break;
			}
		}
		
		if(i == vc.length) {
			return 1;
		}
		
		return 3;
	}
	
	
}
