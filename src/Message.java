import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Message extends JFrame {

    JButton ok;
    JLabel message;


    public Message(String winner){

        JPanel panel = new JPanel();
        setSize(150, 150);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        message = new JLabel("");
        ok = new JButton("OK");

        if(winner == "none"){
            message.setText("No winner");
        }else{
            message.setText("The winner is: " + winner);
        }


        message.setFont(new Font("Arial Times", Font.PLAIN, 14));
        panel.add(message);

        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.addActionListener(new buttonListener());
        panel.add(ok);

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
