import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends JFrame {

    private final int NUMBER_OF_BUTTONS = 9;
    private static final int COLS_NUM = 3;
    private static final int ROWS_NUM = 3;
    private final String CROSS_WIN_PATTERN = "XXX";
    private final String CIRCLE_WIN_PATTERN = "OOO";
    private int[] diagonalIndexesUp_Down;
    private int[] diagonalIndexesDown_Up;
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

        diagonalIndexesUp_Down = new int[ROWS_NUM];
        diagonalIndexesDown_Up = new int[ROWS_NUM];

        buttons = new XOButton[ROWS_NUM][COLS_NUM];
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
        diagonalIndexesInit();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    public void diagonalIndexesInit() {

        for (int i = 0; i < ROWS_NUM; i++) {
            diagonalIndexesUp_Down[i] = 3 * i + i;
            diagonalIndexesDown_Up[i] = ROWS_NUM * (i + 1) - (i + 1);
            System.out.println(diagonalIndexesDown_Up[i]);
        }
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

        bottomPanel.setLayout(new GridLayout(ROWS_NUM, COLS_NUM));
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

        for (int i = 0; i < ROWS_NUM; i++) {
            for (int j = 0; j < COLS_NUM; j++) {

                buttons[i][j] = new XOButton();
                buttons[i][j].addActionListener(new customButtonListener());
                bottomPanel.add(buttons[i][j]);
            }
        }

    }

    public static void reset() {

        usedButtons.clear();

        for (int i = 0; i < ROWS_NUM; i++) {
            for (int j = 0; j < COLS_NUM; j++) {

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

            final int PLAYER_ONE = 0;
            final int PLAYER_TWO = 1;

            switch (whoseTurn) {

                case PLAYER_ONE:
                    buttonClicked.setIcon(buttonClicked.getImageX());
                    buttonClicked.setButtonSign("X");
                    break;

                case PLAYER_TWO:
                    buttonClicked.setIcon(buttonClicked.getImageO());
                    buttonClicked.setButtonSign("O");
                    break;

            }

            if (checkForWin()) return;
            transpose();
            if (checkForWin()) return;
            transpose();

            whoseTurn++;

            if (usedButtons.size() == NUMBER_OF_BUTTONS){
                JFrame message = new Message("none");
                ((Message) message).windowInit();
            }
        }


         public boolean checkForWin(){

           String sign = "";
           StringBuilder pattern = new StringBuilder();

            for (int i = 0; i < ROWS_NUM; i++) {
                for (int j = 0; j < COLS_NUM; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern.append(sign);
                }
                if (patternCheck(pattern)) return true;
            }

             int rowIndex;
             int columnIndex;

            for (int i = 0; i < ROWS_NUM; i++) {

                columnIndex = diagonalIndexesUp_Down[i] % ROWS_NUM;
                rowIndex = i;
               sign = buttons[rowIndex][columnIndex].getButtonSign();
               pattern.append(sign);
               System.out.println("i: " + i + ", row index: " + rowIndex + ", columnIndex: " + columnIndex);
            }

             if (patternCheck(pattern)) return true;

            for (int i = 0; i < ROWS_NUM; i++) {

                columnIndex = diagonalIndexesDown_Up[i] % ROWS_NUM;
                rowIndex = i;
                sign = buttons[rowIndex][columnIndex].getButtonSign();
                pattern.append(sign);
                System.out.println("i: " + i + ", row index: " + rowIndex + ", columnIndex: " + columnIndex);

            }

             if (patternCheck(pattern)) return true;
            else return  false;
         }


        private boolean patternCheck(StringBuilder pattern){

            if (pointTheWinner(pattern.toString())) return true;
            else {
                pattern.delete(0, pattern.length());
                return false;
            }
        }

        /*
        public boolean checkForWin() {

            String sign = "";
            StringBuilder pattern = new StringBuilder();

            for (int i = 0; i < ROWS_NUM; i++) {

                for (int j = 0; j < COLS_NUM; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern.append(sign);
                }

                if (pointTheWinner(pattern.toString())) return true;
            }

            System.out.println(pattern);
            pattern.delete(0, pattern.length());

            for (int i = 0; i < ROWS_NUM; i++) {
                for (int j = i; j == i; j++) {

                    sign = buttons[i][j].getButtonSign();
                    pattern.append(sign);

                    if (sign.equals(new String(""))) {
                        pattern.delete(0, pattern.length());
                        break;
                    }

                }
                if (pointTheWinner(pattern.toString())) return true;
            }

            pattern.delete(0, pattern.length());

            for (int i = 0; i < ROWS_NUM; i++) {
                for (int j = 2; j >= 0; j--) {

                    if (i + j == 2) {

                        sign = buttons[i][j].getButtonSign();
                        pattern.append(sign);

                        if (sign.equals(new String(""))) {
                            pattern.delete(0, pattern.length());
                            break;
                        }
                    }
                }
                if (pointTheWinner(pattern.toString()) == true) return true;
            }

            return false;
        }

*/

        public void displayMessage(String winner) {

            JFrame message = new Message(winner);
            ((Message) message).windowInit();

        }

        private void transpose() {

            XOButton temporary;

            for (int i = 0; i < ROWS_NUM; i++) {
                for (int j = i; j < COLS_NUM; j++) {

                    temporary = buttons[i][j];
                    buttons[i][j] = buttons[j][i];
                    buttons[j][i] = temporary;
                }
            }
        }

        private boolean pointTheWinner(String pattern) {

            if (pattern.equals(CROSS_WIN_PATTERN)) {

                displayMessage("X");
                whoseTurn = 2;
                xPoints++;
                xPlayerPoints.setText(String.valueOf(xPoints));
               // points.setText("<html>Player X : Player O  <br/> " + xPoints + " : " + oPoints + "</html>");


                return true;
            } else if (pattern.equals(CIRCLE_WIN_PATTERN)) {

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
