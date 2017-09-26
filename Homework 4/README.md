Converge-cast algorithm simulation:
===================================
This is an implementation simulating the Converge-cast algorithm on a spanning tree. 

Input:
------
Root node of a spanning tree having processors as nodes and communication channels as edges.

Output:
-------
The maximum value amongst all the nodes converged at root along with all the values concatenated at the root.

Implementation:
---------------
* The tree is implemented using a Java Class.
* Each node has a list of its children. 
* The trigger method takes root node as its input. The tree is traversed using DFS to find out leaf nodes and trigger the converge-cast algorithm.
* The processor implements observer observable pattern and watches for messages from its children. Whenever it receives a message from its child, it updates its value to the max of its current value and the value of the sender.
* Once it has received message from all its children, it forwards its value to its parent.
* The algorithm terminates when root receives messages from all its children.
* Every processor also maintains a sequence of the order of messages it receives from its children. The final sequence is available at the root after termination.

Complexity:
-----------
* Message Complexity: O(n -1) where n is the number of nodes in the tree
* Time Complexity: O(d) where d is the depth of the tree

Classes:
--------
* Processor.java
* Tree.java
* Main.java

Authors:
--------
* Prof. Tanuja Phadke
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute
