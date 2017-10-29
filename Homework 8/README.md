Leader election in asynchronous ring(O(nlogn)):
======================================
This is an implementation simulating the O(nlogn) algorithm for leader election in asynchronous ring 

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
* Initially all processors will send their value with PROBE message to their left and right neighbours  . 2 separate threads for each processor are created for this.
* If a processor receives a value from its neighbour which is greater than its own value then it sends a REPLY message to the processor.
* A processor on receiving a REPLY message from both its left and right neighboous remains active and moves to the next phase. 
* A processor on not receiving a REPLY message from both its neighbours becomes inactive and does not move to the next phase.
* A didWin variable is maintaied which determines if a processor has received REPLY message from all processors it sent the PROBE to.
* In the next phase every active processor will send PROBE message to 2 of their left and right neighbours. The hop count is incremented in every phase. A hop count variable is maintained for this.
* The last processor to remain in the active state is the LEADER processor.

Classes:
--------
* Processor.java
* Algorithm.java
* Main.java
* Message.java
* MessageBuffer.java
* MessageType.java
* MessageDirection.java


Authors:
--------
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute


