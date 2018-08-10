import javax.swing.*;

public class XOButton2 extends JButton {

    ImageIcon X, O, N;
    int buttonValue;
    String buttonSign;

    public XOButton2(){
        X = new ImageIcon(this.getClass().getResource("cross.jpg"));
        O = new ImageIcon(this.getClass().getResource("circle.jpg"));
        N = new ImageIcon(this.getClass().getResource("nothing.jpg"));
        buttonValue = 0;
        buttonSign = "";
    }

    public ImageIcon getImageX(){
        return X;
    }

    public ImageIcon getImageO(){
        return O;
    }

    public ImageIcon getN() {
        return N;
    }

    public String getButtonSign() {
        return buttonSign;
    }

    public void setButtonSign(String buttonSign) {
        this.buttonSign = buttonSign;
    }



    public void setButtonValue(int buttonValue) {
        this.buttonValue = buttonValue;
    }

    public int getButtonValue() {

        return buttonValue;
    }

}
