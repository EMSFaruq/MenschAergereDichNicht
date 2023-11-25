package MenschAergereDichNicht;


import java.awt.Image;

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
        game.create(6, new ImageIcon(BG.getImage().getScaledInstance(
        BG.getIconWidth(), 
        BG.getIconHeight() - 47, 
        Image.SCALE_SMOOTH)), "Middle", "Top", true);
    }

    public void refresh() {
        createBackground();
    }
}
 