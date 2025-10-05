import java.util.*;

class Solution {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || heights.length == 0) return res;

        m = heights.length;
        n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // DFS from Pacific (top and left)
        for (int i = 0; i < m; i++) dfs(heights, pacific, i, 0, heights[i][0]);
        for (int j = 0; j < n; j++) dfs(heights, pacific, 0, j, heights[0][j]);

        // DFS from Atlantic (bottom and right)
        for (int i = 0; i < m; i++) dfs(heights, atlantic, i, n - 1, heights[i][n - 1]);
        for (int j = 0; j < n; j++) dfs(heights, atlantic, m - 1, j, heights[m - 1][j]);

        // Cells reachable from both oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] heights, boolean[][] visited, int i, int j, int prevHeight) {
        // Out of bounds or already visited or height too low
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || heights[i][j] < prevHeight) return;

        visited[i][j] = true;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            dfs(heights, visited, x, y, heights[i][j]);
        }
    }
}
