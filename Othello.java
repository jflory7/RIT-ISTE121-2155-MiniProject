/*
 * Copyright (c) 2016 Justin W. Flory, Timothy Endersby
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
                jmiReset = new JMenuItem("Reset Game");
                jmGame.add(jmiReset);
                jmiQuit = new JMenuItem("Quit");
                jmGame.add(jmiQuit);
            menuBar.add(jmGame);
            JMenu jmHelp = new JMenu("Help");
                jmiAbout = new JMenuItem("About");
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
            game.setup();
        }else if(choice == jmiQuit){
            System.exit(0);
        }else if(choice == jmiAbout){
            JOptionPane.showMessageDialog(null, "About");
        }else{
            System.out.println("How did you get here with: " + ae);
        }
    }
}
