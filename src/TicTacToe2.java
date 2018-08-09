import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe2 extends JFrame {


    XOButton2 [] buttons = new XOButton2[9];
    List<XOButton2> usedButtons = new ArrayList<XOButton2>();
    JPanel p = new JPanel();
    int whoseTurn = 2;

     /*
    0: nothing
    1: X
    2: O
     */

     public TicTacToe2(){

         super("Tic Tac Toe");
         setSize(400, 400);
         setResizable(false);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         p.setLayout(new GridLayout(3, 3));
         add(p);
         initialiseButtons();

         setVisible(true);
     }

     public void initialiseButtons(){

         for(int i = 0; i < 9; i ++){

             buttons[i] = new XOButton2();
             buttons[i].addActionListener(new buttonListener());
             p.add(buttons[i]);

         }

     }

     private class buttonListener implements ActionListener{


         @Override
         public void actionPerformed(ActionEvent e) {

             XOButton2 buttonClicked = (XOButton2) e.getSource();
             if(usedButtons.contains(buttonClicked)) return;
             usedButtons.add(buttonClicked);
             whoseTurn %= 3;


             switch(whoseTurn){

                 case 0:
                     whoseTurn++;

                 case 1:
                     buttonClicked.setIcon(new ImageIcon(this.getClass().getResource("cross.jpg")));
                     buttonClicked.setButtonValue(1);
                     break;

                 case 2:
                     buttonClicked.setIcon(new ImageIcon(this.getClass().getResource("circle.jpg")));
                     buttonClicked.setButtonValue(2);
                     break;
             }

             checkForWin();
             whoseTurn++;
         }

         public boolean checkForWin(){

             int sum = 0;
             int button = 0;

             for(int i = 0; i < 9; i++){

                 System.out.print(buttons[i].getButtonValue());

             }

                System.out.println("");

            displayCommunicate(1);
            return true;
         }

         public void displayCommunicate(int imageValue){

             JFrame communicateWindow = new JFrame();
             communicateWindow.setSize(200,200);
             communicateWindow.setLayout(new FlowLayout());
             
             JTextField communicate = new JTextField();
             communicate.setSize(20, 20);
             communicateWindow.add(new JTextField("The winner is: "));

             switch(imageValue){

                 case 1:
                     communicateWindow.add(new JTextField(" X"));
                     break;
                 case 2:
                     communicateWindow.add(new JTextField(" O"));
                     break;
             }
             communicateWindow.setVisible(true);


         }
     }
}

