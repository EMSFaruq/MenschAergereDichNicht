package MenschAergereDichNicht;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dice {
    int CubeNr = 7;
    JFrame frame = Frame.frame;
    Game game = new Game();
    ImageIcon icon;
    JLabel dice = Game.IconLabel[CubeNr];

    public static void main(String[] args) {
        Frame.FrameUI();
    }

    void createDice() {
        refreshJFrame();
        String File = "WaitDice.png";
        game.create(7, getIcon(File), "Middle", "Middle");

    }

    public void refresh() {
        ImageIcon LastIcon = new ImageIcon((Image) Game.IconLabel[CubeNr].getIcon());
        LastIcon = new ImageIcon(LastIcon.getImage().getScaledInstance(
                Frame.ratio(LastIcon.getIconWidth(), false),
                Frame.ratio(LastIcon.getIconHeight(), !frame.isUndecorated()), Image.SCALE_SMOOTH));
        createDice();
        try {
            Game.IconLabel[CubeNr].setIcon(LastIcon);
        } catch (Exception e) {
            createDice();
        }
    }

    void setDice(int Nummer) {
        icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Dice\\" + "Dice" + Nummer + ".png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(
                Frame.ratio(icon.getIconWidth(), false),
                Frame.ratio(icon.getIconHeight(), !frame.isUndecorated()), Image.SCALE_SMOOTH));
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
