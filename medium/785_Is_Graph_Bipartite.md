###
Given an undirected graph, return true if and only if it is bipartite. 
Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B. 
The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  
There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
  * Method 1 BFS
      * Color the node with -1 and 1. If a node has already been colored but need to be colored again with the opposite color, then return false.
      * Only the node has not been colored can be added to the queue.
      * Visit the node only if it has not been colored.
```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0 || graph[0].length == 0) {
        }
        int[] color = new int[graph.length];
        int flag;
        for (int i = 0; i < graph.length; i++){
            if (color[i] != 0) {
                continue;
            }
            LinkedList<Integer> queue = new LinkedList<>();
            queue.offerFirst(i);
            color[i] = 1;
            while(!queue.isEmpty()) {
              Integer current = queue.pollLast();
              flag = color[current];
              for (int j = 0; j < graph[current].length; j++) {
                if (color[graph[current][j]] == 0) {
                    color[graph[current][j]] = -flag;
                    queue.offerFirst(graph[current][j]);
                } else if (color[graph[current][j]] == flag) {
                    return false;
                } 
              }
            }
        }
        return true;
    }
}
```
  * Method 2 DFS
      * 
