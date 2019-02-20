import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Message extends JFrame {  //window that appears where sb wins the game or there's no winner

   private JButton ok;
   private JLabel message;
   private String winner;



    public Message(String winner){

        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        setPreferredSize(new Dimension(150, 100));

        message = new JLabel("");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setAlignmentY(Component.CENTER_ALIGNMENT);

        pane.add(message);

        ok = new JButton("OK");
        ok.addActionListener(new buttonListener());
        ok.setVisible(true);
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setAlignmentY(Component.CENTER_ALIGNMENT);

        pane.add(ok);

        add(pane);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        pack();

    }

    public void messageInit(String winner){

        if(winner.equals(new String("none"))){
            message.setText("No winner");
        }else{
            message.setText("The winner is: " + winner);
        }

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
