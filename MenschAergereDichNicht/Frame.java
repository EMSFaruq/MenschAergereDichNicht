package MenschAergereDichNicht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

class Frame {

    static GraphicsEnvironment ge;
    static GraphicsDevice[] gd;
    static int defOutput;
    static int output = 2;
    static JFrame frame;
    static boolean decorated = true;
    static GraphicsConfiguration Screen;

    public static void main(String[] args) {
        Frame.FrameUI();
    }

    static void FrameUI() {
        frame = new JFrame("Mensch ärgere Dich nicht!");

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getScreenDevices();
        defOutput = getDefaultScreen();

        try {
            Screen = gd[output].getDefaultConfiguration();
        } catch (Exception e) {
            Screen = gd[defOutput].getDefaultConfiguration();
        }

        int Width = (int) Screen.getBounds().getWidth();
        int Height = (int) Screen.getBounds().getHeight();

        // frame.setSize(Width, Height);
        frame.setSize(Width, Height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocation(Screen.getBounds().getLocation());

        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(800, 600));

        ImageIcon img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Board\\Icon.png");
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
            return size + (frame.getHeight() - frame.getContentPane().getHeight()) / 1080;
        } else
            return (frame.getWidth() * size) / 1920;
    }

    static int getDefaultScreen() {
        for (int i = 0; i < gd.length; i++) {
            if (gd[i] == ge.getDefaultScreenDevice()) {
                return i;
            }
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Standart Monitor nicht gefunden!");
        return -1;
    }

    static int currentScreen() {
        GraphicsDevice currentDevice = frame.getGraphicsConfiguration().getDevice();
        for (int i = 0; i < gd.length; i++) {
            if (gd[i] == currentDevice) {
                return i;
            }
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Aktuellen Monitor nicht gefunden!");
        return -1;
    }
}
