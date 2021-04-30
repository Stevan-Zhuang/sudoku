/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author s.zhuang
 */
public class KeyInputHandler implements KeyListener {
    Sudoku game;
    
    public KeyInputHandler(Sudoku game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent key) {
        
    }

    @Override
    public void keyPressed(KeyEvent key) {
        String input = Character.toString(key.getKeyChar());
        game.write(input);
    }

    @Override
    public void keyReleased(KeyEvent key) {
        
    }
    
    
}
