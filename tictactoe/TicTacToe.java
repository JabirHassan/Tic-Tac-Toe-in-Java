package tictactoe;

import javax.swing.JFrame;

public class TicTacToe {

    GamePanel game;

    //Constructor
    public TicTacToe() {
        game = new GamePanel();
        JFrame frame = new JFrame("Tic Tac Toe");

        //panel.add(new GamePanel());
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();

    }

}
