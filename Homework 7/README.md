Leader election in asynchronous ring(O(n2)):
======================================
This is an implementation simulating the O(n2) algorithm for leader election in asynchronous ring 

Input:
------
The ring topology of the 5 processors along with their values

Output:
-------
The leader node

Implementation:
---------------
* There are 5 processors present in this implementation p1,p2,p3,p4,p5. Each processor has a processor id and an integer value. All the processors are connected in a ring topology
* Each node has information about its neighbours
* Initially all processors will send their value to their left neighbours. A separate thread is created for each processor.
* On receiving a message from neighbour, the processor will check its own value with the received value. If the value received by it is greater, then it will forward this message to its left neighbour. Otherwise it will consume the message
* A processor on receiving its own message back declares itself to be the leader by setting its isLeader to true and sends a terminate message across all the processors


Classes:
--------
* Processor.java
* Algorithm.java
* Main.java
* Message.java
* MessageBuffer.java
* MessageType.java


Authors:
--------
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute


