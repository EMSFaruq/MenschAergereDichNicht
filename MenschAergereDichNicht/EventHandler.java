package MenschAergereDichNicht;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class EventHandler implements KeyListener {

    static JFrame frame = Frame.frame;
    Game game = new Game();
    Menus menus = new Menus();

    public static void main(String[] args) {
        Frame.FrameUI();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_F1) {
            refreshJFrame();
            System.out.println("Frame Position: " + frame.getX() + " " + frame.getY());
            System.out.println("Resolution: " + frame.getWidth() + " " + frame.getHeight());
            System.out.println("Fullscreen: " + frame.isUndecorated());
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Menus menus = new Menus();

            if (menus.Menu == "Open") {
                menus.addEscMenu(menus.Menu);
                menus.Menu = "Close";
            } else if (menus.Menu == "Close") {
                menus.addEscMenu(menus.Menu);
                menus.Menu = "Open";
            } else if (menus.Menu == null) {
                menus.addEscMenu("Open");
                menus.Menu = "Close";
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_F5) {
            frame.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_F2) {
            frame.dispose();
            Frame.FrameUI();
        }

        if (e.getKeyCode() == KeyEvent.VK_F11) {
            Thread thread1 = new Thread(() -> {
                if (frame.isUndecorated()) {
                    frame.dispose();
                    frame.setUndecorated(false);
                    frame.setVisible(true);
                    Menus menus = new Menus();
                    menus.Buttons();
                } else {
                    if (!frame.isUndecorated()) {
                        frame.dispose();
                        frame.setUndecorated(true);
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        frame.setVisible(true);
                    }
                }
            });

            try {
                thread1.join();
                // Thread.sleep(1000);
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_F4) {

            frame.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseMoved(MouseEvent e) {
                    System.out.println(e.getX() + " " + e.getY());
                }
            });
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void refreshJFrame() {
        frame = Frame.frame;
    }

    void addKeyListener() {
        frame.addKeyListener(this);
    }

    void addResizeListener() {

        frame.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                Thread ResizeThread = new Thread(() -> {
                    if (Menus.GameMode != null) {
                        game.refreshAll();
                    } else {
                        frame.getContentPane().removeAll();
                        menus.Buttons();
                    }
                    frame.setSize(e.getComponent().getSize());
                });

                try {
                    ResizeThread.join();
                    // Thread.sleep(1000);
                } catch (Exception e1) {
                    // TODO: handle exception
                }

            }
        });
    }

}
