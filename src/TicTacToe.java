import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends JFrame {


    private static XOButton[][] buttons = new XOButton[3][3];
    private static List<XOButton> usedButtons = new ArrayList<>();


    private final JSplitPane verticalSplitPane;
    //private final JSplitPane horizontalSlitPane;
    //private final JSplitPane horizontalSlitPane2;

    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JButton leftButton;
    private final JButton rightButton;
    public static JLabel points;

    private final Font myFont = new Font("Calibri", Font.BOLD, 30);
    private final Font myFont2 = new Font("Calibri", Font.BOLD, 20);

    private int whoseTurn;

     /*
    0: nothing
    1: X
    2: O
     */

     public static int xPoints = 0;
     public static int oPoints = 0;



    public TicTacToe() {

        super("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        verticalSplitPane = new JSplitPane();

        topPanel = new JPanel();
        bottomPanel = new JPanel();

        setPreferredSize(new Dimension(400, 500));

        getContentPane().setLayout(new GridLayout());
        getContentPane().add(verticalSplitPane);

        topPanel.setLayout(new GridLayout());

        leftButton = new JButton("Reset");
        leftButton.setFont(myFont);
        leftButton.addActionListener(new resetButtonListener());
        points = new JLabel("<html>Player X : Player O <br/> 0 : 0</html>", SwingConstants.CENTER);

        rightButton = new JButton("New\ngame");
        rightButton.setFont(myFont2);
        rightButton.addActionListener(new newGameButtonListener());


        topPanel.add(leftButton);
        topPanel.add(points);
        topPanel.add(rightButton);

        bottomPanel.setLayout(new GridLayout(3, 3));
        initialiseButtons();

        verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        verticalSplitPane.setDividerLocation(100);
        verticalSplitPane.setTopComponent(topPanel);
        verticalSplitPane.setBottomComponent(bottomPanel);
        verticalSplitPane.setEnabled(false);

        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        whoseTurn = 1;

    }



    public void initialiseButtons() {


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j] = new XOButton();
                buttons[i][j].addActionListener(new customButtonListener());
                bottomPanel.add(buttons[i][j]);
            }
        }

    }

    public static void reset() {

        usedButtons.clear();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j].setIcon(null);
                buttons[i][j].setButtonSign("");
            }
        }

    }

    private class customButtonListener implements ActionListener {


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

            if (checkForWin() == true) return;
            transpose();
            if (checkForWin() == true) return;
            transpose();

            whoseTurn++;

            if (usedButtons.size() == 9) new Message("none");
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

                if (pointTheWinner(pattern) == true) return true;


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
                if (pointTheWinner(pattern) == true) return true;
            }

            pattern = "";

            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {

                    if (i + j == 2) {

                        sign = buttons[i][j].getButtonSign();
                        pattern += sign;

                        if (sign == "") {
                            pattern = "";
                            break;
                        }
                    }
                }
                if (pointTheWinner(pattern) == true) return true;
            }

            return false;

        }


        public void displayMessage(String winner) {

            new Message(winner);

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



        public boolean pointTheWinner(String pattern) {

            if (pattern.equals("XXX")) {

                displayMessage("X");
                whoseTurn = 2;
                xPoints++;
                points.setText("<html>Player X : Player O  <br/> " + xPoints + " : " + oPoints + "</html>");


                return true;
            } else if (pattern.equals("OOO")) {

                displayMessage("O");
                whoseTurn = 2;
                oPoints++;
                points.setText("<html>Player X : Player O  <br/> " + xPoints + " : " + oPoints + "</html>");
                return true;
            }
            return false;

        }

    }

    private class resetButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

           reset();
        }


        }

    private class newGameButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            new NewGameInquiry();

           // points.setText("<html>Player X : Player O  <br/> " + xPoints + " : " + oPoints + "</html>");

        }
}

    }
