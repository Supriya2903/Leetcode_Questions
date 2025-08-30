class Solution {
    public boolean isValidSudoku(char[][] board) {
         // Create sets for rows, columns, and boxes
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];
        
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        
        // Traverse the board
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                
                if (ch == '.') continue; // skip empty cells
                
                // Box index calculation
                int boxIndex = (r / 3) * 3 + (c / 3);
                
                // Check for duplicates
                if (rows[r].contains(ch) || cols[c].contains(ch) || boxes[boxIndex].contains(ch)) {
                    return false;
                }
                
                // Add digit to respective sets
                rows[r].add(ch);
                cols[c].add(ch);
                boxes[boxIndex].add(ch);
            }
        }
        return true;
    }
}