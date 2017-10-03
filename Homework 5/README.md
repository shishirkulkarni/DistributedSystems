Vector Clock Simulation:
===================================
This is an implementation simulating the calculation of Vector Clocks

Input:
------
3 types of events - SEND,RECEIVE and COMPUTATION for the 3 processors P0,P1,P2. 
Initially the vector clocks of all the processors will be initialized to 0.

Output:
-------
The final vector clocks of the 3 processors.

Implementation:
---------------
* The calculation for vector clocks of the processors is implemented using Java
classes
* There are 3 processors present - P0,P1,P2
* Each processor has an id (For processor P0-0, P1-1 and P2-2), and a time 
interval after which it starts execution.
* Each processor also has a vector clock which is initially set to 0
* There is an execution plan defined for each processors according to which the
3 types of events - SEND,RECEIVE and COMPUTE will execute in the processor.
* Since all the processors need to execute concurrently,threads are implemented
and each processor starts execution after waiting for an interval of time. Thus
all the events can stay synchronized.
* The vector clocks of each processor will be updated accordingly.
* The final vector clock of the processors after all he events have finished 
execution is the output.


Classes:
--------
* Processor.java
* Buffer.java
* Main.java
* Message.java
* MessageType.java
* Clock.java
* VectorClock.java
* Algorithm.java


Question and Answers:
----------------------

* Interactions
-> Interaction between the processors by 3 events namely COMPUTATION, SEND and 
RECEIVE.

* What we need to store?
-> Vector clock of every processor after any event. 

* What needs to change?
-> The vector clocks of each processor will change upon receiving the COMPUTE, 
SEND or RECEIVE messages.

* What will be the input?
-> The processors and their execution plans will be the input of the algorithm.
The execution plan of each processor will have the events that will take place
individually for each process as per the message type. 

* What will be the output?
-> The updated vector clocks of each processor after all the events have 
taken place will be the final output of the program. 

* Decide which event happened Before, which event is concurrent
-> The timeline of each event explaining its occurence with respect to other 
events could be found out by comparing their vector clocks. The vector clocks
of each processor at a particular instance(event) would help help us compare as
to which event took place before the other, or whether the events were 
concurrent.


Authors:
--------
* Prof. Tanuja Phadke
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute
