import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe2 extends JFrame {


    XOButton2 [][] buttons = new XOButton2[3][3];
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

         /*
         for(int i = 0; i < 9; i ++){

             buttons[i] = new XOButton2();
             buttons[i].addActionListener(new buttonListener());
             p.add(buttons[i]);

         }
        */

         for(int i = 0; i < 3; i ++){
             for(int j = 0; j < 3; j++) {


                 buttons[i][j] = new XOButton2();
                 buttons[i][j].addActionListener(new buttonListener());
                 p.add(buttons[i][j]);
             }
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
                     buttonClicked.setIcon(buttonClicked.getImageX());
                     buttonClicked.setButtonValue(1);
                     break;

                 case 2:
                     buttonClicked.setIcon(buttonClicked.getImageO());
                     buttonClicked.setButtonValue(2);
                     break;
             }

             checkForWin();
             System.out.println("");

             whoseTurn++;
         }

         public void checkForWin(){

             int sum = 0;
             int buttonValue;

             for(int i = 0; i < 3; i ++) {
                 sum = 0;
                 for (int j = 0; j < 3; j++) {

                     buttonValue = buttons[i][j].getButtonValue();
                     sum += buttonValue;

                     System.out.print(buttonValue + " ");

                     if(buttonValue == 0) {
                         // sum = 0;
                          break;
                     }
                 }

                 if(sum == 3){
                     displayCommunicate(1);
                 }else if(sum == 6){
                     displayCommunicate(2);
                 }
                 
                 System.out.print(" Row sum is: " + sum);
                 System.out.println(" ");
             }
         }

         public void displayCommunicate(int imageValue){

             JFrame communicateWindow = new JFrame();
             communicateWindow.setSize(200,200);
             communicateWindow.setLayout(new FlowLayout());
             //communicateWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);


             JTextField communicate = new JTextField();
             communicate.setSize(20, 20);
             communicateWindow.add(new JTextField("The winner is: "));

             switch(imageValue){

                 case 1:
                     communicateWindow.add(new JTextField("X"));
                     break;
                 case 2:
                     communicateWindow.add(new JTextField("O"));
                     break;
             }
             communicateWindow.setVisible(true);


         }
     }
}

