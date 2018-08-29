import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeExtended extends JFrame {

    private final JSplitPane splitPane;
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JButton leftButton;
    private final JButton rightButton;
    private final JLabel points;
    Font myFont = new Font("Calibri", Font.BOLD, 30);
    Font myFont2 = new Font("Calibri", Font.BOLD, 20);


    public TicTacToeExtended() {

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
        // bottomPanel.initialiseButtons();

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(100);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setEnabled(false);

        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
}