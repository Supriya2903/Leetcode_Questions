class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int fresh =0;

        for(int r = 0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(grid[r][c] == 2){
                    queue.add(new int[]{r, c});
                }else if(grid[r][c] == 1){
                    fresh++;
                }
            }
        }
        if(fresh == 0) return 0;

        int minute =-1;
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!queue.isEmpty()){
            int size = queue.size();
            minute++;

            for(int i =0; i<size; i++){
                int[] orange = queue.poll();
                for(int[] d: directions){
                    int nr = orange[0]+ d[0];
                    int nc = orange[1]+ d[1];
            
            if(nr>=0 && nr<rows && nc>=0 && nc<cols && grid[nr][nc] == 1){
                grid[nr][nc] = 2;
                fresh--;
                queue.add(new int[]{nr, nc});


            }

                }
                
            }
        }
        return fresh == 0 ? minute: -1;
    }
}