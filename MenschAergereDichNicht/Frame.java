
package MenschAergereDichNicht;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Frame
 */
public class Frame {

    // Frame Variables
    static JFrame frame;
    boolean resizeable = false;
    // Display Variables
    GraphicsEnvironment ge;
    GraphicsDevice[] gd;
    GraphicsDevice device;
    static int output = 1;

    // Display Size
    GraphicsConfiguration screenConfiguration;
    Rectangle screenSize;

    // Display Position
    Point screenPos;
    int screenX;
    int screenY;
    int screenWidth;
    int screenHeight;

    // Game Panel
    int panelX;
    int panelY;
    int panelWidth;
    int panelHeight;
    // static Graphics2D g2d;

    // Class Import
    Game game;

    // JLabel
    static JLabel BCoin = new JLabel();

    public static void main(String[] args) {
        new Frame().Init();
    }

    public void Init() {
        refreshVariables();
        setFrame();
        drawIconImage();

        // Add Panels
        Menus m = new Menus();
        frame.add(m);
        m.setPanel();
        m.Buttons();
        m.test();
    }

    void refreshVariables() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getScreenDevices();

        try {
            device = gd[output];
        } catch (Exception e) {
            device = ge.getDefaultScreenDevice();
        }

        screenConfiguration = device.getDefaultConfiguration();

        // Screen
        screenPos = screenConfiguration.getBounds().getLocation();
        screenSize = screenConfiguration.getBounds();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        screenX = (int) screenPos.getX();
        screenY = (int) screenPos.getY();

        // Panel
        panelWidth = screenWidth;
        panelHeight = screenHeight;
        panelX = screenWidth / 2 - panelWidth / 2;
        panelY = 0;

    }

    void setFrame() {

        // Base Frame
        frame = new JFrame("Mensch Ärgere Dich Nicht | By MrByte");
        frame.pack();
        frame.setSize(screenWidth, screenHeight);
        frame.setMinimumSize(new Dimension(panelWidth, panelHeight));
        frame.setMaximumSize(new Dimension(1920, 1080));
        frame.setLocation(screenX, screenY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Frame Design
        frame.getContentPane().setBackground(new Color(43, 43, 43));
        frame.setVisible(true);
    }

    void drawIconImage() {
        ImageIcon icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Board\\Board.png");
        frame.setIconImage(icon.getImage());
    }

}