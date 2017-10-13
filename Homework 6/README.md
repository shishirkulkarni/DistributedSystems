Chandy Lamport Algorithm Simulation:
======================================
This is an implementation simulating the Chandy Lamport Algorithm

Input:
------
Execution plan of the 3 processors P1,P2,P3.

Output:
-------
Snapshot of the processors containing the messages received by them

Implementation:
---------------
* There are 3 processors present in this implementation P1,P2,P3
* Each processor has an incoming and outgoing channel. Outgoing channel is implemented as a HashMap and incoming channel as a List
* There are 3 message types MARKER, ALGORITHM and COMPUTATION.
* We pass an execution plan for each processor. The processors send ALGORITHM message to each other. 
* Snapshot is initiated from Processor P1. It starts recording its state and sends MARKER message through its outgoing channel P12 and P13 to Processor P2 and P3. We have used Java 8 style streams here.
* A processor on receving a MARKER message starts recording messages from its incoming channels except the one from which it received the MARKER message and stores it in channelState. It also sets the channel from which it receives the marker message as empty and sends the marker message on its outgoing channels.
* The process continues until a processor receives a duplicate marker at which stage the recording of messages is stopped.
* Once all the processors have received a duplicate marker the algorithm is terminated.


Classes:
--------
* Processor.java
* Buffer.java
* Main.java
* Message.java
* MessageType.java
* Channel.java
* ChannelRecorder.java
* Algorithm.java


Authors:
--------
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute


