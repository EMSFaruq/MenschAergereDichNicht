package MenschAergereDichNicht;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    boolean resizable = true;
    boolean fullscreen = false;

    void createFrame() {
        frame = new JFrame("Mensch Aergere Dich Nicht | By MrByte");
        frame.setLayout(null);
        frame.setSize(getScreenSize());
        frame.setUndecorated(fullscreen);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(resizable);
        frame.setLocation(getScreenPosition());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("MenschAergereDichNicht\\Assets\\Board\\Icon.png").getImage());
        frame.addKeyListener(keyAdapter);
        frame.setVisible(true);
    }

    static String currentMode;

    KeyAdapter keyAdapter = new KeyAdapter() {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                String menu = GamePanel.Menu;
                GamePanel gp = new GamePanel();

                if (menu.equals("Local") || menu.equals("MP")) {
                    currentMode = menu;
                }
                if (menu.equals(currentMode)) {
                    gp.setMenu("Pause");
                } else {
                    if (menu.equals("Pause")) {
                        gp.setMenu(currentMode);
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_F11) {
                frame.setVisible(false);
                frame.dispose();
                frame.setUndecorated(!frame.isUndecorated());
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_F12) {

            }
        }
    };

    GraphicsEnvironment getGraphicsEnviroment() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment();
    }

    GraphicsDevice getDefaultDevice() {
        return getGraphicsEnviroment().getDefaultScreenDevice();

    }

    GraphicsDevice currentDevice() {
        return frame.getGraphicsConfiguration().getDevice();
    }

    int currentDeviceNumber() {
        GraphicsDevice currentDevice = frame.getGraphicsConfiguration().getDevice();
        for (int i = 0; i < getDevices().length; i++) {
            if (getDevices()[i] == currentDevice) {
                return i;
            }
        }
        return -1;
    }

    GraphicsDevice[] getDevices() {
        return getGraphicsEnviroment().getScreenDevices();
    }

    Dimension getScreenSize() {
        return new Dimension(getDefaultDevice().getDefaultConfiguration().getBounds().getSize());
    }

    Point getScreenPosition() {
        return new Point(getDefaultDevice().getDefaultConfiguration().getBounds().getLocation());
    }
}
