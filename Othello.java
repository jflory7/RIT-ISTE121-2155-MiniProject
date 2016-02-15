import java.awt.*;
import javax.swing.*;

/*
* Mini Project 1 - Othello
* Timothy Endersby and Justin ory
* ISTE 121.01
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
        
        //add(new GameBoard(), BorderLayout.CENTER);
        //add(new ScoreBoard(), BorderLayout.EAST);
        
        pack();
        setTitle("Othello");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
