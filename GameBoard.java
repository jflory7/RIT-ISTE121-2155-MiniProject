import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private GamePiece[][] gamePieces;
    private Scoreboard score;
    private Color darkGreen = new Color(0, 100, 0);
    private Color lightGreen = new Color(15, 125, 15);

    public GameBoard(Scoreboard _score){
        score = _score;
        setLayout(new GridLayout(8, 8));
        gamePieces = new GamePiece[8][8];
        int colorCounter = 0;//If even, light green, if odd, dark green
            for(int x = 0; x < 8; x++){
                colorCounter++;
                for(int y = 0; y < 8; y++){
                    colorCounter++;
                    gamePieces[x][y] = new GamePiece();
                    if(colorCounter %2 == 0) {
                        gamePieces[x][y].setBackground(lightGreen);
                    }else{
                        gamePieces[x][y].setBackground(darkGreen);
                    }
                    add(gamePieces[x][y]);
                }
            }
        reset();
    }

    public void reset(){
        //Set initial pieces
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                gamePieces[x][y].setStatus(0);
            }
        }
        gamePieces[3][3].setStatus(3);
        gamePieces[4][3].setStatus(2);
        gamePieces[3][4].setStatus(2);
        gamePieces[4][4].setStatus(3);
        //FOR TESTING GLOW
        gamePieces[0][0].setStatus(1);
        gamePieces[0][1].setStatus(1);
    }

    public void turn(boolean whoseTurn){
        //does the player have any options - yes, continue - no, end turn
        //set status of pieces the user can edit
        //when the user preses a button (action listener) if piece is valid, change that piece, and then change other pieces that need to be changed
        //if game is over, ask user if they want a new game or not - new game, run setup - else close game
        //calculate score
        score.updateScore(gamePieces);
    }

    //Somehow we need an action listener here for the JButtons - Don't know how that will work with an array of buttons
}