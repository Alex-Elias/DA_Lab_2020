# Implementation document

## Projects general structure

The project consists of the following packages:

* GUI: contains the graphical userinterface class.
* algorithms: contains all the pathfinding algorithms for this project.
* datastructures: contains all the datastructures used in this project.
* filereader: contains the Filereader class which reads and returns the contents of a file.
* performanceTesting: contains all the performance testing classes for the algorithms and datastructures in this project.

The program uses the Filereader class to read the map in the form of a text document and creates a matrix representing the map.

The pathfinding algorithms use the matrix to find the shortest path between two points on the map and the GUI class displays the map and the shortest path as a graphical userinterface.

## Time and space complexities of the implemented datastructures and algorithms

### Algorithms

#### Dijkstra's algorithm

Dijkstra's algorithm expected time complexity is O\(\(\|V\|+\|E\|\)log\|V\|\) where \|V\| is the number of nodes and \|E\| is the number of edges.

On the more complex mazes Dijkstra's algoirithm is not much slower than A\* search algorithm. Dijkstra's algorithm is a best-first search algorithm that processes first all nodes that have the least weight and progresses as a wave across the map. Therefore on larger more empty maps Dijkstra's agorithm falls behind on speed because it will process many more nodes than an algorithm that prunes or uses a heuristic.

#### A\* search algorithm

A\* search algorithm's expected time complexity depends on the heuristic used, in the worst case, A\* has the same time complexity as Dijkstra's algorithm. Different heuristics can speed up the search but can sometimes give not exactly the shortest path.

A\* search algorithm pulls ahead of Dijkstra's algorithm when it can follow a mostly straight path to the goal node with few obstacles.

#### Jump point search\(JPS\) 

JPS's expected time complexity also depends on the map, in the worst case JPS has to process just as many nodes as A\* but with more overhead per node the time constant will be higher. On more optimal maps JPS will be much faster than A\* by pruning many of the nodes that will not guarantee a shortest path.

### Datastructures

#### Minimum node priority queue

The minimum priority queue operations can be implemented in O\(log n\) time. Inserting and removing from the queue results in a time complexity of O\(log n\) and the operation min and isEmpty have a time complexity of O\(1\). 

The Priortiy queue uses an array which is lengthed and reduced depending on the number of elements in the queue thus the space complexity is O\(n\).

#### Node list

The node list operations take time O\(1\). Both inserting and removing from the list takes time O\(1\), some individual inserts or removals take longer due to the resizing of the array but on average all operations will take O\(1\) time.

The node list uses an array which is lengthed and reduced depending on the number of elements in the list thus the space complexity is O\(n\).

## Possible flaws and improvements

Some of the flaws and areas of improvement for this program are the graphical userinterface, the structure of the program and the efficency of the algorithms.

The graphical userinterface is pretty barebones with very little functionality. To improve the graphical userinterface I would make the userinterface more intuitive, I would add more maps, be able to create my own maps and I would display all the nodes that the algorithms processed.

The structure of the program could use some improvement on the clarity, some of the names of the variables and methods could use some work and some of the classes could be structured such that there is less repeated code.

The algorithms efficency could be improved in particular A\*. A\* could use a better heuristic which would break ties so fewer same length paths would need to be processed.

## Sorces

* https://en.wikipedia.org/wiki/A\*_search_algorithm
* https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
* https://en.wikipedia.org/wiki/Jump_point_search
* D. Harabor; A. Grastien (2011). [Online Graph Pruning for Pathfinding on Grid Maps](http://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-aaai11.pdf). 25th National Conference on Artificial Intelligence.
* http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
* Moving AI Lab: https://movingai.com/benchmarks/grids.html
* Data Structures and Algorithms Spring 2020 Lecture slides by Juha Kärkkäinen

