import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe2 extends JFrame {


    XOButton2[][] buttons = new XOButton2[3][3];
    List<XOButton2> usedButtons = new ArrayList<XOButton2>();
    JPanel p = new JPanel();
    int whoseTurn = 2;


     /*
    0: nothing
    1: X
    2: O
     */

    public TicTacToe2() {

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

         /*
         for(int i = 0; i < 9; i ++){

             buttons[i] = new XOButton2();
             buttons[i].addActionListener(new buttonListener());
             p.add(buttons[i]);

         }
        */

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {


                buttons[i][j] = new XOButton2();
                buttons[i][j].addActionListener(new buttonListener());
                p.add(buttons[i][j]);
            }
        }

    }

    private class buttonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            XOButton2 buttonClicked = (XOButton2) e.getSource();
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
                    displayCommunicate("X");
                    return true;

                } else if (pattern.equals("OOO")) {
                    displayCommunicate("O");
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

               // System.out.println(pattern + " ");

                if (pattern.equals("XXX")) displayCommunicate("X");
                else if (pattern.equals("OOO")) displayCommunicate("O");
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


                if (pattern.equals("XXX")) displayCommunicate("X");
                else if (pattern.equals("OOO")) displayCommunicate("O");


            }

            return false;

        }

        public void displayCommunicate(String winner) {

            JFrame communicateWindow = new JFrame();
            communicateWindow.setSize(200, 200);
            communicateWindow.setLayout(new FlowLayout());
            communicateWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);


            JTextField communicate = new JTextField();
            communicate.setSize(20, 20);
            communicateWindow.add(new JTextField("The winner is: " + winner));
            communicateWindow.setResizable(false);
            communicateWindow.setVisible(true);
        }

        public void transpose() {

            XOButton2 temporary;

            for (int i = 0; i < 3; i++) {
                for (int j = i; j < 3; j++) {

                    temporary = buttons[i][j];
                    buttons[i][j] = buttons[j][i];
                    buttons[j][i] = temporary;
                }
            }

            /*
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    System.out.print(buttons[i][j].getButtonSign() + " ");
                }
                System.out.println("");
            }
            System.out.println("");
            */
        }

         /*
         public void reset(){
             for(int i = 0; i < 3; i++){
                 for(int j = 0; j < 3; j++){
                     buttons[i][j].setButtonValue(0);
                     buttons[i][j].setIcon(buttons[i][j].getN());
                 }
             }
         }
         */

    }

}