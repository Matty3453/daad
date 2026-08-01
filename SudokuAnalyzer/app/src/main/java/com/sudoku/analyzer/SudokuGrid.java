package com.sudoku.analyzer;

public class SudokuGrid {
    private SudokuCell[][] grid;
    
    public SudokuGrid() {
        grid = new SudokuCell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new SudokuCell();
            }
        }
    }
    
    public void setCell(int row, int col, int value, boolean isFixed) {
        if (isValidPosition(row, col)) {
            grid[row][col] = new SudokuCell(value, isFixed);
        }
    }
    
    public SudokuCell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            return grid[row][col];
        }
        return null;
    }
    
    public int getValue(int row, int col) {
        if (isValidPosition(row, col)) {
            return grid[row][col].getValue();
        }
        return 0;
    }
    
    public void setValue(int row, int col, int value) {
        if (isValidPosition(row, col)) {
            grid[row][col].setValue(value);
        }
    }
    
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 9 && col >= 0 && col < 9;
    }
    
    public void updateCandidates() {
        // Prima resetta tutti i candidati per le celle vuote
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col].isEmpty()) {
                    for (int num = 1; num <= 9; num++) {
                        grid[row][col].addCandidate(num);
                    }
                }
            }
        }
        
        // Rimuovi candidati basati sui valori esistenti
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = grid[row][col].getValue();
                if (value != 0) {
                    eliminateCandidatesForValue(row, col, value);
                }
            }
        }
    }
    
    private void eliminateCandidatesForValue(int row, int col, int value) {
        // Elimina dalla riga
        for (int c = 0; c < 9; c++) {
            if (c != col) {
                grid[row][c].removeCandidate(value);
            }
        }
        
        // Elimina dalla colonna
        for (int r = 0; r < 9; r++) {
            if (r != row) {
                grid[r][col].removeCandidate(value);
            }
        }
        
        // Elimina dal box 3x3
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (r != row || c != col) {
                    grid[r][c].removeCandidate(value);
                }
            }
        }
    }
    
    public boolean isValid() {
        // Controlla righe
        for (int row = 0; row < 9; row++) {
            if (!isRowValid(row)) return false;
        }
        
        // Controlla colonne
        for (int col = 0; col < 9; col++) {
            if (!isColumnValid(col)) return false;
        }
        
        // Controlla box 3x3
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                if (!isBoxValid(boxRow, boxCol)) return false;
            }
        }
        
        return true;
    }
    
    private boolean isRowValid(int row) {
        boolean[] seen = new boolean[10];
        for (int col = 0; col < 9; col++) {
            int value = grid[row][col].getValue();
            if (value != 0) {
                if (seen[value]) return false;
                seen[value] = true;
            }
        }
        return true;
    }
    
    private boolean isColumnValid(int col) {
        boolean[] seen = new boolean[10];
        for (int row = 0; row < 9; row++) {
            int value = grid[row][col].getValue();
            if (value != 0) {
                if (seen[value]) return false;
                seen[value] = true;
            }
        }
        return true;
    }
    
    private boolean isBoxValid(int startRow, int startCol) {
        boolean[] seen = new boolean[10];
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                int value = grid[row][col].getValue();
                if (value != 0) {
                    if (seen[value]) return false;
                    seen[value] = true;
                }
            }
        }
        return true;
    }
    
    public SudokuGrid copy() {
        SudokuGrid newGrid = new SudokuGrid();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = this.getValue(row, col);
                boolean isFixed = this.grid[row][col].isFixed();
                newGrid.setCell(row, col, value, isFixed);
            }
        }
        return newGrid;
    }
}
