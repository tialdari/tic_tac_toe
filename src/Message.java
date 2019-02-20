import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Message extends JFrame {  //window that appears where sb wins the game or there's no winner

    private JPanel pane;
    private JButton ok;
    private JLabel message;
    private String winner;

    public Message(String winner){

        pane = new JPanel();
        ok = new JButton("OK");
        message = new JLabel("");
        this.winner = winner;
    }

    public void windowInit(){

        okButtonInit();
        messageInit(winner);
        paneInit(ok, message);

        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(150, 100));

        pack();
    }

    public void paneInit(JButton ok, JLabel message){

        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(message);
        pane.add(ok);
        add(pane);
    }

    public void okButtonInit(){

        ok.addActionListener(new buttonListener());
        ok.setVisible(true);
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    public void messageInit(String winner){

        if(winner.equals(new String("none"))){
            message.setText("No winner");
        }else{
            message.setText("The winner is: " + winner);
        }

        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    private class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource().equals(ok)){
                TicTacToe.reset();
                    dispose();
            }
        }
    }
}
