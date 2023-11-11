package MenschAergereDichNicht;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class EventHandler implements KeyListener {
        
    static JFrame frame = Frame.frame;
    Game game = new Game();

    public static void main(String[] args) {
        Frame.FrameUI();
    }                       

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {    	
        
        if(e.getKeyCode() == KeyEvent.VK_F1) {
            refreshJFrame();
            System.out.println(frame.getWidth() + " " + frame.getHeight());
        }

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Menus menus = new Menus();

            if(menus.Menu == "Open") {
                menus.addEscMenu(menus.Menu);
                menus.Menu = "Close";
            }else
            if(menus.Menu == "Close") {
                menus.addEscMenu(menus.Menu);
                menus.Menu = "Open";
            }else
            if(menus.Menu == null) {
                menus.addEscMenu("Open");
                menus.Menu = "Close";
            }

            }
            if(e.getKeyCode() == KeyEvent.VK_F5) {
                frame.repaint();
            }

        if(e.getKeyCode() == KeyEvent.VK_F11 ) {
            refreshJFrame();

            if(frame.isUndecorated()) {
                frame.dispose();
                frame.setUndecorated(false);
                frame.setVisible(true);
                Menus menus = new Menus();
                menus.Buttons();
            }else {
                if(!frame.isUndecorated()) {
                    frame.dispose();
                    frame.setUndecorated(true);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame.setVisible(true);
                }
            }
        }
    }

                       
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    void refreshJFrame() {
        frame = Frame.frame;
    }

    void addResizeListener() {
        
        frame.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {

                if(Menus.GameMode != null) {
                    game.refreshAll();
                }else {
                    frame.getContentPane().removeAll();
                    Menus menus = new Menus();
                    menus.Buttons();
                }

                frame.setSize(e.getComponent().getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
            
        });
    }

    void addResolutionListener() {
        frame.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener(){

            @Override
            public void ancestorMoved(HierarchyEvent e) {

            }

            @Override
            public void ancestorResized(HierarchyEvent e) {
            }

        });
    }
    
}