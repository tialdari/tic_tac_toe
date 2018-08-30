import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameInquiry extends JFrame {

    private final JButton yes;
    private final JButton no;
    private final JPanel panel;


    public NewGameInquiry(){

        panel = new JPanel();
        setSize(300, 150);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel message = new JLabel("Are you sure you want to start a new game?");

        yes = new JButton("YES");
        no = new JButton("NO");

        yes.addActionListener(new yesButtonListener());
        no.addActionListener(new noButtonListener());

        panel.add(message);
        panel.add(yes);
        panel.add(no);
    }

    private class yesButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            TicTacToe.xPoints = 0;
            TicTacToe.oPoints = 0;
            TicTacToe.points.setText("<html>Player X : Player O  <br/>  0 : 0 </html>");

            if(e.getSource().equals(yes)){
                dispose();
            }
        }
    }


    private class noButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if(e.getSource().equals(no)){
                dispose();
            }

        }


    }

}
