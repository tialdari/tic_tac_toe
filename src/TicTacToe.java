import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends JFrame {


    XOButton[][] buttons = new XOButton[3][3];
    List<XOButton> usedButtons = new ArrayList<>();
    JPanel p = new JPanel();
    int whoseTurn = 2;

     /*
    0: nothing
    1: X
    2: O
     */

    public TicTacToe() {

        super("Tic Tac Toe");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(3, 3));
        add(p);
        initialiseButtons();
        setVisible(true);
    }

    public void initialiseButtons() {


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j] = new XOButton();
                buttons[i][j].addActionListener(new buttonListener());
                p.add(buttons[i][j]);
            }
        }

    }

    

    private class buttonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            XOButton buttonClicked = (XOButton) e.getSource();

            if (usedButtons.contains(buttonClicked)) return;
            usedButtons.add(buttonClicked);

            whoseTurn %= 3;

            switch (whoseTurn) {

                case 0:
                    whoseTurn++;

                case 1:
                    buttonClicked.setIcon(buttonClicked.getImageX());
                    buttonClicked.setButtonSign("X");
                    break;

                case 2:
                    buttonClicked.setIcon(buttonClicked.getImageO());
                    buttonClicked.setButtonSign("O");
                    break;
            }

            checkForWin();
            transpose();
            checkForWin();
            transpose();

            whoseTurn++;
        }




        public boolean checkForWin() {

            String sign = "";
            String pattern = "";

            for (int i = 0; i < 3; i++) {

                pattern = "";

                for (int j = 0; j < 3; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern += sign;
                }

                if (pattern.equals("XXX")) {
                    displayMessage("X");
                    return true;

                } else if (pattern.equals("OOO")) {
                    displayMessage("O");
                    return true;
                }

            }

            for (int i = 0; i < 3; i++) {
                for (int j = i; j == i; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern += sign;

                    if (sign == "") {
                        pattern = "";
                        break;
                    }

                }

                if (pattern.equals("XXX")) displayMessage("X");
                else if (pattern.equals("OOO")) displayMessage("O");
            }

            pattern = "";

            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {

                    if(i + j == 2) {

                        sign = buttons[i][j].getButtonSign();
                        pattern += sign;

                        if (sign == "") {
                            pattern = "";
                            break;
                        }
                        System.out.print(pattern + " ");
                    }
                }

                if (pattern.equals("XXX")) displayMessage("X");
                else if (pattern.equals("OOO")) displayMessage("O");
            }

            return false;

        }



        public void displayMessage(String winner) {

            JFrame messageWindow = new JFrame();

            messageWindow.setSize(200, 200);
            messageWindow.setLayout(new FlowLayout());
            messageWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
            messageWindow.setResizable(false);
            messageWindow.setVisible(true);


            JTextField message = new JTextField();

            message.setSize(20, 20);
            messageWindow.add(new JTextField("The winner is: " + winner));
        }

        public void transpose() {

            XOButton temporary;

            for (int i = 0; i < 3; i++) {
                for (int j = i; j < 3; j++) {

                    temporary = buttons[i][j];
                    buttons[i][j] = buttons[j][i];
                    buttons[j][i] = temporary;
                }
            }
        }

    }

}