DFS Spanning tree using modified flooding algorithm (v1.0)
==========================================================
This is an implementation simulating the construction of a spanning tree using the depth first traversal. 

Input:
------
An undirected Graph with Processors as nodes and communication channels as edges.

Output:
-------
A spanning tree

Implementation:
---------------
* The graph is implemented using a hashmap with keys as processor nodes and values as adjacency lists of the nodes.
* Each processor has a message buffer where it can receive messages from its neighbors, a list of neighbors not yet explored, a parent node pointer and a list of children.
* The processor watches the message Buffer for new messages which is implemented using Observer-Observable design pattern.
* The message could be of 3 types: M, ALREADY and PARENT.
* Whenever a processor gets a message M for the first time, it sets the sender as its parent and removes the parent from its unexplored list. It then continues exploring its remaining neighbors.
* Upon receiving M subsequently, the processor sends an ALREADY message to the sender and removes the sender from its unexplored list.
* Once the entire unexplored list is exhausted, the processor sends a PARENT message to its parent.
* Upon receiving a PARENT message, the processor adds the sender to its list of children.
* The algorithm terminates once root node has an empty unexplored list.

Complexity:
-----------
* Message Complexity: O(|E|) where E is the number of edges in the graph
* Time Complexity: O(D) where D is the diameter of the graph

Classes:
--------
* Processor.java
* Message.java
* Buffer.java
* Main.java

Authors:
--------
* Prof. Tanuja Phadke
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute
