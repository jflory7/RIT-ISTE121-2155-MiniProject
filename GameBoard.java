/*
 * Copyright (c) 2016 Justin W. Flory, Timothy Endersby
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel implements ActionListener {

    private GamePiece[][] gamePieces;
    private Scoreboard score;

    public GameBoard(Scoreboard _score) {
        score = _score;
        setLayout(new GridLayout(8, 8));
        gamePieces = new GamePiece[8][8];
        int colorCounter = 0;//If even, light green, if odd, dark green
            for(int x = 0; x < 8; x++){
                colorCounter++;
                for(int y = 0; y < 8; y++){
                    colorCounter++;
                    gamePieces[x][y] = new GamePiece(x, y);
                    gamePieces[x][y].addActionListener(this);
                    if(colorCounter %2 == 0) {
                        Color lightGreen = new Color(15, 125, 15);
                        gamePieces[x][y].setBackground(lightGreen);
                    }else{
                        Color darkGreen = new Color(0, 100, 0);
                        gamePieces[x][y].setBackground(darkGreen);
                    }
                    add(gamePieces[x][y]);
                }
            }
        reset();
    }

    /*
    * Resets the gameBoard to starting position
     */
    public void reset() {
        //Set initial pieces
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                gamePieces[x][y].setStatus(0);

            }
        }
        gamePieces[3][3].setStatus(3);
        gamePieces[4][3].setStatus(2);
        gamePieces[3][4].setStatus(2);
        gamePieces[4][4].setStatus(3);
        //FOR TESTING GLOW EFFECT
        gamePieces[0][0].setStatus(1);
        gamePieces[0][1].setStatus(1);
    }

    public void turn (boolean whoseTurn) {
        //does the player have any options - yes, continue - no, end turn
        //set status of pieces the user can edit
        //when the user preses a button (action listener) if piece is valid, change that piece, and then change other pieces that need to be changed
        //if game is over, ask user if they want a new game or not - new game, run setup - else close game
        //calculate score
        score.updateScore(gamePieces);
    }

    /*
    * When a button  is pushed, it prints out the location of that button
     */
    public void actionPerformed (ActionEvent ae) {
        String choice = ae.getActionCommand();
        int x = Integer.parseInt(choice.substring(0, 1));
        int y = Integer.parseInt(choice.substring(1, 2));
        System.out.println("(" + x + ", " + y + ")");
    }
}