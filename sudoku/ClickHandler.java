/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author s.zhuang
 */
public class ClickHandler implements ActionListener {
    Sudoku game;
    
    public ClickHandler(Sudoku game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        InputSquare square = (InputSquare) evt.getSource();
        game.setSelectedSquare(square);
    }
}
