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
```
BFS(G,s)
1.  for each vertex u ∈ G.V - {s}
2.     u.color == WHITE
3.     u.d = INF
4.     u.pi = NIL
5.  s.color = GRAY
6.  s.d = 0
7.  s.pi = NIL
8.  Q = ∅
9.  ENQUEUE(Q,s)
10. while Q ≠ ∅
11.    u = DEQUEUE(Q)
12.    for each v ∈ G.Adj[u]
13.       if v.color == WHITE
14.          v.color = GRAY
15.          v.d = u.d + 1
16.          v.pi = u
17.          ENQUEUE(Q,v)
18.    u.color = BLACK
```
  * Method 2 DFS
      * 
      
 ```
 DFS(G)
1.  for each vertex u ∈ G.V
2.     u.color = WHITE
3.     u.pi = NIL
4.  time = 0
5.  for each vertex u ∈ G.V
6.     if u.color == WHITE
7.        DFS-VISIT(G,u)

DFS-VISIT(G,u)
1.  time = time + 1
2.  u.d = time
3.  u.color = GRAY
4.  for each v ∈ G.Adj[u]
5.     if v.color == WHITE
6.        v.pi = u
7.        DFS-VISIT(G,v)
8.  u.color = BLACK
9.  time = time + 1
10. u.f = time
 ```
