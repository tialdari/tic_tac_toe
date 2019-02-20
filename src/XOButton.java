import javax.swing.*;

public class XOButton extends JButton { // class of a single button

    private ImageIcon X, O;
    private String buttonSign;

    public XOButton(){
        X = new ImageIcon(this.getClass().getResource("cross.jpg"));
        O = new ImageIcon(this.getClass().getResource("circle.jpg"));
        buttonSign = "";
    }

    public ImageIcon getImageX(){
        return X;
    }

    public ImageIcon getImageO(){
        return O;
    }

    public String getButtonSign() {
        return buttonSign;
    }

    public void setButtonSign(String buttonSign) {
        this.buttonSign = buttonSign;
    }

}
