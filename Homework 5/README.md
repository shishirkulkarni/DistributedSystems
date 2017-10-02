UML for Vector-clock computations:
===================================
This is aUML diagram of vector clocks of processors.

Input:
------
The events in the executionPlan() methods.

Output:
-------
The final computed vector clocks of the processors.

Procedure:
---------------
* There are 3 processors present P0,P1 and P2 each maintaining a vector clock 
and a message buffer. 
* Each processor can have 3 types of events executing, SEND, RECEIVE and 
COMPUTE. Depending on the type of event, the vector clock will be updated.
* On a compute event, the vector clock of the processor will be incremented by 
1.
* On a send event, the processor will increment its vector clock and send it to 
the receiver.
* On a receive event, the processor will increment its own vector clock and 
take the maximum value of vector clocks of other processors. 
* Algorithm class initializes the Processors P0,P1 and P2 and calls the 
executionPlan() method for each processor.
* The executionPlan() method will call the send, receive and compute events as 
input given by the user.
* Each Processor maintains a vector clock. Depending on the type of 
message(event), the vector clock will be updated. The different events occur 
concurrently in all the processors.
* Once all the events are completed, the final vector clocks in each processor 
is the final output.

Authors:
--------
* Prof. Tanuja Phadke
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute


