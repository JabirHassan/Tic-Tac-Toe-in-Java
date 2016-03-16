package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {

    private int width = 445;
    private int height = 445;
    //Mouse coordinates
    private int mouseX = 0, mouseY = 0;
    private boolean player1turn = false;
    private boolean player2turn = false;

    private Image x;    //player 1
    private Image o;    //Player 2
    private Image blank;    //image of white background
    private Rectangle[] rects = new Rectangle[9];   //ArrayList to keep the coordinates and size of all rectangles
    private Image[] images = new Image[9];      //An array of images to store all the images to be drawn
    private ArrayList<Integer> index = new ArrayList<>();    // To store index of each rectangle already clicked
    private Graphics g;

    //Constructor
    public GamePanel() {

        setBackground(Color.white);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);

        try {
            x = ImageIO.read(getClass().getResource("/images/x.png"));
            o = ImageIO.read(getClass().getResource("/images/o.png"));
            blank = ImageIO.read(getClass().getResource("/images/blank.png"));
        } catch (IOException ex) {

        }

        //add coordinates and dimensions of all rectangles
        rects[0] = new Rectangle(2, 2, 148, 148);
        rects[1] = new Rectangle(152, 2, 148, 148);
        rects[2] = new Rectangle(302, 2, 148, 148);
        rects[3] = new Rectangle(2, 152, 148, 148);
        rects[4] = new Rectangle(152, 152, 148, 148);
        rects[5] = new Rectangle(302, 152, 148, 148);
        rects[6] = new Rectangle(2, 302, 148, 148);
        rects[7] = new Rectangle(152, 302, 148, 148);
        rects[8] = new Rectangle(302, 302, 148, 148);
        init();

    }

    public void init() {
        addMouseListener(this); //attach mouse listener
        //Add a blank image to every index in images array
        for (int i = 0; i < images.length; i++) {
            images[i] = blank;
        }
    }

    // Checks if there is a winner
    public void checkWinner() {

        for (int i = 0; i < images.length; i++) {
            if ((images[0] == x && images[1] == x && images[2] == x) || (images[3] == x
                    && images[4] == x && images[5] == x) || (images[6] == x
                    && images[7] == x && images[8] == x) || (images[0] == x
                    && images[3] == x && images[6] == x || (images[1] == x
                    && images[4] == x && images[7] == x) || (images[2] == x
                    && images[5] == x && images[8] == x) || (images[0] == x
                    && images[4] == x && images[8] == x) || (images[2] == x
                    && images[4] == x && images[6] == x))) {

                //displays message box and waits for player's choice
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Player 1 wins?. Do you want to re-start?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    //re-start game
                    new TicTacToe();
                    return;
                } else if (selectedOption == JOptionPane.NO_OPTION) {
                    //Exits game
                    System.exit(0);
                }
            } else if ((images[0] == o && images[1] == o && images[2] == o) || (images[3] == o
                    && images[4] == o && images[5] == o) || (images[6] == o && images[7] == o
                    && images[8] == o) || (images[0] == o && images[3] == o && images[6] == o)
                    || (images[1] == o && images[4] == o && images[7] == o) || (images[2] == o
                    && images[5] == o && images[8] == o) || (images[0] == o && images[4] == o
                    && images[8] == o) || (images[2] == o && images[4] == o && images[6] == o)) {

                //displays message box and waits for player's choice
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Player 2 wins?. Do you want to re-start?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    //re-start game
                    new TicTacToe();
                    return;
                } else if (selectedOption == JOptionPane.NO_OPTION) {
                    //Exits game
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        checkWinner();

        //If the mouse is clicked
        if (mouseX > 0 || mouseY > 0) {
            //Checks which rectangle the mouse is clicked within
            for (int i = 0; i < rects.length; i++) {
                // If it is player 1 turn and the rectangle hasn't been clicked already
                if (rects[i].contains(mouseX, mouseY) && player1turn == true && !index.contains(i)) {
                    images[i] = x;  // Adds image of x to the same index in images array
                    index.add(i);   // Adds the index to index array to prevent from being changed             
                    player1turn = false;
                    player2turn = true;

                    //If it is player 2 turn
                } else if (rects[i].contains(mouseX, mouseY) && player2turn == true && !index.contains(i)) {
                    images[i] = o;  // Adds image o to images array
                    index.add(i);   // adds the index to index array
                    player2turn = false;
                    player1turn = true;

                }

            }
        }
        //Check if it is a draw
        if (index.size() == 9) {
            //Displays a draw message and waits for player's choice
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "It's a draw. Do you want to re-start?",
                    "Choose",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                //re-starts Game
                new TicTacToe();
                return;
            } else if (selectedOption == JOptionPane.NO_OPTION) {
                //Exits game
                System.exit(0);
            }
        }
        //Draws all the images in images array according to locations and dimensions of rectangles in rects array
        for (int i = 0; i < rects.length; i++) {
            g.drawImage(images[i], rects[i].x, rects[i].y, rects[i].width, rects[i].height, this);
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //When the game first starts
        if (player1turn == false && player2turn == false) {
            mouseX = e.getX();
            mouseY = e.getY();
            player1turn = true;
            System.out.println(mouseX + " , " + mouseY);
        }
        //Player 1 turn
        if (player1turn = true && player2turn == false) {
            mouseX = e.getX();
            mouseY = e.getY();
            System.out.println(mouseX + " , " + mouseY);

        }
        //Player 2 turn
        if (player1turn == false && player2turn == true) {
            mouseX = e.getX();
            mouseY = e.getY();

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
