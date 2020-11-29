# InterviewSolution-
Solution to the MiBosillo IT Challenge 

My approach to the given problem : 

The given problem can be considered as Dijkstra’s shortest path finding problem. 
DFS can be done to find if there would exist a path from one node to another or not. 
And also to find the distance from one node to another(shortest) Djikstra’s algorithm can be used. 
However, In the program various different functions to compute shortest path, number of paths and edges involved. 

Djikstra’s algorithm would find a shortest-path from a fixed start node. 
So the distances or edge weights can be represented as an adjacency matrix 
With a complexity of O(|V|^3)[In the soltion program, functions can be directly used on nodes stored in an appropriate data strucure.]

-> To run the program , put all the JAVA Files (Path.java, Train.java and Map.java) in one single folder and run Train.java

Running using CMD on Windows :

1.Initially Compile all the files or compile Train.java using javac Train.java 


2.And run Train.java using the command :  java Train "AB5, BC4, DC8, DE6, AD5, CE2, EB3, AE7" . 

      
