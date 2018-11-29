import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameInquiry extends JFrame { //window that appears when the "new game" button is clicked

    private final JButton yes;
    private final JButton no;
    private final JPanel panel;
    private TicTacToe ticTacToe;

    public NewGameInquiry(TicTacToe ticTacToe){

        panel = new JPanel();
        setSize(300, 100);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        this.ticTacToe = ticTacToe;

        JLabel message = new JLabel("Are you sure you want to start a new game?");

        yes = new JButton("YES");
        no = new JButton("NO");

        message.setAlignmentX(CENTER_ALIGNMENT);
        yes.addActionListener(new yesButtonListener());
        no.addActionListener(new noButtonListener());

        yes.setAlignmentX(CENTER_ALIGNMENT);
        no.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(message);
        panel.add(yes);
        panel.add(no);
    }

    private class yesButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

           int xPoints = TicTacToe.xPoints = 0;
           int oPoints = TicTacToe.oPoints = 0;

            TicTacToe.xPlayerPoints.setText(String.valueOf(xPoints));
            TicTacToe.oPlayerPoints.setText(String.valueOf(oPoints));


            if(e.getSource().equals(yes)){
                ticTacToe.reset();
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
