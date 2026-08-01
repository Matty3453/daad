package com.sudoku.analyzer;

public class SudokuCell {
    private int value; // 0 significa cella vuota
    private boolean[] candidates; // possibili valori (1-9)
    private boolean isFixed; // se è un valore iniziale
    
    public SudokuCell() {
        this.value = 0;
        this.candidates = new boolean[10]; // index 0 non usato, 1-9 per i valori
        for (int i = 1; i <= 9; i++) {
            candidates[i] = true;
        }
        this.isFixed = false;
    }
    
    public SudokuCell(int value, boolean isFixed) {
        this();
        if (value >= 1 && value <= 9) {
            this.value = value;
            this.isFixed = isFixed;
            // Se ha un valore, nessun candidato è possibile
            for (int i = 1; i <= 9; i++) {
                candidates[i] = false;
            }
        }
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        if (!isFixed) {
            this.value = value;
            if (value != 0) {
                for (int i = 1; i <= 9; i++) {
                    candidates[i] = false;
                }
            }
        }
    }
    
    public boolean isEmpty() {
        return value == 0;
    }
    
    public boolean isFixed() {
        return isFixed;
    }
    
    public void setFixed(boolean fixed) {
        this.isFixed = fixed;
    }
    
    public boolean hasCandidate(int num) {
        if (num < 1 || num > 9) return false;
        return candidates[num];
    }
    
    public void removeCandidate(int num) {
        if (num >= 1 && num <= 9) {
            candidates[num] = false;
        }
    }
    
    public void addCandidate(int num) {
        if (num >= 1 && num <= 9 && value == 0) {
            candidates[num] = true;
        }
    }
    
    public int getCandidateCount() {
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (candidates[i]) count++;
        }
        return count;
    }
    
    public int[] getCandidates() {
        int count = getCandidateCount();
        int[] result = new int[count];
        int idx = 0;
        for (int i = 1; i <= 9; i++) {
            if (candidates[i]) {
                result[idx++] = i;
            }
        }
        return result;
    }
}
