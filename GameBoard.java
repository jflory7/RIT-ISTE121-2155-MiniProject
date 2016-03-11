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
    private boolean turnFinished;

    public GameBoard(Scoreboard _score) {
        turnFinished = false;
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
        score.updateScore(gamePieces, true);
    }

    public void turn (boolean whoseTurn) {
        score.updateScore(gamePieces, whoseTurn);
        turnFinished = false;
        //does the player have any options - yes, continue - no, end turn here
        //set status of pieces the user can edit

        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                //do Logic to see if its a valid move
            }
        }
        //when the user preses a button (action listener) if piece is valid, change that piece, and then change other pieces that need to be changed
        //if game is over, ask user if they want a new game or not - new game, run setup - else close game
        //calculate score
        while(!turnFinished){//wait until the turn is done - turn is set to true in the action listener
            try {
                Thread.sleep(100);//This is to reduce CPU usage - without it it uses a lot of CPU
            }catch(InterruptedException e){
                System.out.println("Sleep error" + e);
            }
        }
        score.updateScore(gamePieces, whoseTurn);
    }

    /*
    * When a button  is pushed, it prints out the location of that button
     */
    public void actionPerformed (ActionEvent ae) {
        String choice = ae.getActionCommand();
        int x = Integer.parseInt(choice.substring(0, 1));
        int y = Integer.parseInt(choice.substring(1, 2));
        System.out.println("(" + x + ", " + y + ")");

        int status = gamePieces[x][y].getStatus();
        if(status == 0) {
            System.out.println("Nothing, button not valid");
        }else if(status == 1){
            //Logic to switch all applicable pieces goes here
            gamePieces[x][y].setStatus(2);
            turnFinished = true;
        }else if(status == 2 || status == 3){
            System.out.println("nothing, button already pressed");
        }
    }
}