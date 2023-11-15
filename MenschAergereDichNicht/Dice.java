package MenschAergereDichNicht;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Dice {

    static JFrame frame = Frame.frame;
    Game game = new Game();
    ImageIcon icon;
    public static void main(String[] args) {
        Frame.FrameUI();
    }

    void createDice() {
        refreshJFrame();
        String File = "WaitDice.png";
        game.create(7, getIcon(File), "Middle", "Middle");
    }
    
    public void refresh() {
        createDice();
    }

    void setDice(int Nummer) {
        icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Dice\\" + "Dice" + Nummer + ".png");

    }

    public void refreshJFrame() {
        frame = Frame.frame;
    }

    public ImageIcon getIcon(String DataName) {
        icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Dice\\" + DataName);
        return icon;
    }

    // boolean isInTurn() {
    //     if(PlayerOnTurn == ) {
    //         return true;
    //     }
    // }
    
}
