# Testing

## Unit testing

The program's unit testing covers the pathfinding algorithms and the different data structures.

The tests can be run with the command:
`mvn test`

Test coverage report can be generated with the command:
`mvn test jacoco:report`

The generated report can be found at `/target/site/jacoco/index.html`

Current test coverage is at 97% for the algorithms and data structures

![test coverage](https://github.com/Alex-Elias/Pathfinder/blob/master/documentation/Images/Screenshot_2020-10-24%20pathfinder.png) "Test coverage"

## Performance testing

The goal of performance testing is to see the difference in run times between Java library data structures and and my own implemented data structures as well ass the performance difference between the different algorithms.

### Testing machine

All the tests were run on a Lenovo ThinkPad T490 Laptop

* Intel Core i5-8265U 4 core 3.9GHz processor
* 16 GB DDR4 2400 MHz RAM
* Mesa Intel® UHD Graphics 620

### Data structures performance testing

The data structures performance testing compares the different total insert and removal times of different number of elements.

Each insert and removal is done 100 times per different number of elements and the median run time is recorded for each ammount.

All my data structures are competitive against their Java counterparts, only at large amounts of elements is there a noticable difference. The NodeList starts to become much less efficient around 100000 elements and the NodePriorityQueue starts to beat out Java's version around 10000 elements.

|Inserts| NodePriorityQueue\(us\)| Java PriorityQueue\(us\)|
|-------|---------------------|---------------------|
|100|5|16|
|1000|31|46|
|10000|139|417|
|100000|1749|5244|
|1000000|33054|176128|

|Removals| NodePriorityQueue\(us\)| Java PriorityQueue\(us\)|
|----|-----------|------------|
|100|4|10|
|1000|32|25|
|10000|149|161|
|100000|1391|1874|
|1000000|22301|25580|

|Inserts| NodeList\(us\)| ArrayList\(us\)|
|------|----------------|--------------|
|100|7|6
|1000|14|14
|10000|121|138|
|100000|1399|594|
|1000000|20460|11195|

Below are the graphs comparing the different data structures with their conterparts(the lower the bar the better).

![PriorityQueue insert](https://github.com/Alex-Elias/Pathfinder/blob/master/documentation/Images/Node%20Priority%20Queue%20and%20Java%20Priority%20Queue%20insert%20times.png) "Comparison between Java and Node Priority queues"

![PriorityQueue removal](https://github.com/Alex-Elias/Pathfinder/blob/master/documentation/Images/Node%20Priority%20Queue%20and%20Java%20Priority%20Queue%20removal%20time.png) "Comparison between Java and Node Priority queues removal"

![List comparison](https://github.com/Alex-Elias/Pathfinder/blob/master/documentation/Images/NodeList%20and%20ArrayList%20insert%20times.png) "List comparison"

