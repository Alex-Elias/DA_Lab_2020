# Project specification

## Project discription

The project finds the shortest path between two points on a graph using different pathfinding algorithms. The program requires a map and start and end points for the input. The program then outputs the shortest path length and route with the duration of the specific algorithm.

## Implemented algorithms

The Pathfinding algorithms Implemeted are Dijkstra's algorithm, A Star search algorithm and Jump point first algorithm.

### Dijkstra's algorithm

Dijkstra's algorithm is an algorithm for finding the shortest path between two points in a weighted graph. The algorithm will find all the shortest paths between the starting point and all other points unless the algorithm is stoped early at the desired point.

Dijkstra's algorithm uses a priority queue to first process the paths with the least weight.

Dijkstra's algorithm is a natural choice for a pathfinding program, the algorithm will always find a correct path, it is efficent enough to be used on large graphs and it is relatively easy to implement.

### A Star search algorithm

A Star search algorithm is an algorithm for finding the shortest path between two points in a weighted graph. A Star search algorithm can be seen as an extention of Dijktra's algorithm. It achieves better performance by using heuristics to guide its search.

A Star search algorithm uses a priority queue with heuristics to help reduce the amount of nodes it has to process.

A Star search algorithm is a natural choice for a pathfinding program because it is similar to Dijktra's algorithm but uses heuristics to increase its efficency.

### Jump point search algorithm

Jump point search algorithm (JPS) is an algorithm for finding the shortest path between two points in an uniform-cost grid. JPS is an extention to the A Star search algorithm. It achieves better performance by pruning certain unfavorable nodes before they are processed and creating 'jump points' along straight lines on the grid.

JPS is a natural choice for shortest pathfinding in an uniform-cost grid because it can greatly improve the efficency over A Star algorithm.


## Implemented datastructures

The implemented algorithms for this project require a priority queue and a basic list datastructure.

A basic Tuple and Node are implemeted for ease of use.

## Inputs

The program inputs are a map and starting and ending points. The progam already has built in maps which the user can freely choose between. The starting and ending points are given to the shortest path algorithms as parameters and the algoirthms return the shortest path and distance of the path.

## Expected time and space complexities of the program

|E| is the number of edges and |V| is the number of vertices.

|Algorithm| Time Complexity| Space Complexity|
|---------|----------------|-----------------|
|Dijkstra| O\(\(|E|+|V|\)log|V|\) | O\(|V|\) | 
|A Star| O\(|E|\) | O\(|V|\) |
|JPS| O\(|E|\) | O\(|V|\) |


## Sources

* https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
* https://en.wikipedia.org/wiki/A*_search_algorithm#Complexity
* https://en.wikipedia.org/wiki/Jump_point_search
* https://iq.opengenus.org/dijkstras-algorithm-finding-shortest-path-between-all-nodes/
* http://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-aaai11.pdf
* https://www.redblobgames.com/pathfinding/a-star/introduction.html


##### Degree programme: Bachelor's Programme in Chemistry


##### Documentation language: English 
