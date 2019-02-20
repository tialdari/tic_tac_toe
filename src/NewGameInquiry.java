import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameInquiry extends JFrame {

    private final JButton yes;
    private final JButton no;
    private final JPanel panel;
    private JLabel message;
    private TicTacToe ticTacToe;

    public NewGameInquiry(TicTacToe ticTacToe){

        yes = new JButton("YES");
        no = new JButton("NO");
        panel = new JPanel();
        message = new JLabel("Are you sure you want to start a new game?");
        this.ticTacToe = ticTacToe;
    }

    public void windowInit(){

        yesNoInit();
        messageInit();
        panelInit(yes, no, message);
        add(panel);

        setSize(300, 100);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void panelInit(JButton yes, JButton no, JLabel message){

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(message);
        panel.add(yes);
        panel.add(no);
    }

    public void yesNoInit(){

        yes.addActionListener(new yesButtonListener());
        no.addActionListener(new noButtonListener());
        yes.setAlignmentX(CENTER_ALIGNMENT);
        no.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void messageInit(){

        message.setAlignmentX(CENTER_ALIGNMENT);
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
