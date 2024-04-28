package MenschAergereDichNicht;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board {

    JPanel frame = new Frame();

    public static void main(String[] args) {
        new Frame();
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
