/*
 * Copyright (c) 2016 Justin W. Flory
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
 * Scoreboard.java
 *
 * This class defines the behavior and logic for the scoreboard.
 *
 * @author Timothy Endersby
 * @author Justin W. Flory
 * @version 2016.02.15.v2
 */
public class Scoreboard extends JPanel implements ActionListener {

    // Attributes
    private int p1Score;
    private int p2Score;
    private int turn;
    private JLabel jl1Score;
    private JLabel jl2Score;
    private JLabel player;
    private JButton jbHelp;

    /**
     * Default constructor. Creates labels for player score and defines the organization of the panel, defines action
     * listener for help button.
     */
    public Scoreboard() {

        // Sets layout of scoreboard to use a grid
        setLayout(new GridLayout(20, 1));

        // Create labels for both player one and player two's scores, add to frame
        jl1Score = new JLabel("Player One's score: " + p1Score, SwingConstants.CENTER);
            add(jl1Score);
        jl2Score = new JLabel("Player Two's score: " + p2Score, SwingConstants.CENTER);
            add(jl2Score);

        // Empty label, used as a spacer
        add(new JLabel("                                                       "));

        // Display which player's turn it is, add to JFrame
        player = new JLabel("It is player "+ turn + "'s " + "turn.", SwingConstants.CENTER);
            add(player);

        // Empty label, used as a spacer
        add(new JLabel());

        // Add help button, displays information on gameplay
        jbHelp = new JButton("Help");
            add(jbHelp);
            jbHelp.addActionListener(this);

    }

    /**
     * Updates the score on the scoreboard based on most recent changes. Method is called in GameBoard whenever a new
     * move is made to reflect the latest score changes on the board.
     *
     * @param gamePieces
     * @param isTurn
     */
    public void updateScore(GamePiece[][] gamePieces, boolean isTurn) {

        // Set whose turn it is
        if (isTurn) turn = 1;
        else turn = 2;

        // Reset scores
        p1Score = 0;
        p2Score = 0;

        // Scans all pieces on the board, checks status; increments score based on piece status
        for (int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(gamePieces[x][y].getStatus() == 2) p1Score++;
                else if(gamePieces[x][y].getStatus() == 3) p2Score++;
            }
        }

        // Sets the score labels based on updated values
        jl1Score.setText("Player one's score: " + p1Score);
        jl2Score.setText("Player two's score: " + p2Score);
        player.setText("It is player "+ turn + "'s" + " turn");
    }

    /**
     * Returns which player's turn it is.
     *
     * @return 1 if P1, 2 if P2
     */
    public int getTurn() {
        return turn;
    }

    /**
     * The action listener for the help button. Only purpose is to display game instructions and rules when the
     * button is pressed.
     *
     * @param ae the action passed to the listener (assumed to be the help button in this class)
     */
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(null,
                "How to Play Othello" +
                "\n" +
                "\nPlayers battle to finish the game with more of their own pieces on the board than " +
                "\ntheir opponent. The game is classed as finished when there are no spaces left on " +
                "\nthe board or there are no more possible legal moves for either competitor" +
                "\n" +
                "\nThe Start" +
                "\n" +
                "\nBoth players begin the game with two pieces on the board in the four centre squares." +
                "\nNo two matching colours are connected vertically or horizontally so a miniature chequered " +
                "\npattern is made. In the typical set ups where it is black versus white the person using " +
                "\nblack chips must make the first move." +
                "\n" +
                "\nThe Game" +
                "\n" +
                "\nBoth players take it in turns to make their move which consists of placing one piece down" +
                "\nin a legally acceptable position and then turning any of the opposing player’s pieces where " +
                "\napplicable. A legal move is one that consists of, for example, a black piece being placed " +
                "\non the board that creates a straight line (vertical, horizontal or diagonal) made up of a " +
                "\nblack piece at either end and only white pieces in between. When a player achieves this, " +
                "\nthey must complete the move by turning any white pieces in between the two black so that they " +
                "\nline becomes entirely black. This turning action must be completed for every legal turning " +
                "\nline that is created with the placing of the new piece." +
                "\n" +
                "\nIt goes without say that while the example assumes the use of black as the moving player, it " +
                "\nis applicable both ways." +
                "\n" +
                "\nPlayers will then continue to move alternately until they get to the end of the game and a " +
                "\nwinner is decided. This decision is reached by identifying which of the two opponents has the " +
                "\nmost pieces on the board." +
                "\n" +
                "\nInstructions from http://www.othelloonline.org/");
    }
}