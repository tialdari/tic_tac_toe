import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    JPanel p = new JPanel();
    XOButton [] buttons  = new XOButton[9];

/*
    public static void main(String [] args){
        new TicTacToe();
    }
*/
    public TicTacToe(){
        super("Tic Tac Toe");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(3, 3));

        for(int i = 0; i < 9; i ++){
            buttons[i] = new XOButton();
            p.add(buttons[i]);

        }
        add(p);

        setVisible(true);

    }

    public void play(){

        boolean player1win = false;
        boolean player2win = false;


        while(!player1win || !player2win){

            //player1 moves
            //player2 moves
            //check for win
            //or end of game


        }
    }

}
