Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.
* Start from the edge, mark all the connection of edge O with E, mark all the remaining O with X. Then mark E as O.
* Note: can put all the edge O in to the queue, instead of calling helper function several times.

```java
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        LinkedList<Pair> q = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') { 
                q.offerFirst(new Pair(i, 0));
                board[i][0] = 'E';
            }
            if (board[i][board[0].length - 1] == 'O') { 
                q.offerFirst(new Pair(i, board[0].length - 1));
                board[i][board[0].length - 1] = 'E';
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') { 
                q.offerFirst(new Pair(0, i));
                board[0][i] = 'E';
            }
            if (board[board.length - 1][i] == 'O') { 
                q.offerFirst(new Pair(board.length - 1, i));
                board[board.length - 1][i] = 'E';
            }
        }
        helper(board, q);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
        return;
    }
    private void helper(char[][] board, LinkedList<Pair> q) {
               int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
               while (!q.isEmpty()) {
                   Pair cur = q.pollLast();
                   int x = cur.x;
                   int y = cur.y;
                   for (int k = 0; k < dir.length; k++) {
                        if (x + dir[k][0] >= 0 && x + dir[k][0] < board.length && y + dir[k][1] >= 0 && y + dir[k][1] < board[0].length && board[x + dir[k][0]][y + dir[k][1]] == 'O') {
                            q.offerFirst(new Pair(x + dir[k][0], y + dir[k][1]));
                            board[x + dir[k][0]][y + dir[k][1]] = 'E';
                        }
                       
                   }
               }
          
    }
    class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
```
Method 2 DFS:
```java
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') { 
                dfs(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') { 
                dfs(board, i, board[0].length - 1);
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') { 
                dfs(board, 0, i);
            }
            if (board[board.length - 1][i] == 'O') { 
                dfs(board, board.length - 1, i);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
        return;
    }
    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1 || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'E';
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }

}
```

