package MenschAergereDichNicht;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

class Frame {

    static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static GraphicsDevice[] gd = ge.getScreenDevices();
    static int defOutput = getDefaultScreen();
    static int output = 0;
    static JFrame frame = new JFrame("Mensch ärgere Dich nicht!");
    static boolean decorated = true;
    
    public static void main(String[] args) {
        FrameUI();
        
    }
    static void FrameUI() {
        ImageIcon img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Board\\Icon.png");
        frame.setLocationRelativeTo(null);

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

        //Frame Behavior
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
    
    static int ratio(int size) {
        return size * frame.getWidth() / 1920;
    }
    
    static int getDefaultScreen() {
        for (int i = 0; i < gd.length; i++) {
            if(gd[i] == ge.getDefaultScreenDevice()) {
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


       