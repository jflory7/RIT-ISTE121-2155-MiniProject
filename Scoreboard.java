import javax.swing.*;
import java.awt.event.*;

public class Scoreboard extends JPanel implements ActionListener{

    private int p1Score;
    private int p2Score;
    private JLabel jl1Score;
    private JLabel jl2Score;
    private JButton jbHelp;

    public Scoreboard(){
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
        JOptionPane.showMessageDialog(null, "Help");
    }
}