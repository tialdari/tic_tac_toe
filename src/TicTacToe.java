import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends JFrame {  // the main game window


    private static XOButton[][] buttons = new XOButton[3][3];
    private static List<XOButton> usedButtons = new ArrayList<>();

    private final JSplitPane splitPane;
    private final JSplitPane splitPane2;
    private final JSplitPane splitPane3;

    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JButton leftButton;
    private final JButton rightButton;
    public static JLabel points;
    public static JLabel xPlayerPoints;
    public static JLabel oPlayerPoints;

    Font myFont = new Font("Calibri", Font.BOLD, 30);
    Font myFont2 = new Font("Calibri", Font.BOLD, 20);
    Font myFont3 = new Font("Calibri", Font.BOLD, 12);

    int whoseTurn = 1;

    public static int xPoints = 0;
    public static int oPoints = 0;

    public TicTacToe() {

        super("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        splitPane = new JSplitPane();
        splitPane2 = new JSplitPane();
        splitPane3 = new JSplitPane();


        topPanel = new JPanel();
        bottomPanel = new JPanel();

        setPreferredSize(new Dimension(400, 500));

        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);

        SpringLayout layout = new SpringLayout();
        topPanel.setLayout(layout);

        leftButton = new JButton("Reset");
        leftButton.setFont(myFont);
        leftButton.addActionListener(new resetButtonListener());
        points = new JLabel("<html>Player X : Player O </html>" , SwingConstants.CENTER);
        points.setHorizontalAlignment( SwingConstants.CENTER);
        points.setFont(myFont3);

        rightButton = new JButton("New game");
        rightButton.setFont(myFont2);
        rightButton.addActionListener(new newGameButtonListener(this));

        xPlayerPoints = new JLabel(String.valueOf(TicTacToe.xPoints));
        oPlayerPoints = new JLabel((String.valueOf(TicTacToe.oPoints)));

        Label punctuation = new Label(" :");

        topPanel.add(points);
        topPanel.add(xPlayerPoints);
        topPanel.add(punctuation);
        topPanel.add(oPlayerPoints);

        layout.putConstraint(SpringLayout.WEST, points, 2, SpringLayout.WEST, topPanel);

        layout.putConstraint(SpringLayout.NORTH, xPlayerPoints, 20, SpringLayout.NORTH, topPanel);
        layout.putConstraint(SpringLayout.WEST, xPlayerPoints, 42, SpringLayout.WEST, topPanel);

        layout.putConstraint(SpringLayout.NORTH, punctuation, 20, SpringLayout.NORTH, topPanel);
        layout.putConstraint(SpringLayout.WEST, punctuation, 50 , SpringLayout.WEST, topPanel);

        layout.putConstraint(SpringLayout.NORTH, oPlayerPoints, 20, SpringLayout.NORTH, topPanel);
        layout.putConstraint(SpringLayout.WEST, oPlayerPoints, 62 , SpringLayout.WEST, topPanel);



        bottomPanel.setLayout(new GridLayout(3, 3));
        initialiseButtons();

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane3.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        splitPane.setDividerLocation(100);
        splitPane.setTopComponent(splitPane2);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setEnabled(false);

        splitPane2.setDividerLocation(130);
        splitPane2.setTopComponent(leftButton);
        splitPane2.setBottomComponent(splitPane3);
        splitPane2.setEnabled(false);

        splitPane3.setDividerLocation(115);
        splitPane3.setTopComponent(topPanel);
        splitPane3.setBottomComponent(rightButton);
        splitPane3.setEnabled(false);


        setVisible(true);
        pack();
        setLocationRelativeTo(null);
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

                if (pointTheWinner(pattern) == (true)) return true;


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
                xPlayerPoints.setText(String.valueOf(xPoints));
               // points.setText("<html>Player X : Player O  <br/> " + xPoints + " : " + oPoints + "</html>");


                return true;
            } else if (pattern.equals("OOO")) {

                displayMessage("O");
                whoseTurn = 2;
                oPoints++;
                oPlayerPoints.setText(String.valueOf(oPoints));
               // points.setText("<html>Player X : Player O  <br/> " + xPoints + " : " + oPoints + "</html>");
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

        private TicTacToe ticTacToe;

        public newGameButtonListener(TicTacToe ticTacToe){
            this.ticTacToe = ticTacToe;
        }

        public void actionPerformed(ActionEvent e) {

            new NewGameInquiry(ticTacToe);

           // points.setText("<html>Player X : Player O  <br/> " + xPoints + " : " + oPoints + "</html>");

        }
}

    }
