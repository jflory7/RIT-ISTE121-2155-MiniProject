/*
 * Copyright (c) 2016 Justin W. Flory, Timothy Endersby
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * GamePiece.java
 *
 * This class defines the behavior and logic for the game pieces. It works with the images that compose pieces,
 * defines and sets the status for game pieces (e.g. whether a piece is in play or not), as well as handle mouse
 * actions for an "overlay" effect on potential moves.
 *
 * @author Timothy Endersby
 * @author Justin W. Flory
 * @version 2016.02.15.v2
 */
public class GamePiece extends JButton {

    // Attributes
    private int status;
    private Image blackCircle;
    private Image whiteCircle;
    private Image transparentCircle;

    /**
     * Default constructor.
     *
     * @param x the x-position of the piece on the board
     * @param y the y-position of the piece on the board
     */
    public GamePiece(int x, int y) {

        // Sets action command to represent the piece's coordinates
        setActionCommand(x + "" + y);

        // Removes borders from all game pieces
        setBorder(BorderFactory.createEmptyBorder());

        // Tries to set the image files to be used for game pieces to attributes defined previously
        try {
            blackCircle = ImageIO.read(getClass().getResource("resources/img/black-circle.png"));
            whiteCircle = ImageIO.read(getClass().getResource("resources/img/white-circle.png"));
            transparentCircle = ImageIO.read(getClass().getResource("resources/img/transparent-circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adds a mouse listener to the piece
        addMouseListener(new MouseAction());
    }

    /**
     * Sets the status of the piece to represent its state in the game (e.g. is there a piece in this position, what
     * player currently has a piece here, is this a valid move, etc.).
     *
     * @param status the integer value representing the status of the piece
     */
    public void setStatus(int status){

        // Sanity check if the passed number is valid
        if (status <= 3 && status >= 0) {
            // Initialize status to local variable
            this.status = status;

            // Clears icon
            if(status == 0 || status == 1) setIcon(null);

            // Sets icon to black
            else if(status == 2) setIcon(new ImageIcon(blackCircle));

            // Sets icon to white
            else if(status == 3) setIcon(new ImageIcon(whiteCircle));
        } else {
            System.out.println("Invalid status entry." +
                    "\nStatus entered: " + status);
        }
    }

    /**
     * Returns piece status.
     *
     * @return int the status of a piece
     */
    public int getStatus(){
        return status;
    }

    /**
     * MouseAction inner class.
     *
     * Inner class that defines behavior for mouse events. When a space is a valid move (status 1), add a transparent
     * circle overlay to the board to show a move can be made there.
     */
    public class MouseAction extends MouseAdapter {

        /**
         * When mousing over a piece with a valid move, set it to display the transparent circle, indicating a move
         * can be made in that position.
         *
         * @param e the mouse event passed to listener (not used in this implementation)
         */
        public void mouseEntered(MouseEvent e) { if (status == 1) setIcon(new ImageIcon(transparentCircle)); }

        /**
         * When mousing away from a piece that was a valid move, set it back to a null icon.
         *
         * @param e the mouse event passed to listener (not used in this implementation)
         */
        public void mouseExited(MouseEvent e) { if (status == 1) setIcon(null); }
    }
}
