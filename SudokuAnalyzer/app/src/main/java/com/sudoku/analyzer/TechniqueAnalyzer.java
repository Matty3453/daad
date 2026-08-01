package com.sudoku.analyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TechniqueAnalyzer {
    private SudokuGrid grid;
    private List<Technique> techniques;
    
    public TechniqueAnalyzer(SudokuGrid grid) {
        this.grid = grid;
        this.techniques = new ArrayList<>();
    }
    
    public List<Technique> analyzeSolution() {
        techniques.clear();
        SudokuGrid workingGrid = grid.copy();
        
        int maxIterations = 1000;
        int iterations = 0;
        
        while (!isSolved(workingGrid) && iterations < maxIterations) {
            workingGrid.updateCandidates();
            iterations++;
            
            boolean progress = false;
            
            // Livello 1: Tecniche base
            if (findNakedSingles(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findHiddenSingles(workingGrid)) {
                progress = true;
                continue;
            }
            
            // Livello 2: Subset
            if (findNakedPairs(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findHiddenPairs(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findNakedTriples(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findHiddenTriples(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findNakedQuads(workingGrid)) {
                progress = true;
                continue;
            }
            
            // Livello 3: Intersections
            if (findPointingPairs(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findBoxLineReduction(workingGrid)) {
                progress = true;
                continue;
            }
            
            // Livello 4: Fish patterns
            if (findXWing(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findSwordfish(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findJellyfish(workingGrid)) {
                progress = true;
                continue;
            }
            
            // Livello 5: Wings
            if (findYWing(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (findXYZWing(workingGrid)) {
                progress = true;
                continue;
            }
            
            // Livello 6: Coloring
            if (findSimpleColoring(workingGrid)) {
                progress = true;
                continue;
            }
            
            if (!progress) {
                techniques.add(new Technique(
                    Technique.Type.BRUTE_FORCE,
                    "Necessario backtracking o tecniche molto avanzate",
                    -1, -1, 0
                ));
                break;
            }
        }
        
        return techniques;
    }
    
    private boolean findNakedSingles(SudokuGrid grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = grid.getCell(row, col);
                if (cell.isEmpty() && cell.getCandidateCount() == 1) {
                    int value = cell.getCandidates()[0];
                    grid.setValue(row, col, value);
                    techniques.add(new Technique(
                        Technique.Type.NAKED_SINGLE,
                        String.format("R%dC%d: solo %d possibile", 
                                    row + 1, col + 1, value),
                        row, col, value
                    ));
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean findHiddenSingles(SudokuGrid grid) {
        // Righe
        for (int row = 0; row < 9; row++) {
            for (int num = 1; num <= 9; num++) {
                int count = 0;
                int lastCol = -1;
                
                for (int col = 0; col < 9; col++) {
                    SudokuCell cell = grid.getCell(row, col);
                    if (cell.isEmpty() && cell.hasCandidate(num)) {
                        count++;
                        lastCol = col;
                    }
                }
                
                if (count == 1) {
                    grid.setValue(row, lastCol, num);
                    techniques.add(new Technique(
                        Technique.Type.HIDDEN_SINGLE,
                        String.format("R%dC%d: %d unico nella riga %d", 
                                    row + 1, lastCol + 1, num, row + 1),
                        row, lastCol, num
                    ));
                    return true;
                }
            }
        }
        
        // Colonne
        for (int col = 0; col < 9; col++) {
            for (int num = 1; num <= 9; num++) {
                int count = 0;
                int lastRow = -1;
                
                for (int row = 0; row < 9; row++) {
                    SudokuCell cell = grid.getCell(row, col);
                    if (cell.isEmpty() && cell.hasCandidate(num)) {
                        count++;
                        lastRow = row;
                    }
                }
                
                if (count == 1) {
                    grid.setValue(lastRow, col, num);
                    techniques.add(new Technique(
                        Technique.Type.HIDDEN_SINGLE,
                        String.format("R%dC%d: %d unico nella colonna %d", 
                                    lastRow + 1, col + 1, num, col + 1),
                        lastRow, col, num
                    ));
                    return true;
                }
            }
        }
        
        // Box 3x3
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                for (int num = 1; num <= 9; num++) {
                    int count = 0;
                    int lastRow = -1, lastCol = -1;
                    
                    for (int r = boxRow; r < boxRow + 3; r++) {
                        for (int c = boxCol; c < boxCol + 3; c++) {
                            SudokuCell cell = grid.getCell(r, c);
                            if (cell.isEmpty() && cell.hasCandidate(num)) {
                                count++;
                                lastRow = r;
                                lastCol = c;
                            }
                        }
                    }
                    
                    if (count == 1) {
                        grid.setValue(lastRow, lastCol, num);
                        techniques.add(new Technique(
                            Technique.Type.HIDDEN_SINGLE,
                            String.format("R%dC%d: %d unico nel box", 
                                        lastRow + 1, lastCol + 1, num),
                            lastRow, lastCol, num
                        ));
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean findNakedPairs(SudokuGrid grid) {
        return findNakedSubset(grid, 2, Technique.Type.NAKED_PAIR);
    }
    
    private boolean findNakedTriples(SudokuGrid grid) {
        return findNakedSubset(grid, 3, Technique.Type.NAKED_TRIPLE);
    }
    
    private boolean findNakedQuads(SudokuGrid grid) {
        return findNakedSubset(grid, 4, Technique.Type.NAKED_QUAD);
    }
    
    private boolean findNakedSubset(SudokuGrid grid, int size, Technique.Type type) {
        // Righe
        for (int row = 0; row < 9; row++) {
            List<Integer> cells = new ArrayList<>();
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = grid.getCell(row, col);
                if (cell.isEmpty() && cell.getCandidateCount() <= size) {
                    cells.add(col);
                }
            }
            
            if (findNakedSubsetInUnit(grid, cells, row, true, size, type)) {
                return true;
            }
        }
        
        // Colonne
        for (int col = 0; col < 9; col++) {
            List<Integer> cells = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                SudokuCell cell = grid.getCell(row, col);
                if (cell.isEmpty() && cell.getCandidateCount() <= size) {
                    cells.add(row);
                }
            }
            
            if (findNakedSubsetInUnit(grid, cells, col, false, size, type)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean findNakedSubsetInUnit(SudokuGrid grid, List<Integer> cells, int fixedIndex, 
                                          boolean isRow, int size, Technique.Type type) {
        if (cells.size() < size) return false;
        
        return findCombinations(grid, cells, new ArrayList<>(), 0, size, fixedIndex, isRow, type);
    }
    
    private boolean findCombinations(SudokuGrid grid, List<Integer> cells, List<Integer> current,
                                     int start, int size, int fixedIndex, boolean isRow, Technique.Type type) {
        if (current.size() == size) {
            Set<Integer> unionCandidates = new Set<>();
            for (int idx : current) {
                SudokuCell cell = isRow ? grid.getCell(fixedIndex, idx) : grid.getCell(idx, fixedIndex);
                for (int cand : cell.getCandidates()) {
                    unionCandidates.add(cand);
                }
            }
            
            if (unionCandidates.size() == size) {
                boolean eliminated = false;
                for (int i = 0; i < 9; i++) {
                    if (!current.contains(i)) {
                        SudokuCell cell = isRow ? grid.getCell(fixedIndex, i) : grid.getCell(i, fixedIndex);
                        if (cell.isEmpty()) {
                            for (int cand : unionCandidates) {
                                if (cell.hasCandidate(cand)) {
                                    cell.removeCandidate(cand);
                                    eliminated = true;
                                }
                            }
                        }
                    }
                }
                
                if (eliminated) {
                    String location = isRow ? "Riga " + (fixedIndex + 1) : "Colonna " + (fixedIndex + 1);
                    techniques.add(new Technique(type,
                        String.format("%s: Naked subset trovato", location),
                        fixedIndex, current.get(0), 0
                    ));
                    return true;
                }
            }
        }
        
        for (int i = start; i < cells.size(); i++) {
            current.add(cells.get(i));
            if (findCombinations(grid, cells, current, i + 1, size, fixedIndex, isRow, type)) {
                return true;
            }
            current.remove(current.size() - 1);
        }
        
        return false;
    }
    
    private boolean findHiddenPairs(SudokuGrid grid) {
        return findHiddenSubset(grid, 2, Technique.Type.HIDDEN_PAIR);
    }
    
    private boolean findHiddenTriples(SudokuGrid grid) {
        return findHiddenSubset(grid, 3, Technique.Type.HIDDEN_TRIPLE);
    }
    
    private boolean findHiddenSubset(SudokuGrid grid, int size, Technique.Type type) {
        // Implementazione semplificata
        return false;
    }
    
    private boolean findPointingPairs(SudokuGrid grid) {
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                for (int num = 1; num <= 9; num++) {
                    List<int[]> positions = new ArrayList<>();
                    
                    for (int r = boxRow; r < boxRow + 3; r++) {
                        for (int c = boxCol; c < boxCol + 3; c++) {
                            SudokuCell cell = grid.getCell(r, c);
                            if (cell.isEmpty() && cell.hasCandidate(num)) {
                                positions.add(new int[]{r, c});
                            }
                        }
                    }
                    
                    if (positions.size() >= 2 && positions.size() <= 3) {
                        boolean sameRow = true;
                        int firstRow = positions.get(0)[0];
                        for (int[] pos : positions) {
                            if (pos[0] != firstRow) {
                                sameRow = false;
                                break;
                            }
                        }
                        
                        if (sameRow) {
                            boolean eliminated = false;
                            for (int col = 0; col < 9; col++) {
                                int colBox = col / 3;
                                if (colBox != boxCol / 3) {
                                    SudokuCell cell = grid.getCell(firstRow, col);
                                    if (cell.isEmpty() && cell.hasCandidate(num)) {
                                        cell.removeCandidate(num);
                                        eliminated = true;
                                    }
                                }
                            }
                            
                            if (eliminated) {
                                techniques.add(new Technique(
                                    Technique.Type.POINTING_PAIR,
                                    String.format("%d punta alla riga %d dal box", num, firstRow + 1),
                                    firstRow, boxCol, num
                                ));
                                return true;
                            }
                        }
                        
                        boolean sameCol = true;
                        int firstCol = positions.get(0)[1];
                        for (int[] pos : positions) {
                            if (pos[1] != firstCol) {
                                sameCol = false;
                                break;
                            }
                        }
                        
                        if (sameCol) {
                            boolean eliminated = false;
                            for (int row = 0; row < 9; row++) {
                                int rowBox = row / 3;
                                if (rowBox != boxRow / 3) {
                                    SudokuCell cell = grid.getCell(row, firstCol);
                                    if (cell.isEmpty() && cell.hasCandidate(num)) {
                                        cell.removeCandidate(num);
                                        eliminated = true;
                                    }
                                }
                            }
                            
                            if (eliminated) {
                                techniques.add(new Technique(
                                    Technique.Type.POINTING_PAIR,
                                    String.format("%d punta alla colonna %d dal box", num, firstCol + 1),
                                    boxRow, firstCol, num
                                ));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean findBoxLineReduction(SudokuGrid grid) {
        // Righe
        for (int row = 0; row < 9; row++) {
            for (int num = 1; num <= 9; num++) {
                List<Integer> cols = new ArrayList<>();
                
                for (int col = 0; col < 9; col++) {
                    SudokuCell cell = grid.getCell(row, col);
                    if (cell.isEmpty() && cell.hasCandidate(num)) {
                        cols.add(col);
                    }
                }
                
                if (cols.size() >= 2 && cols.size() <= 3) {
                    int firstBox = cols.get(0) / 3;
                    boolean sameBox = true;
                    for (int col : cols) {
                        if (col / 3 != firstBox) {
                            sameBox = false;
                            break;
                        }
                    }
                    
                    if (sameBox) {
                        boolean eliminated = false;
                        int boxRow = (row / 3) * 3;
                        int boxCol = firstBox * 3;
                        
                        for (int r = boxRow; r < boxRow + 3; r++) {
                            if (r != row) {
                                for (int c = boxCol; c < boxCol + 3; c++) {
                                    SudokuCell cell = grid.getCell(r, c);
                                    if (cell.isEmpty() && cell.hasCandidate(num)) {
                                        cell.removeCandidate(num);
                                        eliminated = true;
                                    }
                                }
                            }
                        }
                        
                        if (eliminated) {
                            techniques.add(new Technique(
                                Technique.Type.BOX_LINE_REDUCTION,
                                String.format("Riga %d: %d limitato al box", row + 1, num),
                                row, boxCol, num
                            ));
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean findXWing(SudokuGrid grid) {
        return findFishPattern(grid, 2, Technique.Type.X_WING);
    }
    
    private boolean findSwordfish(SudokuGrid grid) {
        return findFishPattern(grid, 3, Technique.Type.SWORDFISH);
    }
    
    private boolean findJellyfish(SudokuGrid grid) {
        return findFishPattern(grid, 4, Technique.Type.JELLYFISH);
    }
    
    private boolean findFishPattern(SudokuGrid grid, int size, Technique.Type type) {
        // X-Wing: 2 righe, stessi 2 colonne
        for (int num = 1; num <= 9; num++) {
            List<Integer> rows = new ArrayList<>();
            List<List<Integer>> rowCols = new ArrayList<>();
            
            for (int row = 0; row < 9; row++) {
                List<Integer> cols = new ArrayList<>();
                for (int col = 0; col < 9; col++) {
                    SudokuCell cell = grid.getCell(row, col);
                    if (cell.isEmpty() && cell.hasCandidate(num)) {
                        cols.add(col);
                    }
                }
                if (cols.size() >= 2 && cols.size() <= size) {
                    rows.add(row);
                    rowCols.add(cols);
                }
            }
            
            if (rows.size() >= size) {
                if (checkFishCombinations(grid, rows, rowCols, size, num, type, true)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean checkFishCombinations(SudokuGrid grid, List<Integer> lines, 
                                          List<List<Integer>> lineCands, int size, int num, 
                                          Technique.Type type, boolean isRow) {
        // Implementazione semplificata per X-Wing base
        if (size == 2 && lines.size() >= 2) {
            for (int i = 0; i < lines.size(); i++) {
                for (int j = i + 1; j < lines.size(); j++) {
                    List<Integer> cols1 = lineCands.get(i);
                    List<Integer> cols2 = lineCands.get(j);
                    
                    if (cols1.size() == 2 && cols2.size() == 2) {
                        if (cols1.get(0).equals(cols2.get(0)) && cols1.get(1).equals(cols2.get(1))) {
                            int col1 = cols1.get(0);
                            int col2 = cols1.get(1);
                            int row1 = lines.get(i);
                            int row2 = lines.get(j);
                            
                            boolean eliminated = false;
                            for (int row = 0; row < 9; row++) {
                                if (row != row1 && row != row2) {
                                    if (grid.getCell(row, col1).hasCandidate(num)) {
                                        grid.getCell(row, col1).removeCandidate(num);
                                        eliminated = true;
                                    }
                                    if (grid.getCell(row, col2).hasCandidate(num)) {
                                        grid.getCell(row, col2).removeCandidate(num);
                                        eliminated = true;
                                    }
                                }
                            }
                            
                            if (eliminated) {
                                techniques.add(new Technique(type,
                                    String.format("X-Wing su %d: righe %d,%d colonne %d,%d", 
                                                num, row1+1, row2+1, col1+1, col2+1),
                                    row1, col1, num
                                ));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean findYWing(SudokuGrid grid) {
        // Y-Wing: 3 celle con pattern XY-XZ-YZ
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell pivot = grid.getCell(row, col);
                if (pivot.isEmpty() && pivot.getCandidateCount() == 2) {
                    int[] pivotCands = pivot.getCandidates();
                    
                    // Cerca due pinze con i candidati giusti
                    List<int[]> pincers = new ArrayList<>();
                    
                    for (int r = 0; r < 9; r++) {
                        for (int c = 0; c < 9; c++) {
                            if (r == row && c == col) continue;
                            
                            SudokuCell cell = grid.getCell(r, c);
                            if (cell.isEmpty() && cell.getCandidateCount() == 2) {
                                int[] cands = cell.getCandidates();
                                // Deve condividere esattamente 1 candidato con pivot
                                boolean shares0 = cands[0] == pivotCands[0] || cands[0] == pivotCands[1];
                                boolean shares1 = cands[1] == pivotCands[0] || cands[1] == pivotCands[1];
                                
                                if ((shares0 && !shares1) || (!shares0 && shares1)) {
                                    if (canSee(row, col, r, c)) {
                                        pincers.add(new int[]{r, c});
                                    }
                                }
                            }
                        }
                    }
                    
                    // Controlla coppie di pinze
                    for (int i = 0; i < pincers.size(); i++) {
                        for (int j = i + 1; j < pincers.size(); j++) {
                            int[] p1 = pincers.get(i);
                            int[] p2 = pincers.get(j);
                            
                            SudokuCell pincer1 = grid.getCell(p1[0], p1[1]);
                            SudokuCell pincer2 = grid.getCell(p2[0], p2[1]);
                            
                            int[] c1 = pincer1.getCandidates();
                            int[] c2 = pincer2.getCandidates();
                            
                            // Trova il candidato comune alle pinze (non in pivot)
                            for (int cand1 : c1) {
                                for (int cand2 : c2) {
                                    if (cand1 == cand2) {
                                        boolean inPivot = false;
                                        for (int pc : pivotCands) {
                                            if (pc == cand1) {
                                                inPivot = true;
                                                break;
                                            }
                                        }
                                        
                                        if (!inPivot) {
                                            // Y-Wing trovato! Elimina cand1 dalle celle che vedono entrambe le pinze
                                            boolean eliminated = eliminateYWing(grid, p1, p2, cand1);
                                            if (eliminated) {
                                                techniques.add(new Technique(
                                                    Technique.Type.Y_WING,
                                                    String.format("Y-Wing elimina %d", cand1),
                                                    row, col, cand1
                                                ));
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean canSee(int r1, int c1, int r2, int c2) {
        // Stessa riga, colonna o box
        if (r1 == r2 || c1 == c2) return true;
        if ((r1/3 == r2/3) && (c1/3 == c2/3)) return true;
        return false;
    }
    
    private boolean eliminateYWing(SudokuGrid grid, int[] p1, int[] p2, int cand) {
        boolean eliminated = false;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if ((r == p1[0] && c == p1[1]) || (r == p2[0] && c == p2[1])) continue;
                
                if (canSee(r, c, p1[0], p1[1]) && canSee(r, c, p2[0], p2[1])) {
                    SudokuCell cell = grid.getCell(r, c);
                    if (cell.isEmpty() && cell.hasCandidate(cand)) {
                        cell.removeCandidate(cand);
                        eliminated = true;
                    }
                }
            }
        }
        return eliminated;
    }
    
    private boolean findXYZWing(SudokuGrid grid) {
        // XYZ-Wing: cella pivot con 3 candidati XYZ
        // Tre wing cells con XY, XZ, YZ che vedono pivot
        return false; // Implementazione complessa
    }
    
    private boolean findSimpleColoring(SudokuGrid grid) {
        // Simple Coloring: trova chain di coppie coniugate
        return false; // Implementazione complessa
    }
    
    private boolean isSolved(SudokuGrid grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid.getCell(row, col).isEmpty()) {
                    return false;
                }
            }
        }
        return grid.isValid();
    }
    
    public List<Technique> getTechniques() {
        return techniques;
    }
}
