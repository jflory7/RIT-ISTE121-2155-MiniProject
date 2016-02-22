import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GamePiece extends JButton implements MouseListener {
    private int status;
    private Image blackCircle;
    private Image whiteCircle;
    private Image transparentCircle;

    public GamePiece(int x, int y) {
        setActionCommand(x + "" + y);
        setBorder(BorderFactory.createEmptyBorder());//Remove all borders
        try {
            blackCircle = ImageIO.read(getClass().getResource("resources/img/black-circle.png"));
            whiteCircle = ImageIO.read(getClass().getResource("resources/img/white-circle.png"));
            transparentCircle = ImageIO.read(getClass().getResource("resources/img/transparent-circle.png"));
        }catch(IOException e){
            System.out.println("IO Exception" + e);
        }
        addMouseListener(this);
    }

    public void setStatus(int _status){
        if(_status <= 3 && _status >= 0){//Check that status is a valid number from 0-3
            status = _status;
            if(status == 0 || status == 1) {
                //Clear Icon
                setIcon(null);
            }else if(status == 2){
                //set Icon to black
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

    /*
    * Mouse events
    * When a space is a valid move (status 1), add transparent circle
     */
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        if(status == 1){
            setIcon(new ImageIcon(transparentCircle));
        }
    }
    public void mouseExited(MouseEvent e) {
        if(status == 1){
            setIcon(null);
        }
    }
}
