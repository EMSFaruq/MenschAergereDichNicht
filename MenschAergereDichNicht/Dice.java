package MenschAergereDichNicht;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dice {
    int CubeNr = 7;
    JPanel frame = new Frame();
    Game game = new Game();
    ImageIcon icon;
    JLabel dice = Game.IconLabel[CubeNr];

    public static void main(String[] args) {
        new Frame().Init();
    }

    void createDice() {
        refreshJFrame();
        String File = "WaitDice.png";
        game.create(7, getIcon(File), "Middle", "Middle");

    }

    public void refresh() {
        ImageIcon LastIcon = new ImageIcon((Image) Game.IconLabel[CubeNr].getIcon());
        LastIcon = new ImageIcon(LastIcon.getImage().getScaledInstance(
                LastIcon.getIconWidth(),
                LastIcon.getIconWidth(), Image.SCALE_SMOOTH));
        createDice();
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
                icon.getIconWidth(),
                icon.getIconWidth(), Image.SCALE_SMOOTH));
        dice.setIcon(icon);

    }

    public void refreshJFrame() {
        frame = new Frame();
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
