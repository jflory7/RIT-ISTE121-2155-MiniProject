/*
 * Copyright (c) 2016 Justin W. Flory, Timothy Endersby
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
import javax.swing.*;
import java.awt.*;
/**
 * Othello.java
 *
 * Mini Project 1, ISTE-121.01: Othello
 *
 * @author Timothy Endersby
 * @author Justin W. Flory
 * @version 2016.02.15.v1
 */
public class Othello extends JFrame {
    private JMenuItem jmiNewGame;
    public static void main(String[] args){
        new Othello();
    }

    public Othello(){
        JMenuBar menuBar = new JMenuBar();
            JMenu jmFile = new JMenu("File");
                jmiNewGame = new JMenuItem("New Game");
                jmFile.add(jmiNewGame);
            menuBar.add(jmFile);
        add(menuBar, BorderLayout.NORTH);

        add(new GameBoard(), BorderLayout.CENTER);
        add(new Scoreboard(), BorderLayout.EAST);

        pack();
        setTitle("Othello");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
