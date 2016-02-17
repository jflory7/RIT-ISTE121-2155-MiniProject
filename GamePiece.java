import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;

public class GamePiece extends JButton{
    private int status;
    private Image blackCircle;
    private Image whiteCircle;
    public GamePiece() {
        setStatus(0);
        setBorder(BorderFactory.createEmptyBorder());//Remove all boarders
        try {
            blackCircle = ImageIO.read(getClass().getResource("black circle.png"));
            whiteCircle = ImageIO.read(getClass().getResource("white circle.png"));
        }catch(IOException e){
            System.out.println("IO Exception" + e);
        }
    }

    public void setStatus(int _status){
        if(_status < 4 && _status >= 0){
            status = _status;
            if(status == 0) {
                //set icon to not glow
            }else if(status == 1){
                //set icon to glow
                //clear Icon
            }else if(status == 2){
                //set Icon to black
                //Clear Icon
                setIcon(new ImageIcon(blackCircle));
            }else if(status == 3){
                //set Icon to white
                setIcon(new ImageIcon(whiteCircle));
            }
        }else{
            System.out.println("Status set error, status entered: " + _status);
        }
    }

    public int getStatus(){
        return status;
    }
}