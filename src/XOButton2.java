import javax.swing.*;

public class XOButton2 extends JButton {

    ImageIcon X,O;
    int buttonValue;

    public XOButton2(){
        X = new ImageIcon(this.getClass().getResource("cross.jpg"));
        O = new ImageIcon(this.getClass().getResource("circle.jpg"));
        buttonValue = 0;
    }

    public ImageIcon getImageX(){
        return X;
    }

    public ImageIcon getImageO(){
        return O;
    }

    public void setButtonValue(int buttonValue) {
        this.buttonValue = buttonValue;
    }

    public int getButtonValue() {

        return buttonValue;
    }
}
