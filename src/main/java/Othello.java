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
 * Othello.java
 *
 * Mini Project, ISTE-121.01: Othello
 *
 * @author Timothy Endersby
 * @author Justin W. Flory
 * @version 2016.02.15.v1
 */
public class Othello extends JFrame implements ActionListener {

    // Constants
    public final double GAME_VERSION = 0.1;

    // Attributes
    private JMenuItem jmiReset;
    private JMenuItem jmiQuit;
    private JMenuItem jmiAbout;
    private GameBoard game;

    /**
     * Main method. Creates a new object of the class.
     *
     * @param args a String array of any arguments passed in the CLI
     */
    public static void main(String[] args){
        new Othello();
    }

    /**
     * Default constructor.
     *
     * The purpose of this class is to build and create the "master" JFrame that the game will be played in. Basic
     * work and customizations to the GUI are made in this class. The game-specific work is handled in its own
     * respective classes (see GameBoard, GamePiece, Scoreboard).
     */
    public Othello() {

        // Create the JMenuBar and its menu items
        JMenuBar menuBar = new JMenuBar();
            JMenu jmGame = new JMenu("Game");
            jmGame.setMnemonic('G');

                jmiReset = new JMenuItem("Reset Game");
                jmiReset.setMnemonic('R');
                jmGame.add(jmiReset);

                jmiQuit = new JMenuItem("Quit");
                jmiQuit.setMnemonic('Q');
                jmGame.add(jmiQuit);

            menuBar.add(jmGame);

            JMenu jmHelp = new JMenu("Help");
            jmHelp.setMnemonic('H');

                jmiAbout = new JMenuItem("About");
                jmiAbout.setMnemonic('A');
                jmHelp.add(jmiAbout);

            menuBar.add(jmHelp);

        setJMenuBar(menuBar);

        // Add action listeners to all JMenuItems (listener in this class file)
        jmiReset.addActionListener(this);
        jmiQuit.addActionListener(this);
        jmiAbout.addActionListener(this);

        // Declare a new scoreboard object and add it to the frame
        Scoreboard score = new Scoreboard();
        add(score, BorderLayout.EAST);

        // Declare a new GameBoard object and add it to the frame
        game = new GameBoard(score);
        add(game, BorderLayout.CENTER);

        // Define misc. attributes of the frame and display frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Othello - Justin W. Flory, Timothy Endersby (ISTE-121)");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        // Loop to listen for next turn by a player
        while (true){
            game.turn(true);
            game.turn(false);
        }
    }

    /**
     * Action listener for JMenuItems in class constructor.
     *
     * @param ae the action passed to the listener
     */
    public void actionPerformed(ActionEvent ae){

        // Source of the action
        Object choice = ae.getSource();

        /*
         * Reset    =   Resets game board
         * Quit     =   Exits program
         * About    =   Shows information about the actual game
         */
        if (choice == jmiReset) game.reset();
        else if (choice == jmiQuit) System.exit(0);
        else if (choice == jmiAbout) {
            JOptionPane.showMessageDialog(null, "Othello" +
                    "\nVersion " + GAME_VERSION +
                    "\nReleased: 2016-03-14" +
                    "\nDeveloped by: Justin W. Flory and Timothy Endersby");
        } else {
            System.out.println("ERROR. Your selection was invalid." +
                    "\nSelection: " + ae);
        }
    }
}
