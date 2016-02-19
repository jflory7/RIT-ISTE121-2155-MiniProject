import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Scoreboard extends JPanel implements ActionListener{

    private int p1Score;
    private int p2Score;
    private JLabel jl1Score;
    private JLabel jl2Score;
    private JButton jbHelp;

    public Scoreboard(){
        setLayout(new GridLayout(0, 1));
        p1Score = 0;
        p2Score = 0;
        jl1Score = new JLabel("Player one's score: " + p1Score);
        add(jl1Score);
        jl2Score = new JLabel("Player two's score: " + p2Score);
        add(jl2Score);
        jbHelp = new JButton("Help");
        add(jbHelp);
        jbHelp.addActionListener(this);
    }

    public void updateScore(GamePiece[][] gamePieces){
        p1Score = 0;
        p2Score = 0;
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                if(gamePieces[x][y].getStatus() == 2){
                    p1Score++;
                }else if(gamePieces[x][y].getStatus() == 3){
                    p2Score++;
                }
            }
        }
        jl1Score.setText("Player one's score: " + p1Score);
        jl2Score.setText("Player two's score: " + p2Score);
    }

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