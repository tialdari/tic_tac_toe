import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeExtended extends JFrame {

    private final JSplitPane splitPane;
    private final JSplitPane splitPane2;
    private final JSplitPane splitPane3;

    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JButton leftButton;
    private final JButton rightButton;
    private final JLabel points;
    private JLabel xPlayerPoints;
    private JLabel oPlayerPoints;
    Font myFont = new Font("Calibri", Font.BOLD, 30);
    Font myFont2 = new Font("Calibri", Font.BOLD, 20);
    Font myFont3 = new Font("Calibri", Font.BOLD, 12);



    public TicTacToeExtended() {

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
        points = new JLabel("<html>Player X : Player O </html>" , SwingConstants.CENTER);
        points.setHorizontalAlignment( SwingConstants.CENTER);
        points.setFont(myFont3);

        /*
        String  sText  = "<html>Player 1 : Player 2  <br/> 0 : 0</html>";
        points.setText (sText);
        points.setHorizontalAlignment( SwingConstants.CENTER);
        */

        rightButton = new JButton("New game");
        rightButton.setFont(myFont2);

        xPlayerPoints = new JLabel(String.valueOf(TicTacToe.xPoints));
        oPlayerPoints = new JLabel((String.valueOf(TicTacToe.oPoints)));

       // pane.add(button, BorderLayout.PAGE_START);

        /*
        topPanel.add(points, BorderLayout.PAGE_START);
        topPanel.add(xPlayerPoints, BorderLayout.CENTER);
        topPanel.add(new Label(" : "), BorderLayout.CENTER);
        topPanel.add(oPlayerPoints, BorderLayout.LINE_END);


        layout.putConstraint(SpringLayout.WEST, label,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,
                5,
                SpringLayout.NORTH, contentPane);

         */

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
        // bottomPanel.initialiseButtons();

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
}