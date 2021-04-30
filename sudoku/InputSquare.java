/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author s.zhuang
 */
public class InputSquare extends JButton {
    private int value;
    private int playerValue;
    private boolean filled;
    
    public InputSquare(int value, boolean filled) {        
        this.setPreferredSize(new Dimension(75, 75));
        
        this.setBackground(Color.WHITE);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        this.setForeground(Color.BLACK);
        if (!filled) this.setForeground(Color.RED);
        
        Border grayline = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        this.setBorder(grayline);
        
        if (filled) {
            this.setText(String.valueOf(value));
            this.playerValue = value;
        }
        
        this.value = value;
        this.playerValue = filled ? value : -1;
        this.filled = filled;
    }
    
    public void setPlayerValue(int value) {
        this.playerValue = value;
        boolean isEmpty = this.playerValue != -1;
        String text = isEmpty ? String.valueOf(this.playerValue) : "";
        this.setText(text);
    }
    
    public int getValue() {
        return this.value;
    }
    
    public int getPlayerValue() {
        return this.playerValue;
    }
    
    public boolean isFilled() {
        return this.filled;
    }    
}
