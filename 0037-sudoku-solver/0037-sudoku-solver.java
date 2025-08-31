class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') { // empty cell
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // place number
                            if (solve(board)) {
                                return true; // solved
                            }
                            board[row][col] = '.'; // backtrack
                        }
                    }
                    return false; // no number fits here
                }
            }
        }
        return true; // all cells filled
    }
    
    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            // check row
            if (board[row][i] == num) return false;
            // check column
            if (board[i][col] == num) return false;
            // check 3x3 box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == num) return false;
        }
        return true;
    }
}
