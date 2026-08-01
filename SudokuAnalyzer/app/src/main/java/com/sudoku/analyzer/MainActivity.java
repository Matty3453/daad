package com.sudoku.analyzer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridLayout sudokuGrid;
    private EditText[][] cells;
    private MaterialButton btnAnalyze, btnClear, btnExample;
    private TextView tvResults, tvDifficulty, tvTechniqueCount, tvMaxDifficulty;
    private MaterialCardView cardResults, cardGrid;
    private FrameLayout loadingOverlay;
    private LinearProgressIndicator progressBar;
    private LinearLayout statsContainer;
    private SudokuGrid grid;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper());

        initViews();
        setupSudokuGrid();
        setupButtons();
        animateEntrance();
    }

    private void initViews() {
        sudokuGrid = findViewById(R.id.sudokuGrid);
        btnAnalyze = findViewById(R.id.btnAnalyze);
        btnClear = findViewById(R.id.btnClear);
        btnExample = findViewById(R.id.btnExample);
        tvResults = findViewById(R.id.tvResults);
        tvDifficulty = findViewById(R.id.tvDifficulty);
        tvTechniqueCount = findViewById(R.id.tvTechniqueCount);
        tvMaxDifficulty = findViewById(R.id.tvMaxDifficulty);
        cardResults = findViewById(R.id.cardResults);
        cardGrid = findViewById(R.id.cardGrid);
        loadingOverlay = findViewById(R.id.loadingOverlay);
        progressBar = findViewById(R.id.progressBar);
        statsContainer = findViewById(R.id.statsContainer);

        grid = new SudokuGrid();
        cells = new EditText[9][9];
    }

    private void animateEntrance() {
        // Animate card entrance
        cardGrid.setAlpha(0f);
        cardGrid.setTranslationY(100f);
        cardGrid.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(600)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        // Animate button entrance with delay
        btnAnalyze.setAlpha(0f);
        btnAnalyze.setScaleX(0.8f);
        btnAnalyze.setScaleY(0.8f);
        btnAnalyze.postDelayed(() -> {
            btnAnalyze.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500)
                    .setInterpolator(new OvershootInterpolator())
                    .start();
        }, 300);
    }

    private void setupSudokuGrid() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int cellSize = (screenWidth - 140) / 9;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                EditText cell = new EditText(this);
                
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = cellSize;
                params.height = cellSize;
                params.rowSpec = GridLayout.spec(row);
                params.columnSpec = GridLayout.spec(col);
                
                int margin = 1;
                if (row % 3 == 0 && row != 0) margin = 4;
                params.topMargin = margin;
                
                if (col % 3 == 0 && col != 0) params.leftMargin = 4;
                else params.leftMargin = 1;
                
                cell.setLayoutParams(params);
                cell.setGravity(Gravity.CENTER);
                cell.setInputType(InputType.TYPE_CLASS_NUMBER);
                cell.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
                cell.setBackgroundResource(R.drawable.cell_background);
                cell.setTextSize(20);
                cell.setTextColor(ContextCompat.getColor(this, R.color.text_primary));
                
                // Add animation on focus
                final int finalRow = row;
                final int finalCol = col;
                cell.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) {
                        animateCell((EditText) v, true);
                    }
                });
                
                cells[row][col] = cell;
                sudokuGrid.addView(cell);
                
                // Animate cells entrance
                cell.setAlpha(0f);
                cell.setScaleX(0.3f);
                cell.setScaleY(0.3f);
                final int delay = (row * 9 + col) * 8;
                cell.postDelayed(() -> {
                    cell.animate()
                            .alpha(1f)
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(300)
                            .setInterpolator(new OvershootInterpolator())
                            .start();
                }, delay);
            }
        }
    }

    private void animateCell(EditText cell, boolean selected) {
        if (selected) {
            cell.animate()
                    .scaleX(1.1f)
                    .scaleY(1.1f)
                    .setDuration(200)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        } else {
            cell.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(200)
                    .start();
        }
    }

    private void setupButtons() {
        btnClear.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_in));
            clearGrid();
        });
        
        btnExample.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_in));
            loadExample();
        });
        
        btnAnalyze.setOnClickListener(v -> {
            animateButtonPress(v);
            handler.postDelayed(this::analyzeGrid, 200);
        });
    }

    private void animateButtonPress(View button) {
        button.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> {
                    button.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                })
                .start();
    }

    private void clearGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final EditText cell = cells[row][col];
                final int delay = (row * 9 + col) * 5;
                
                cell.postDelayed(() -> {
                    cell.setText("");
                    cell.setBackgroundResource(R.drawable.cell_background);
                    
                    ObjectAnimator.ofFloat(cell, "rotation", 0f, 360f)
                            .setDuration(300)
                            .start();
                }, delay);
            }
        }
        
        hideResultsWithAnimation();
    }

    private void loadExample() {
        int[][] example = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final EditText cell = cells[row][col];
                final int value = example[row][col];
                final int delay = (row * 9 + col) * 10;
                
                cell.postDelayed(() -> {
                    if (value != 0) {
                        cell.setText(String.valueOf(value));
                    } else {
                        cell.setText("");
                    }
                    
                    cell.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .setDuration(150)
                            .withEndAction(() -> {
                                cell.animate()
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(150)
                                        .start();
                            })
                            .start();
                }, delay);
            }
        }

        showToastAnimation("✨ Esempio caricato!");
    }

    private void analyzeGrid() {
        grid = new SudokuGrid();
        boolean hasValues = false;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText().toString();
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value >= 1 && value <= 9) {
                            grid.setCell(row, col, value, true);
                            hasValues = true;
                        }
                    } catch (NumberFormatException e) {
                        // Ignora
                    }
                }
            }
        }

        if (!hasValues) {
            showToastAnimation("❌ Inserisci almeno alcuni numeri!");
            shakeView(cardGrid);
            return;
        }

        if (!grid.isValid()) {
            showToastAnimation("❌ La griglia contiene errori!");
            shakeView(cardGrid);
            flashErrors();
            return;
        }

        showLoadingAnimation();
        
        // Simula analisi con delay per mostrare loading
        handler.postDelayed(() -> {
            performAnalysis();
        }, 1500);
    }

    private void performAnalysis() {
        TechniqueAnalyzer analyzer = new TechniqueAnalyzer(grid);
        List<Technique> techniques = analyzer.analyzeSolution();

        hideLoadingAnimation();

        if (techniques.isEmpty()) {
            tvResults.setText("✅ La griglia è già completa o non necessita tecniche aggiuntive.");
            showResultsWithAnimation();
        } else {
            displayTechniquesAnimated(techniques);
        }
    }

    private void displayTechniquesAnimated(List<Technique> techniques) {
        StringBuilder result = new StringBuilder();
        
        // Statistiche
        int totalTechniques = techniques.size();
        int maxDiff = 0;
        int[] counts = new int[22];
        
        for (Technique t : techniques) {
            counts[t.getType().ordinal()]++;
            if (t.getDifficulty() > maxDiff) {
                maxDiff = t.getDifficulty();
            }
        }

        // Riepilogo
        result.append("🎯 RIEPILOGO TECNICHE\n\n");
        
        for (Technique.Type type : Technique.Type.values()) {
            int count = counts[type.ordinal()];
            if (count > 0) {
                Technique sample = new Technique(type, "", 0, 0, 0);
                result.append(sample.getEmoji()).append(" ")
                      .append(sample.getTypeName())
                      .append(": ").append(count).append("×")
                      .append(" (Diff: ").append(sample.getDifficulty()).append("/10)\n");
            }
        }

        result.append("\n📝 DETTAGLI PASSO-PASSO\n\n");
        int step = 1;
        for (Technique t : techniques) {
            result.append(String.format("%d. %s %s\n", step++, t.getEmoji(), t.getTypeName()));
            result.append("   ").append(t.getDescription()).append("\n\n");
        }

        String difficulty = evaluateDifficulty(techniques, maxDiff);
        result.append("\n🏆 VALUTAZIONE FINALE\n");
        result.append("Difficoltà: ").append(difficulty).append("\n");
        result.append("Punteggio: ").append(maxDiff).append("/10");

        tvResults.setText(result.toString());
        
        // Update stats
        tvTechniqueCount.setText(String.valueOf(totalTechniques));
        tvMaxDifficulty.setText(String.valueOf(maxDiff));
        
        // Set difficulty badge color
        int diffColor = getDifficultyColor(maxDiff);
        tvDifficulty.setText(difficulty);
        tvDifficulty.setBackgroundColor(diffColor);
        
        showResultsWithAnimation();
        animateStats(totalTechniques, maxDiff);
    }

    private String evaluateDifficulty(List<Technique> techniques, int maxDiff) {
        if (maxDiff >= 10) return "💀 Estremo";
        if (maxDiff >= 8) return "🔥 Esperto";
        if (maxDiff >= 6) return "⚡ Difficile";
        if (maxDiff >= 4) return "📈 Medio";
        if (maxDiff >= 2) return "🌱 Facile";
        return "👶 Principiante";
    }

    private int getDifficultyColor(int maxDiff) {
        if (maxDiff >= 10) return ContextCompat.getColor(this, R.color.diff_extreme);
        if (maxDiff >= 8) return ContextCompat.getColor(this, R.color.diff_expert);
        if (maxDiff >= 6) return ContextCompat.getColor(this, R.color.diff_hard);
        if (maxDiff >= 4) return ContextCompat.getColor(this, R.color.diff_medium);
        return ContextCompat.getColor(this, R.color.diff_easy);
    }

    private void showResultsWithAnimation() {
        cardResults.setVisibility(View.VISIBLE);
        cardResults.setAlpha(0f);
        cardResults.setTranslationY(100f);
        
        cardResults.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(600)
                .setInterpolator(new DecelerateInterpolator())
                .start();
                
        statsContainer.setVisibility(View.VISIBLE);
    }

    private void hideResultsWithAnimation() {
        if (cardResults.getVisibility() == View.VISIBLE) {
            cardResults.animate()
                    .alpha(0f)
                    .translationY(100f)
                    .setDuration(400)
                    .withEndAction(() -> {
                        cardResults.setVisibility(View.GONE);
                        statsContainer.setVisibility(View.GONE);
                    })
                    .start();
        }
    }

    private void animateStats(int techniques, int maxDiff) {
        animateNumber(tvTechniqueCount, 0, techniques, 800);
        handler.postDelayed(() -> {
            animateNumber(tvMaxDifficulty, 0, maxDiff, 800);
        }, 200);
    }

    private void animateNumber(TextView textView, int start, int end, long duration) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            textView.setText(String.valueOf(animation.getAnimatedValue()));
        });
        animator.start();
        
        textView.animate()
                .scaleX(1.3f)
                .scaleY(1.3f)
                .setDuration(200)
                .withEndAction(() -> {
                    textView.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(300)
                            .setInterpolator(new BounceInterpolator())
                            .start();
                })
                .start();
    }

    private void showLoadingAnimation() {
        loadingOverlay.setVisibility(View.VISIBLE);
        loadingOverlay.setAlpha(0f);
        loadingOverlay.animate()
                .alpha(1f)
                .setDuration(300)
                .start();
    }

    private void hideLoadingAnimation() {
        loadingOverlay.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction(() -> loadingOverlay.setVisibility(View.GONE))
                .start();
    }

    private void shakeView(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 
                0, 25, -25, 25, -25, 15, -15, 6, -6, 0);
        animator.setDuration(500);
        animator.start();
    }

    private void flashErrors() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final EditText cell = cells[row][col];
                if (!cell.getText().toString().isEmpty()) {
                    ValueAnimator colorAnim = ValueAnimator.ofArgb(
                            Color.WHITE, 
                            ContextCompat.getColor(this, R.color.cell_error),
                            Color.WHITE
                    );
                    colorAnim.setDuration(600);
                    colorAnim.addUpdateListener(animation -> {
                        cell.setBackgroundColor((int) animation.getAnimatedValue());
                    });
                    colorAnim.start();
                }
            }
        }
    }

    private void showToastAnimation(String message) {
        // Custom toast with animation - simplified version
        // In produzione usare Snackbar
        runOnUiThread(() -> {
            android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show();
        });
    }
}
