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
     * Default constructor. Creates array for GamePieces, initializes board, and resets it, making it ready for a new
     * game to begin.
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

        // Color counter that generates the checkerboard design for the board (even = light green, odd = dark green)
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

        //Sets status for valid possible moves
        countValidMoves();

        // Updates the score to reflect the accurate number of pieces on the board
        score.updateScore(gamePieces, true);
    }

    /**
     * Set of actions that happen on each player's turn. Called from loop in the Othello class and is repeated
     * constantly during the course of the game.
     *
     * @param whoseTurn true if it is currently the player's turn
     */
    public void turn(boolean whoseTurn) {

        // Local variables
        //int validMoves = 0;

        // Update the score before beginning turn and set turn to unfinished (in case hanging from previous runs)
        score.updateScore(gamePieces, whoseTurn);
        turnFinished = false;

<<<<<<< HEAD
        int validMoves = countValidMoves();

=======
        // Checks for valid moves and counts how many valid moves there are
        if(countValidMoves() == 0){
            System.out.println("End game");
        }

        /*
>>>>>>> parent of 5e04827... THE GAME WORKS
        // If there are no more valid moves to be made, end turn and end game
        if (validMoves == 0) {
            JOptionPane.showMessageDialog(null, "There are no more moves on the board!");
            turnFinished = true;
            String[] options = new String[] {"Play again", "Quit"};
            int response = JOptionPane.showOptionDialog(null, "There are no more moves left", "Winner",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            System.out.println(response);
        }

        /*
         * //TODO Explain this block below in more English-y words
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
     * Checks to see if a move a player makes is valid or not by checking all of the possible directions a move can
     * be made. If the move is valid, the piece's status is set to '1' to enable the glow effect. Returns true if
     * move is valid.
     *
     * @param rightHere the GamePiece being tested for
     * @return true if the move is valid
     */
    public boolean checkForMove(GamePiece rightHere) {
        if (checkForDirection(-1, -1, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(0, -1, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(+1, -1, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(+1, 0, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(+1, +1, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(0, +1, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(-1, +1, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(-1, 0, rightHere) > 0) {
            rightHere.setStatus(1);
            return true;
        }

        return false;
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
        if (status == 0) System.out.println("Invalid selection. No move can be made here.");
<<<<<<< HEAD

        else if (status == 1) {
            //Set selected piece
            gamePieces[x][y].setStatus(score.getTurn() + 1);

            //Move pieces in all 8 directions
            GamePiece rightHere = gamePieces[x][y];
            checkForDirection(-1, -1, rightHere, true);
            checkForDirection(0, -1, rightHere, true);
            checkForDirection(+1, 0, rightHere, true);
            checkForDirection(+1, +1, rightHere, true);
            checkForDirection(0, +1, rightHere, true);
            checkForDirection(-1, +1, rightHere, true);
            checkForDirection(-1, 0, rightHere, true);
=======
        
        else if (status == 1) {
            //TODO Logic to convert pieces goes here [probably the hardest part of the entire project]
            if (score.getTurn() == 1) {
                gamePieces[x][y].setStatus(2);
                //TODO Change all neighboring pieces that are affected
            } else if (score.getTurn() == 2) {
                gamePieces[x][y].setStatus(3);
                //TODO Change all neighboring pieces that are affected
            }
>>>>>>> parent of 5e04827... THE GAME WORKS

            turnFinished = true;
        }

        else if (status == 2 || status == 3) System.out.println("Invalid selection. Piece already exists here.");
    }

    /**
     * This method checks for all pieces in the specified direction of a game piece and sees if there are any valid
     * moves.
     *
     * @param plusX the x-position in relation to the position of the game piece
     * @param plusY the y-position in relation to the position of the game piece
     * @param rightHere the GamePiece being checked against
     * @return >0 if valid move, 0 if invalid, <0 if unexpected error
     */
    private int checkForDirection(int plusX, int plusY, GamePiece rightHere) {
        int x = rightHere.getXPos();
        int y = rightHere.getYPos();
        int player = score.getTurn();
        int counter = 0;
        while(true) {
            x+=plusX;
            y+=plusY;
            if(x > 7 || x < 0) return 0;
            if(y > 7 || y < 0) return 0;
            int stat = gamePieces[x][y].getStatus();
            if(stat == player+1) {
<<<<<<< HEAD
                // DEBUG LINE if(counter != 0) System.out.println(x + ", " + y + " status: " + gamePieces[x][y].getStatus() + " x+ " + plusX + " y+ " + plusY + " counter: " + counter);
                break;
=======
                if(counter != 0) System.out.println(x + ", " + y + " status: " + gamePieces[x][y].getStatus() + " x+ " + plusX + " y+ " + plusY + " counter: " + counter);
                return counter;
>>>>>>> parent of 5e04827... THE GAME WORKS
            }else if(stat == 0 || stat == 1){
                return 0;
            }else if(stat == player+2 || stat == player){
                counter++;
            }else{
                System.out.println("Status error " + x + ", " + y + " status: " + gamePieces[x][y].getStatus());
                return 0;
            }
        }
    }

    /**
     * Counts the number of valid moves on the board.
     *
     * @return number of valid moves on board
     */
    public int countValidMoves() {

        // Local variables
        int validMoves = 0;

        // Checks for valid moves and counts how many valid moves there are
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if(gamePieces[x][y].getStatus() < 2) {
                    if (checkForMove(gamePieces[x][y])) validMoves++;
                    else gamePieces[x][y].setStatus(0);
                }
            }
        }
        return validMoves;
    }
}