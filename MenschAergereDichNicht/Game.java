package MenschAergereDichNicht;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game {
    
    static Players players;
    static Dice dice;
    static Board bg = new Board();
    static JFrame frame = Frame.frame;
    static JLabel[] label = new JLabel[99];
    boolean LabelInfos = false;

    public static void main(String[] args) {
        Frame.FrameUI();

    }

    public void createGame() {
        bg.createBackground();
    }

    public void createPlayers() {
        // TestPlayer testPlayer = new TestPlayer();
        // testPlayer.Player();

        players = new Players();
        players.Player();
        // players.playerNames();

        for (int i = 1; i <= (players.Playerlabel.length - 1) / 4; i++) {
            System.out.println("Team: " + i);
            players.SortTeam(i);
        }
        
    }

    public void createCube() {
        dice = new Dice();
        dice.createDice();
    }

    public void Move(int[][] Felder, JLabel[] Player, int PlayerNum, int FeldNummer) {
        Player[PlayerNum].setLocation(Felder[FeldNummer][0], Felder[FeldNummer][1]);
        
    }

    public void refreshBackground() {
        try {
            bg.refresh();
        } catch (Exception e) {
            bg = new Board();
            bg.createBackground();
            e.printStackTrace();
        }
    }

    public void refreshPlayers() {
        try {
            players.refresh();
        } catch (Exception e) {
            players = new Players();
            players.Player();
            e.printStackTrace();
        }
    }

    public void refreshCube() {
        try {
            dice.refresh();
        } catch (Exception e) {
            dice = new Dice();
            dice.createDice();
            e.printStackTrace();
        }
    }

    public void refreshAll() {
        refreshPlayers();
        refreshCube();
        refreshBackground();
    }

    public boolean wait(int Time) {
        final boolean[] ergebnis = new boolean[1];
    
        Thread waitThread = new Thread(() -> {
            try {
                Thread.sleep(Time);
                ergebnis[0] = true;
            } catch (InterruptedException e) {
                ergebnis[0] = false;
            }
        });
    
        waitThread.start();
    
        try {
            waitThread.join();
        } catch (InterruptedException e) {
            ergebnis[0] = false;
        }
    
        return ergebnis[0];
    }
    void create(int ID, ImageIcon icon, String PosX, String PosY) { 
        refreshJFrame();
        label[ID] = new JLabel();
                icon = new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth() * frame.getWidth() / 1920, icon.getIconHeight() * frame.getHeight() / 1080, Image.SCALE_DEFAULT));
                label[ID].setSize(icon.getIconWidth(), icon.getIconHeight());
                label[ID].setIcon(icon);
            if(LabelInfos) {
                System.out.println("ID: " + ID + " IconSize: " + icon.getIconWidth() + " " + icon.getIconHeight());
                System.out.println("ID: " + ID + " LabelSize: " + label[ID].getWidth() + " " + label[ID].getHeight());
                System.out.println("ID: " + ID + " Location: " + label[ID].getX() + " " + label[ID].getY());
            }

        try {
            int LocX = Integer.parseInt(PosX);
            int LocY = Integer.parseInt(PosY);
            label[ID].setLocation(LocX, LocY);
        } catch (Exception e) {
            switch (PosX) {
            case "Left":
                label[ID].setLocation(0, (int) label[ID].getY());
                break;

            case "Right":
                label[ID].setLocation(frame.getWidth() - icon.getIconHeight(), (int) label[ID].getY());
                break;

            case "Middle":
                label[ID].setLocation(frame.getWidth() / 2 - icon.getIconWidth() / 2, (int) label[ID].getY());
                break;
            
            default:
                label[ID].setLocation(0, (int) label[ID].getY());
                break;
        }

        switch (PosY) {
            case "Top":
                label[ID].setLocation((int) label[ID].getX(), 0);
                break;

            case "Bottom":
                label[ID].setLocation((int) label[ID].getX(), frame.getHeight() - icon.getIconHeight() - icon.getIconHeight());
                break;

            case "Middle":
                label[ID].setLocation((int) label[ID].getX(), frame.getHeight() / 2 - icon.getIconHeight() / 2);
                break;
            
            case "Preview":
                label[ID].setLocation(label[ID].getX(), frame.getHeight() / 5);
                break;

            default:
                label[ID].setLocation((int) label[ID].getLocation().getX(), 0);
                break;
        }
            
        }
        for (int i = 0; i < label.length; i++) {

            if (label[i] != null) {
                label[i].addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                 Menus menus = new Menus();
                 
                    if(ID == 1 || ID == 2) {
                        frame.getContentPane().removeAll();
                        menus.Buttons();
                        frame.repaint();
                    }
                    if(ID == 7) {
                        Dice dice = new Dice();
                        Random rand = new Random();
                        int random = rand.nextInt(1, 6 + 1);
                        dice.setDice(random);
                    }
            }
                });
           
            }
        }

        frame.add(label[ID]);
    }
    void refreshJFrame() {
        frame = Frame.frame;
    }
}