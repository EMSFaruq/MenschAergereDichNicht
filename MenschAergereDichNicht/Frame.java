package MenschAergereDichNicht;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

public class Frame {

    public static void main(String[] args) {
        Frame f = new Frame();
        f.createFrame();
        GamePanel gp = new GamePanel();
        gp.setPanel();
        frame.add(gp);
        GamePanel.Menu = "Main";
    }

    static JFrame frame;

    void createFrame() {
        frame = new JFrame("Mensch Aergere Dich Nicht | By MrByte");
        frame.setLayout(null);
        frame.setSize(getScreenSize());
        frame.setLocation(getScreenPosition());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    GraphicsDevice getScreenInfos() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return ge.getDefaultScreenDevice();

    }

    Dimension getScreenSize() {
        return new Dimension(getScreenInfos().getDefaultConfiguration().getBounds().getSize());
    }

    Point getScreenPosition() {
        return new Point(getScreenInfos().getDefaultConfiguration().getBounds().getLocation());
    }
}
