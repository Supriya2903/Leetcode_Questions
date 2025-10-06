import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        // Min-heap storing [elevation, row, col]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        pq.offer(new int[]{grid[0][0], 0, 0});
        int res = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int elevation = curr[0];
            int i = curr[1];
            int j = curr[2];
            
            // Keep track of max elevation we've stepped on
            res = Math.max(res, elevation);
            
            // If we've reached bottom-right, return result
            if (i == n - 1 && j == n - 1) return res;
            
            if (visited[i][j]) continue;
            visited[i][j] = true;
            
            // Explore neighbors
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                
                if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
                    pq.offer(new int[]{grid[x][y], x, y});
                }
            }
        }
        
        return res; // unreachable theoretically
    }
}
