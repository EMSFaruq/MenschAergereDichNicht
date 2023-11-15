package MenschAergereDichNicht;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Board {

    public static JFrame frame = Frame.frame;

    public static void main(String[] args) {
        Frame.FrameUI();
    }
     
    public void createBackground() {
        ImageIcon BG = new ImageIcon("MenschAergereDichNicht\\Sprites\\Board\\Board.png");

        Game game = new Game();
        game.create(6, BG, "Middle", "Top");

    }

    public void refresh() {
        createBackground();
    }
}
 