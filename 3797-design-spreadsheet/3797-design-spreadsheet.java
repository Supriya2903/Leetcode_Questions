class Spreadsheet {
    private int[][] grid; // rows x 26
    private int rows;

    public Spreadsheet(int rows) {
        this.rows = rows;
        this.grid = new int[rows][26]; // all initialized to 0
    }

    public void setCell(String cell, int value) {
        int[] pos = parseCell(cell);
        grid[pos[0]][pos[1]] = value;
    }

    public void resetCell(String cell) {
        int[] pos = parseCell(cell);
        grid[pos[0]][pos[1]] = 0;
    }

    public int getValue(String formula) {
        // formula format: "=X+Y"
        String expr = formula.substring(1); // remove '='
        String[] parts = expr.split("\\+");

        int val1 = getOperandValue(parts[0]);
        int val2 = getOperandValue(parts[1]);

        return val1 + val2;
    }

    // ---- helpers ----
    private int getOperandValue(String operand) {
        // If operand starts with A-Z → it's a cell
        if (Character.isLetter(operand.charAt(0))) {
            int[] pos = parseCell(operand);
            return grid[pos[0]][pos[1]];
        } else {
            // it's an integer
            return Integer.parseInt(operand);
        }
    }

    private int[] parseCell(String cell) {
        // e.g. "B2"
        char colChar = cell.charAt(0);
        int col = colChar - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        return new int[]{row, col};
    }
}
