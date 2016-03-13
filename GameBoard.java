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

/**
 * GameBoard.java
 *
 * This class is the primary logic piece for the project. The game board is actually built in this frame and the
 * logic that defines how the pieces interact with each other, increment score values in the scoreboard, and more are
 * tracked in this file.
 *
 * @author Timothy Endersby
 * @author Justin W. Flory
 * @version 2016.02.15.v2
 */
public class GameBoard extends JPanel implements ActionListener {

    // Constants
    private final Color DARK_GREEN = new Color(0, 100, 0);
    private final Color LIGHT_GREEN = new Color(15, 125, 15);

    // Attributes
    private GamePiece[][] gamePieces;
    private Scoreboard score;
    private boolean turnFinished;

    /**
     * Default constructor.
     *
     * @param score the Scoreboard object to keep track of
     */
    public GameBoard(Scoreboard score) {

        // Define attributes
        turnFinished = false;
        this.score = score;

        // Create panel layout and GamePiece array
        setLayout(new GridLayout(8, 8));
        gamePieces = new GamePiece[8][8];

        // Color counter that generates the checkerboard design for the gameboard (even = light green, odd = dark green)
        int colorCounter = 0;

            for (int x = 0; x < 8; x++) {
                colorCounter++;

                for (int y = 0; y < 8; y++){
                    colorCounter++;

                    // Creates GamePiece objects in each position of the board and adds an action listener to each
                    gamePieces[x][y] = new GamePiece(x, y);
                    gamePieces[x][y].addActionListener(this);

                    // Sets alternating colors for each "checker" on the board
                    if ((colorCounter % 2) == 0) gamePieces[x][y].setBackground(LIGHT_GREEN);
                    else gamePieces[x][y].setBackground(DARK_GREEN);

                    // Adds colored game pieces to the frame
                    add(gamePieces[x][y]);
                }
            }

        // Reset board to starting position (will initialize the pieces)
        reset();
    }

    /**
     * Resets game board to starting position. Invoked on first run or on player request in menu.
     */
    public void reset() {

        // Create initial states of pieces at every playable position on the board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                gamePieces[x][y].setStatus(0);
            }
        }

        // Creates the "beginning four" pieces in the center of the board
        gamePieces[3][3].setStatus(3);
        gamePieces[4][3].setStatus(2);
        gamePieces[3][4].setStatus(2);
        gamePieces[4][4].setStatus(3);

        // FOR TESTING GLOW EFFECT
        gamePieces[0][0].setStatus(1);
        gamePieces[0][1].setStatus(1);

        //
        score.updateScore(gamePieces, true);
    }

    /**
     * Set of actions that happen on each player's turn. Called from loop in the Othello class and is repeated
     * constantly during the course of the game.
     *
     * @param whoseTurn true if it is currently the player's turn
     */
    public void turn (boolean whoseTurn) {

        //
        score.updateScore(gamePieces, whoseTurn);
        turnFinished = false;

        //TODO Run check here. Does the player have valid options? If yes, continue; if no, break!

        //TODO Set status of pieces onMouseOver (do we want to do that here or elsewhere?)

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                //TODO Check if valid move
            }
        }

        /*
         * When the user presses a button (ActionListener), it checks if the move is valid.
         *      If yes: Change that piece and change other pieces that need changing
         *      If no valid moves: End game. Ask user if they want a new game (run setup); else, close the game
         */
        while (!turnFinished) { //wait until the turn is done - turn is set to true in the action listener
            try {
                Thread.sleep(100);//This is to reduce CPU usage - without it it uses a lot of CPU
            } catch(InterruptedException e) {
                System.out.println("Thread.sleep error!\n");
                e.printStackTrace();
            }
        }

        // Runs score calculation before exiting loop
        score.updateScore(gamePieces, whoseTurn);
    }

    /**
     * Action listener for buttons in game board. When a button is pushed, it prints the location of the button in
     * console.
     *
     * @param ae the action passed to the listener
     */
    public void actionPerformed (ActionEvent ae) {

        // Local variables
        String choice = ae.getActionCommand();
        int x = Integer.parseInt(choice.substring(0, 1));
        int y = Integer.parseInt(choice.substring(1, 2));
        int status = gamePieces[x][y].getStatus();

        // Prints button position to console
        System.out.println("(" + x + ", " + y + ")");

        /*
         * Checks status of button.
         *      0   =   Invalid button
         *      1   =   Valid move, change all applicable pieces
         *    2/3   =   Invalid move, piece already placed
         */
        if (status == 0) System.out.println("Nothing, button not valid");

        else if (status == 1) {
            //TODO Logic to convert pieces goes here

            gamePieces[x][y].setStatus(2);
            turnFinished = true;
        }

        else if (status == 2 || status == 3) System.out.println("nothing, button already pressed");
    }
}