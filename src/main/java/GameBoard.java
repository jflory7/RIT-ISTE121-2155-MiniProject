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

import static javax.swing.SwingConstants.CENTER;

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

        // Updates the score to reflect the accurate number of pieces on the board
        score.updateScore(gamePieces, true);

        //Set valid moves
        countValidMoves();
    }

    /**
     * Set of actions that happen on each player's turn. Called from loop in the Othello class and is repeated
     * constantly during the course of the game.
     *
     * @param whoseTurn true if it is currently the player's turn
     */
    public void turn(boolean whoseTurn) {

        // Update the score before beginning turn and set turn to unfinished (in case hanging from previous runs)
        score.updateScore(gamePieces, whoseTurn);
        turnFinished = false;

        // Checks for valid moves and counts how many valid moves there are
        if(countValidMoves() == 0){
            score.updateScore(gamePieces, !whoseTurn);
            if(countValidMoves() == 0) {
                String[] options = new String[]{"Play Again", "Quit"};
                int response = JOptionPane.showOptionDialog(null, new JLabel("<html>There are no valid moves left<br>Player " + score.getWinner() + " won</html>", JLabel.CENTER),
                        "Winner", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response == 0) reset();
                else System.exit(0);
            }else{
                score.updateScore(gamePieces, whoseTurn);
                JOptionPane.showMessageDialog(null, new JLabel("Player " + score.getTurn() + " can't make a move", JLabel.CENTER));
            }
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
    public boolean checkForMove(GamePiece rightHere, boolean setPieces) {
        if (checkForDirection(-1, -1, rightHere, setPieces) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(0, -1, rightHere, setPieces) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(+1, -1, rightHere, setPieces) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(+1, 0, rightHere, setPieces) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(+1, +1, rightHere, setPieces) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(0, +1, rightHere, setPieces) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(-1, +1, rightHere, setPieces) > 0) {
            rightHere.setStatus(1);
            return true;
        }
        else if (checkForDirection(-1, 0, rightHere, setPieces) > 0) {
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
        //System.out.println("(" + x + ", " + y + ")");

        /*
         * Checks status of button.
         *      0   =   Invalid button
         *      1   =   Valid move, change all applicable pieces
         *    2/3   =   Invalid move, piece already placed
         */
        //if (status == 0) //System.out.println("Invalid selection. No move can be made here.");

        if (status == 1) {
            if (score.getTurn() == 1) {
                gamePieces[x][y].setStatus(2);
            } else if (score.getTurn() == 2) {
                gamePieces[x][y].setStatus(3);
            }

            turnFinished = true;

            GamePiece rightHere = gamePieces[x][y];

            checkForDirection(0, -1, rightHere, true);
            checkForDirection(+1, 0, rightHere, true);
            checkForDirection(+1, +1, rightHere, true);
            checkForDirection(0, +1, rightHere, true);
            checkForDirection(-1, +1, rightHere, true);
            checkForDirection(-1, 0, rightHere, true);
            checkForDirection(-1, -1, rightHere, true);
            checkForDirection(+1, -1, rightHere, true);

        }

        //else if (status == 2 || status == 3) System.out.println("Invalid selection. Piece already exists here.");
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
    private int checkForDirection(int plusX, int plusY, GamePiece rightHere, boolean setPieces) {
        //int origX = rightHere.getXPos();
        //int origY = rightHere.getYPos();
        int x = rightHere.getXPos();
        int y = rightHere.getYPos();
        int player = score.getTurn();
        int counter = 0;
        int status = 0;
        if (score.getTurn() == 1) status = 2;
        else if (score.getTurn() == 2) status = 3;
        while(true) {
            x+=plusX;
            y+=plusY;
            if(x > 7 || x < 0) return 0;
            if(y > 7 || y < 0) return 0;
            int stat = gamePieces[x][y].getStatus();
            if(stat == player+1) {
                //if(counter != 0) System.out.println("(" + x + ", " + y + ") (" + origX + ", " + origY + ") status: " + gamePieces[x][y].getStatus() + " x+ " + plusX + " y+ " + plusY + " counter: " + counter);
                break;
            }else if(stat == 0 || stat == 1){
                return 0;
            }else if(stat == player+2 || stat == player){
                counter++;
            }else{
                System.out.println("Status error " + x + ", " + y +  gamePieces[x][y].getStatus());
                return 0;
            }
        }

        if(counter != 0 && setPieces){
            x = rightHere.getXPos();
            y = rightHere.getYPos();
            for(int i = 0; i < counter; i++){
                x+=plusX;
                y+=plusY;
                gamePieces[x][y].setStatus(status);
            }
        }
        return counter;
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
                    if (checkForMove(gamePieces[x][y], false)) validMoves++;
                    else gamePieces[x][y].setStatus(0);
                }
            }
        }
        return validMoves;
    }
}