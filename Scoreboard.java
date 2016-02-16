import javax.swing.*;

public class Scoreboard extends JPanel{

    public Scoreboard(){
        setSize(200, 200);
        add(new JLabel("This is the scoreboard"));
        add(new JLabel("Its player 2's turn"));
        add(new JButton("Help"));
    }

    public void calculateScore(GamePiece[][] gamePieces){
        //do math
    }
}