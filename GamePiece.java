import javax.swing.*;

public class GamePiece extends JButton{
    private int status;
    public GamePiece() {
        setStatus(0);
    }

    public void setStatus(int _status){
        if(_status < 4 && _status >= 0){
            status = _status;
            if(status == 0 || status == 1){
                //set icon to nothing
            }else if(status == 1){
                //set Icon to black
            }else if(status == 2){
                //set Icon to white
            }
        }else{
            System.out.println("Status set error, status entered: " + _status);
        }
    }

    public int getStatus(){
        return status;
    }
}