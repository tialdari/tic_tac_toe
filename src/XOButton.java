import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XOButton extends JButton  {


    ImageIcon X,O;
    byte value = 0;

    /*
    0: nothing
    1: X
    2: O
     */
    public XOButton(){
        X = new ImageIcon(this.getClass().getResource("cross.jpg"));
        O = new ImageIcon(this.getClass().getResource("circle.jpg"));
       // this.addActionListener(this);
    }




    public void actionPerformed(ActionEvent e){

        value++;
        value %= 3;

        switch(value){
            case 0:
                setIcon(null);
                break;

            case 1:
                setIcon(X);
                break;

            case 2:
                setIcon(O);
                break;
        }
    }


}
