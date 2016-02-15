import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private GamePiece[][] gamePieces;

    public GameBoard(){
        setLayout(new GridLayout(8, 8));
        gamePieces = new GamePiece[8][8];
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    gamePieces[x][y] = new GamePiece();
                    //gamePieces[x][y].setIcon(img);
                    add(gamePieces[x][y]);
                }
            }
    }
}