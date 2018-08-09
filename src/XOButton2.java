import javax.swing.*;

public class XOButton2 extends JButton {

    ImageIcon X,O;

    public XOButton2(){
        X = new ImageIcon(this.getClass().getResource("cross.jpg"));
        O = new ImageIcon(this.getClass().getResource("circle.jpg"));
    }
}
