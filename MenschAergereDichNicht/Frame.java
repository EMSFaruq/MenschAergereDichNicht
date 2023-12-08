package MenschAergereDichNicht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

class Frame {

    static GraphicsEnvironment ge;
    static GraphicsDevice[] gd;
    static int defOutput;
    static int output = 0;
    static JFrame frame;
    static boolean decorated = true;

    public static void main(String[] args) {
        Frame.FrameUI();

    }

    static void FrameUI() {
        frame = new JFrame("Mensch ärgere Dich nicht!");
        ImageIcon img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Board\\Icon.png");
        frame.setLocationRelativeTo(null);

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getScreenDevices();
        defOutput = getDefaultScreen();

        try {
            gd[output].getDefaultConfiguration();
        } catch (Exception e) {
            output = defOutput;
        }

        Rectangle DisplayBounds = gd[output].getDefaultConfiguration().getBounds();
        frame.setSize((int) DisplayBounds.getWidth(), (int) DisplayBounds.getHeight());
        frame.setLocation(gd[output].getDefaultConfiguration().getBounds().getLocation());
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setIconImage(img.getImage());

        // Frame Behavior
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        Color color = new Color(42, 42, 42);
        frame.getContentPane().setBackground(color);

        EventHandler handler = new EventHandler();
        handler.addKeyListener();
        handler.addResizeListener();

        Menus menus = new Menus();
        menus.Buttons();
        frame.repaint();

        Board background = new Board();
        background.createBackground();

        frame.setVisible(true);
    }

    static int ratio(int size, boolean Height) {
        if (Height) {
            return 1920 * (size - 42) / 1920;
        } else
            return (1920 * size) / 1920;
    }

    static int getDefaultScreen() {
        for (int i = 0; i < gd.length; i++) {
            if (gd[i] == ge.getDefaultScreenDevice()) {
                return i;
            }
        }
        return -1;
    }

    static int currentScreen() {
        GraphicsDevice currentDevice = frame.getGraphicsConfiguration().getDevice();
        for (int i = 0; i < gd.length; i++) {
            if (gd[i] == currentDevice) {
                return i;
            }
        }
        return -1;
    }
}
