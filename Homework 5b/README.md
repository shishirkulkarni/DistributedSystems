Maximal Consistent Cut:
=====================
This is an implementation simulating the calculation of Maximal Consistent Cut

Input:
------
A vector clock instance representing the cut value.

Output:
-------
The maximal consistent cut for the input value.

Implementation:
---------------
Calculation of vector clock :

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
* The final vector clock of the processors after all the events have finished 
execution is the output.

Calculation of maximal consistent cut :

* A list store is created to store the vector clock values after each computation for each processor
* The vector for cut is passed to the processors
* The value of the cut and store vector is compared to find the maximal consistent cut.


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


Authors:
--------
* Shishir Kulkarni
* Sphoorti Poojary
* Shubham Pachpute
