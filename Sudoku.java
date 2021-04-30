/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author s.zhuang
 */
public class Sudoku extends JFrame {
    private InputSquare[][] grid;
    private JLabel message;
    private InputSquare selectedSquare;
    private boolean finished = false;
    
    static int ROWS = 9;
    static int COLS = 9;
    
    static int INROWS = 3;
    static int INCOLS = 3;
    
    static int OUTROWS = 3;
    static int OUTCOLS = 3;
    
    public Sudoku() {
        super("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
                
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(OUTROWS, OUTCOLS));
        
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        gridPanel.setBorder(blackline);

        grid = new InputSquare[ROWS][COLS];

        Puzzle curPuzzle = new Puzzle();
        
        for (int outRow = 0; outRow < OUTROWS; outRow++) {
            for (int outCol = 0; outCol < OUTCOLS; outCol++) {
                JPanel miniGrid = new JPanel();
                miniGrid.setLayout(new GridLayout(INROWS, INCOLS));
                miniGrid.setBorder(blackline);

                for (int inRow = 0; inRow < INROWS; inRow++) {
                    for (int inCol = 0; inCol < INCOLS; inCol++) {
                        int gridRow = outRow*3 + inRow;
                        int gridCol = outCol*3 + inCol;
                        
                        int value = curPuzzle.solution[gridRow][gridCol];
                        boolean filled = curPuzzle.filled[gridRow][gridCol];

                        InputSquare square = new InputSquare(value, filled);
                        square.addKeyListener(new KeyInputHandler(this));
                        square.addActionListener(new ClickHandler(this));
                        miniGrid.add(square);
                        
                        grid[gridRow][gridCol] = square;
                    }
                }
                gridPanel.add(miniGrid);
            }
        }

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setBorder(blackline);
        
        message = new JLabel("Enter a Number!");
        message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        message.setForeground(Color.BLACK);
        messagePanel.add(message);
                
        this.add(gridPanel, BorderLayout.NORTH);
        this.add(messagePanel, BorderLayout.SOUTH);
        this.pack();
    }
    
    public void write(String userInput) {
        if (finished) {
            return;
        }
        message.setText("...");
        if (this.selectedSquare == null) {
            message.setText("No Square Selected!");
            return;
        }
        InputSquare square = this.selectedSquare;
        try {
            int value = Integer.parseInt(userInput);
            if (1 > value || value > 9) {
                throw new Exception();
            }
            square.setPlayerValue(value);
            if (playerHasWon()) {
                finished = true;
                message.setText("You Win!");
            }
        } catch (Exception err) {
            if (userInput.equals("")) {
                square.setPlayerValue(-1);
            } else {
                square.setPlayerValue(square.getPlayerValue());
                message.setText("Not a Valid Number.");
            }
        }
    }
    
    public void setSelectedSquare(InputSquare square) {
        if (finished) {
            return;
        }        
        if (square.isFilled()) {
            message.setText("Not a Valid Square.");
        } else {
            if (this.selectedSquare != null) {
                this.selectedSquare.setBackground(Color.WHITE);
            }
            this.selectedSquare = square;
            square.setBackground(Color.LIGHT_GRAY);
        }
    }
    
    public boolean playerHasWon() {
        boolean allCorrect = true;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                InputSquare square = grid[row][col];
                if (square.getValue() != square.getPlayerValue())
                    allCorrect = false;                
            }
        }
        return allCorrect;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sudoku game = new Sudoku();
    }
}
