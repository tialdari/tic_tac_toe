import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Message extends JFrame {

   private JButton ok;
   private JLabel message;


    public Message(String winner){

        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        setPreferredSize(new Dimension(150, 100));

        message = new JLabel("");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(message);

        if(winner == "none"){
            message.setText("No winner");
        }else{
            message.setText("The winner is: " + winner);
        }

        ok = new JButton("OK");
        ok.addActionListener(new buttonListener());
        ok.setVisible(true);
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(ok);


        add(pane);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        pack();

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
