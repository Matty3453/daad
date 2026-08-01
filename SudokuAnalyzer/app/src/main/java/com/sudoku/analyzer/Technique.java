package com.sudoku.analyzer;

public class Technique {
    public enum Type {
        NAKED_SINGLE,
        HIDDEN_SINGLE,
        NAKED_PAIR,
        NAKED_TRIPLE,
        NAKED_QUAD,
        HIDDEN_PAIR,
        HIDDEN_TRIPLE,
        HIDDEN_QUAD,
        POINTING_PAIR,
        POINTING_TRIPLE,
        BOX_LINE_REDUCTION,
        X_WING,
        SWORDFISH,
        JELLYFISH,
        Y_WING,
        XY_CHAIN,
        XYZ_WING,
        WXYZ_WING,
        SIMPLE_COLORING,
        MULTI_COLORING,
        UNIQUE_RECTANGLE,
        BRUTE_FORCE
    }
    
    private Type type;
    private String description;
    private int row;
    private int col;
    private int value;
    
    public Technique(Type type, String description, int row, int col, int value) {
        this.type = type;
        this.description = description;
        this.row = row;
        this.col = col;
        this.value = value;
    }
    
    public Type getType() {
        return type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getTypeName() {
        switch (type) {
            case NAKED_SINGLE:
                return "Naked Single";
            case HIDDEN_SINGLE:
                return "Hidden Single";
            case NAKED_PAIR:
                return "Naked Pair";
            case NAKED_TRIPLE:
                return "Naked Triple";
            case NAKED_QUAD:
                return "Naked Quad";
            case HIDDEN_PAIR:
                return "Hidden Pair";
            case HIDDEN_TRIPLE:
                return "Hidden Triple";
            case HIDDEN_QUAD:
                return "Hidden Quad";
            case POINTING_PAIR:
                return "Pointing Pair";
            case POINTING_TRIPLE:
                return "Pointing Triple";
            case BOX_LINE_REDUCTION:
                return "Box/Line Reduction";
            case X_WING:
                return "X-Wing";
            case SWORDFISH:
                return "Swordfish";
            case JELLYFISH:
                return "Jellyfish";
            case Y_WING:
                return "Y-Wing";
            case XY_CHAIN:
                return "XY-Chain";
            case XYZ_WING:
                return "XYZ-Wing";
            case WXYZ_WING:
                return "WXYZ-Wing";
            case SIMPLE_COLORING:
                return "Simple Coloring";
            case MULTI_COLORING:
                return "Multi-Coloring";
            case UNIQUE_RECTANGLE:
                return "Unique Rectangle";
            case BRUTE_FORCE:
                return "Brute Force";
            default:
                return "Unknown";
        }
    }
    
    public String getEmoji() {
        switch (type) {
            case NAKED_SINGLE:
                return "🎯";
            case HIDDEN_SINGLE:
                return "🔍";
            case NAKED_PAIR:
            case NAKED_TRIPLE:
            case NAKED_QUAD:
                return "👥";
            case HIDDEN_PAIR:
            case HIDDEN_TRIPLE:
            case HIDDEN_QUAD:
                return "🕵️";
            case POINTING_PAIR:
            case POINTING_TRIPLE:
                return "👉";
            case BOX_LINE_REDUCTION:
                return "📦";
            case X_WING:
            case SWORDFISH:
            case JELLYFISH:
                return "🐟";
            case Y_WING:
            case XY_CHAIN:
            case XYZ_WING:
            case WXYZ_WING:
                return "🔗";
            case SIMPLE_COLORING:
            case MULTI_COLORING:
                return "🎨";
            case UNIQUE_RECTANGLE:
                return "⬜";
            case BRUTE_FORCE:
                return "💪";
            default:
                return "❓";
        }
    }
    
    public int getDifficulty() {
        switch (type) {
            case NAKED_SINGLE:
                return 1;
            case HIDDEN_SINGLE:
                return 2;
            case NAKED_PAIR:
            case POINTING_PAIR:
                return 3;
            case NAKED_TRIPLE:
            case HIDDEN_PAIR:
            case BOX_LINE_REDUCTION:
                return 4;
            case POINTING_TRIPLE:
            case HIDDEN_TRIPLE:
            case NAKED_QUAD:
                return 5;
            case X_WING:
            case HIDDEN_QUAD:
                return 6;
            case SWORDFISH:
            case Y_WING:
                return 7;
            case XYZ_WING:
            case SIMPLE_COLORING:
                return 8;
            case JELLYFISH:
            case XY_CHAIN:
            case WXYZ_WING:
            case MULTI_COLORING:
            case UNIQUE_RECTANGLE:
                return 9;
            case BRUTE_FORCE:
                return 10;
            default:
                return 0;
        }
    }
    
    @Override
    public String toString() {
        return getTypeName() + ": " + description;
    }
}
