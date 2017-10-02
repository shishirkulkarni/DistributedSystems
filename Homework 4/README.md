Converge-cast algorithm simulation (v2):
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
* A processor recursively calculates the maximum value of its subtree rooted at its children and returns the max of its own value and the maximum value of the children 
* The algorithm terminates when root returns its maximum value.
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
