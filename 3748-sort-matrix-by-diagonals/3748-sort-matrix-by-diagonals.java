class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagonals = new HashMap<>();
        
        // Step 1: Collect elements by diagonal
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d = i - j;
                diagonals.putIfAbsent(d, new ArrayList<>());
                diagonals.get(d).add(grid[i][j]);
            }
        }
        
        // Step 2: Sort each diagonal according to rule
        for (int d : diagonals.keySet()) {
            List<Integer> diag = diagonals.get(d);
            if (d >= 0) {
                // bottom-left including main diagonal → descending
                diag.sort(Collections.reverseOrder());
            } else {
                // top-right → ascending
                Collections.sort(diag);
            }
        }
        
        // Step 3: Refill grid with sorted diagonals
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d = i - j;
                grid[i][j] = diagonals.get(d).remove(0);
            }
        }
        
        return grid;
    }
}