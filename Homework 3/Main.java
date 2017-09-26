package edu.sjsu.cs249.one;

import java.util.*;

/**
 * Created by tphadke on 8/29/17.
 */
public class Main {
    Map <Processor, List<Processor> > graph ;

    public Main(){
        init();
    }

    public static void main ( String[] args){
        Main m = new Main();        
        //TODO: Choose a processor as a Root
        //TODO: Send an initial message Message.M to this processor.
        Processor root;
        for (Iterator<Processor> it = m.getGraph().keySet().iterator(); it.hasNext();) {
        	root = it.next();
        	if(root.getId() == 0) {
        		root.sendMessgeToMyBuffer(Message.M, root);
        		m.printTree(root);
        		break;
        	}
        }
    }

    public void init(){
        //TODO: Populate the Graph with processors 0,1,2,3...
    	this.graph = new HashMap<>();
    	
    	Processor p0, p1, p2, p3, p4, p5;
    	p0 = new Processor(0);
    	p1 = new Processor(1);
    	p2 = new Processor(2);
    	p3 = new Processor(3);
    	p4 = new Processor(4);
    	p5 = new Processor(5);

    	graph.put(p0, new LinkedList<Processor>(Arrays.asList(new Processor[] {p2, p1, p3})));
    	p0.setUnexplored(new LinkedList<Processor>(Arrays.asList(new Processor[] {p2, p1, p3})));
    	graph.put(p1, new LinkedList<Processor>(Arrays.asList(new Processor[] {p0, p2, p4})));
    	p1.setUnexplored(new LinkedList<Processor>(Arrays.asList(new Processor[] {p0, p2, p4})));
    	graph.put(p2, new LinkedList<Processor>(Arrays.asList(new Processor[] {p0, p1, p5})));
    	p2.setUnexplored(new LinkedList<Processor>(Arrays.asList(new Processor[] {p0, p1, p5})));
    	graph.put(p3, new LinkedList<Processor>(Arrays.asList(new Processor[] {p0})));
    	p3.setUnexplored(new LinkedList<Processor>(Arrays.asList(new Processor[] {p0})));
    	graph.put(p4, new LinkedList<Processor>(Arrays.asList(new Processor[] {p1, p5})));
    	p4.setUnexplored(new LinkedList<Processor>(Arrays.asList(new Processor[] {p1, p5})));
    	graph.put(p5, new LinkedList<Processor>(Arrays.asList(new Processor[] {p2, p4})));
    	p5.setUnexplored(new LinkedList<Processor>(Arrays.asList(new Processor[] {p2, p4})));
    	
    }

	public Map<Processor, List<Processor>> getGraph() {
		return graph;
	}
	
	public void printTree(Processor root) {
		System.out.println("Processor: " + root + " Children: " + root.getChildren());
		Iterator<Processor> it = root.getChildren().iterator();
		while(it.hasNext()) {
			this.printTree(it.next());
		}
	}

	public void setGraph(Map<Processor, List<Processor>> graph) {
		this.graph = graph;
	}
}
