import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends JFrame {


    XOButton[][] buttons = new XOButton[3][3];
    List<XOButton> usedButtons = new ArrayList<>();


    private final JSplitPane splitPane;
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JButton leftButton;
    private final JButton rightButton;
    private final JLabel points;
    Font myFont = new Font("Calibri", Font.BOLD, 30);
    Font myFont2 = new Font("Calibri", Font.BOLD, 20);

    int whoseTurn = 1;

     /*
    0: nothing
    1: X
    2: O
     */

    public TicTacToe() {

        super("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        splitPane = new JSplitPane();

        topPanel = new JPanel();
        bottomPanel = new JPanel();

        setPreferredSize(new Dimension(400, 500));

        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);

        topPanel.setLayout(new GridLayout(1, 3));

        leftButton = new JButton("Reset");
        leftButton.setFont(myFont);
        points = new JLabel("<html>Player 1 : Player 2  <br/> 0 : 0</html>" , SwingConstants.CENTER);
        /*
        String  sText  = "<html>Player 1 : Player 2  <br/> 0 : 0</html>";
        points.setText (sText);
        points.setHorizontalAlignment( SwingConstants.CENTER);
        */
        rightButton = new JButton("New\ngame");
        rightButton.setFont(myFont2);


        topPanel.add(leftButton);
        topPanel.add(points);
        topPanel.add(rightButton);

        bottomPanel.setLayout(new GridLayout(3, 3));
        initialiseButtons();

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(100);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setEnabled(false);

        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }

    public void initialiseButtons() {


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j] = new XOButton();
                buttons[i][j].addActionListener(new buttonListener());
                bottomPanel.add(buttons[i][j]);
            }
        }

    }


    private class buttonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            XOButton buttonClicked = (XOButton) e.getSource();

            if (usedButtons.contains(buttonClicked)) return;
            usedButtons.add(buttonClicked);

            whoseTurn %= 2;

            switch (whoseTurn) {

                case 0:
                    buttonClicked.setIcon(buttonClicked.getImageX());
                    buttonClicked.setButtonSign("X");
                    break;

                case 1:
                    buttonClicked.setIcon(buttonClicked.getImageO());
                    buttonClicked.setButtonSign("O");
                    break;


            }

            if(checkForWin() == true) return;
            transpose();
            if(checkForWin() == true) return;
            transpose();

            whoseTurn++;

            if(usedButtons.size() == 9) reset();

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

                System.out.println(pattern);
                if(pointTheWinner(pattern) == true) return true;


            }

            System.out.println(pattern);

            pattern = "";
            for (int i = 0; i < 3; i++) {
                for (int j = i; j == i; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern += sign;

                    if (sign == "") {
                        pattern = "";
                        break;
                    }

                }
                System.out.println(pattern);
                if(pointTheWinner(pattern) == true) return true;
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
                    }
                }
                System.out.println(pattern);
                if(pointTheWinner(pattern) == true) return true;
                         }

            return false;

        }


        public void displayMessage(String winner) {

            new Message(winner);
            reset();

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

        public void reset(){

            usedButtons.clear();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    buttons[i][j].setIcon(null);
                    buttons[i][j].setButtonSign("");
                }
            }

        }

        public boolean pointTheWinner(String pattern){

            if (pattern.equals("XXX")){

                displayMessage("X");
               // reset();
                whoseTurn = 2;
                return true;
            }
            else if (pattern.equals("OOO")){

                displayMessage("O");
              //  reset();
                whoseTurn = 2;
                return true;
            }
            return false;

        }

    }

}
