import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends JFrame {


    private static XOButton[][] buttons;
    private static List<XOButton> usedButtons;

    private final JSplitPane splitPane;
    private final JSplitPane splitPane2;
    private final JSplitPane splitPane3;

    private Label punctuation;
    private SpringLayout layout;
    private final JPanel topPanel;
    private final JPanel bottomPanel;

    private final JButton leftButton;
    private final JButton rightButton;

    public static JLabel points;
    public static JLabel xPlayerPoints;
    public static JLabel oPlayerPoints;

    private final Font myFont;
    private final Font myFont2;
    private final Font myFont3;

    private int whoseTurn;

    public static int xPoints;
    public static int oPoints;

    public TicTacToe(){

        super("Tic Tac Toe");

        buttons = new XOButton[3][3];
        usedButtons = new ArrayList<>();

        splitPane = new JSplitPane();
        splitPane2 = new JSplitPane();
        splitPane3 = new JSplitPane();

        punctuation = new Label(" :");
        layout = new SpringLayout();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        leftButton = new JButton("Reset");
        rightButton = new JButton("New game");

        points = new JLabel("<html>Player X : Player O </html>" , SwingConstants.CENTER);
        xPlayerPoints = new JLabel(String.valueOf(TicTacToe.xPoints));
        oPlayerPoints = new JLabel((String.valueOf(TicTacToe.oPoints)));

        myFont = new Font("Calibri", Font.BOLD, 30);
        myFont2 = new Font("Calibri", Font.BOLD, 20);
        myFont3 = new Font("Calibri", Font.BOLD, 12);

        whoseTurn = 1;

        xPoints = 0;
        oPoints = 0;
    }


    public void windowInit(){

        initialiseButtons();
        panelButtonsInit();
        pointsInit();
        layoutInit();
        panelsInit();
        splitPaneInit();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void panelButtonsInit(){

        leftButton.setFont(myFont);
        leftButton.addActionListener(new resetButtonListener());

        rightButton.setFont(myFont2);
        rightButton.addActionListener(new newGameButtonListener(this));
    }

    private void pointsInit(){

        points.setHorizontalAlignment(SwingConstants.CENTER);
        points.setFont(myFont3);
    }

    private void layoutInit(){

        layout.putConstraint(SpringLayout.WEST, points, 2, SpringLayout.WEST, topPanel);

        layout.putConstraint(SpringLayout.NORTH, xPlayerPoints, 20, SpringLayout.NORTH, topPanel);
        layout.putConstraint(SpringLayout.WEST, xPlayerPoints, 42, SpringLayout.WEST, topPanel);

        layout.putConstraint(SpringLayout.NORTH, punctuation, 20, SpringLayout.NORTH, topPanel);
        layout.putConstraint(SpringLayout.WEST, punctuation, 50 , SpringLayout.WEST, topPanel);

        layout.putConstraint(SpringLayout.NORTH, oPlayerPoints, 20, SpringLayout.NORTH, topPanel);
        layout.putConstraint(SpringLayout.WEST, oPlayerPoints, 62 , SpringLayout.WEST, topPanel);
    }

    private void panelsInit(){

        topPanel.setLayout(layout);
        topPanel.add(points);
        topPanel.add(xPlayerPoints);
        topPanel.add(punctuation);
        topPanel.add(oPlayerPoints);

        bottomPanel.setLayout(new GridLayout(3, 3));
    }

    private void splitPaneInit(){

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
    }

    private void initialiseButtons() {

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

            if (checkForWin()) return;
            transpose();
            if (checkForWin()) return;
            transpose();

            whoseTurn++;

            if (usedButtons.size() == 9){
                JFrame message = new Message("none");
                ((Message) message).windowInit();
            }
        }


        public boolean checkForWin() {

            String sign = "";
            StringBuilder pattern = new StringBuilder();

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern.append(sign);
                }

                if (pointTheWinner(pattern.toString())) return true;
            }

            System.out.println(pattern);
            pattern.delete(0, pattern.length());

            for (int i = 0; i < 3; i++) {
                for (int j = i; j == i; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern.append(sign);

                    if (sign == "") {
                        pattern.delete(0, pattern.length());
                        break;
                    }

                }
                if (pointTheWinner(pattern.toString())) return true;
            }

            pattern.delete(0, pattern.length());

            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {

                    if (i + j == 2) {

                        sign = buttons[i][j].getButtonSign();
                        pattern.append(sign);

                        if (sign == "") {
                            pattern.delete(0, pattern.length());
                            break;
                        }
                    }
                }
                if (pointTheWinner(pattern.toString()) == true) return true;
            }

            return false;

        }


        public void displayMessage(String winner) {

            JFrame message = new Message(winner);
            ((Message) message).windowInit();

        }

        private void transpose() {

            XOButton temporary;

            for (int i = 0; i < 3; i++) {
                for (int j = i; j < 3; j++) {

                    temporary = buttons[i][j];
                    buttons[i][j] = buttons[j][i];
                    buttons[j][i] = temporary;
                }
            }
        }


        private boolean pointTheWinner(String pattern) {

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

           JFrame newGameInquiry = new NewGameInquiry(ticTacToe);
           ((NewGameInquiry) newGameInquiry).windowInit();
        }
    }

}
