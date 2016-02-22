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
import java.awt.event.KeyEvent;
/**
 * Othello.java
 *
 * Mini Project 1, ISTE-121.01: Othello
 *
 * @author Timothy Endersby
 * @author Justin W. Flory
 * @version 2016.02.15.v1
 */
public class Othello extends JFrame implements ActionListener {

    public static final double gameVersion = 0.1;
    private JMenuItem jmiReset;
    private JMenuItem jmiQuit;
    private JMenuItem jmiAbout;
    private GameBoard game;

    public static void main(String[] args){
        new Othello();
    }

    public Othello(){
        JMenuBar menuBar = new JMenuBar();
            JMenu jmGame = new JMenu("Game");
            jmGame.setMnemonic(KeyEvent.VK_G);

                jmiReset = new JMenuItem("Reset Game");
                jmiReset.setMnemonic(KeyEvent.VK_R);
                jmGame.add(jmiReset);

                jmiQuit = new JMenuItem("Quit");
                jmiQuit.setMnemonic(KeyEvent.VK_Q);
                jmGame.add(jmiQuit);

            menuBar.add(jmGame);

            JMenu jmHelp = new JMenu("Help");
            jmHelp.setMnemonic(KeyEvent.VK_H);

                jmiAbout = new JMenuItem("About");
                jmiAbout.setMnemonic(KeyEvent.VK_A);
                jmHelp.add(jmiAbout);

            menuBar.add(jmHelp);

        add(menuBar, BorderLayout.NORTH);

        jmiReset.addActionListener(this);
        jmiQuit.addActionListener(this);
        jmiAbout.addActionListener(this);

        Scoreboard score = new Scoreboard();
        add(score, BorderLayout.EAST);

        game = new GameBoard(score);
        add(game, BorderLayout.CENTER);

        pack();
        setTitle("Othello");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        while(true){
            game.turn(true);
            game.turn(false);
        }
    }

    public void actionPerformed(ActionEvent ae){
        Object choice = ae.getSource();

        if(choice == jmiReset){
            game.reset();
        }else if(choice == jmiQuit){
            System.exit(0);
        }else if(choice == jmiAbout){//We might want to move these instructions to help
            JOptionPane.showMessageDialog(null, "Othello" +
                    "\nVersion " + gameVersion +
                    "\nDate of final release here" +
                    "\nDeveloped by Justin W. Flory and Timothy Endersby");
        } else {
            System.out.println("How did you get here with: " + ae);
        }
    }
}
