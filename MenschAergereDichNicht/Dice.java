package MenschAergereDichNicht;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dice {

    JFrame frame = Frame.frame;
    Game game = new Game();
    ImageIcon icon;
    JLabel dice = Game.IconLabel[7];

    public static void main(String[] args) {
        Frame.FrameUI();
    }

    void createDice() {
        refreshJFrame();
        String File = "WaitDice.png";
        game.create(7, getIcon(File), "Middle", "Middle");

    }

    public void refresh() {
        ImageIcon LastIcon = new ImageIcon((Image) Game.IconLabel[7].getIcon());
        createDice();
        try {
            Game.IconLabel[7].setIcon(LastIcon);
        } catch (Exception e) {
            createDice();
        }
    }

    void setDice(int Nummer) {
        icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Dice\\" + "Dice" + Nummer + ".png");
        dice.setIcon(icon);

    }

    public void refreshJFrame() {
        frame = Frame.frame;
    }

    public ImageIcon getIcon(String DataName) {
        icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Dice\\" + DataName);
        return icon;
    }

    // boolean isInTurn() {
    // if(PlayerOnTurn == ) {
    // return true;
    // }
    // }

}
