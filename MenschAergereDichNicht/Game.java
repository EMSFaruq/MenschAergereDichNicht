package MenschAergereDichNicht;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game {

    static Players players;
    static Dice dice;
    static Board bg = new Board();
    JFrame frame = Frame.frame;
    static JLabel[] IconLabel = new JLabel[99];
    static JLabel[] TextLabel = new JLabel[99];
    boolean LabelInfos = false;

    public static void main(String[] args) {
        new Frame();
    }

    public void createGame() {
        bg.createBackground();
    }

    public void createPlayers() {
        TestPlayer testPlayer = new TestPlayer();
        testPlayer.Player();

        players = new Players();
        players.Player();
        // players.playerNames();

        for (int i = 1; i <= (players.Playerlabel.length - 1) / 4; i++) {
            System.out.println("Team: " + i);
            players.SortTeam(i);
        }
    }

    static int getObject(JLabel[] Button, JLabel Player) {
        for (int i = 1; i < Button.length; i++) {
            if (Button[i] == Player) {
                return i;
            }
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Object nicht gefunden!");
        return -1;
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
        }
    }

    public void refreshPlayers() {
        try {
            players.refresh();
        } catch (Exception e) {
            createPlayers();
        }
    }

    public void refreshCube() {
        try {
            dice.refresh();
        } catch (Exception e) {
            createCube();
        }
    }

    public void refreshAll() {
        refreshPlayers();
        refreshCube();
        refreshBackground();
    }

    public boolean wait(int Time) {
        try {
            Thread.sleep(Time * 1000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static JLabel createRoundedBorderLabel(String text, int cornerRadius, int padding) {
        return new JLabel(text) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // PADDDIIINNNNG MORRGEN MACHENNNNNN
                // LERNE RUNDE BORDERRRRRRRSSSSSSSSS

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(Frame.ratio(40),
                        Frame.ratio(40),
                        Frame.ratio(35), Frame.ratio(40), cornerRadius, cornerRadius);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.draw(roundedRectangle);
                g2d.dispose();
            }
        };
    }

    void createText(int ID, String Text, String PosX, String PosY) {
        refreshJFrame();

        // Create first
        Font CustomFont = new Font("Poor Richard", Font.PLAIN, Frame.ratio(100));
        // TextLabel[ID] = createRoundedBorderLabel(Text, 16, 50);
        TextLabel[ID] = new JLabel();
        TextLabel[ID].setFont(CustomFont);
        TextLabel[ID].setHorizontalAlignment(JLabel.CENTER);
        TextLabel[ID].setVerticalAlignment(JLabel.CENTER);
        TextLabel[ID].setSize(Frame.ratio(250), Frame.ratio(125));

        if (LabelInfos) {
            System.out.println("ID: " + ID + " TextSize: " + TextLabel[ID].getFont().getSize());
            System.out
                    .println("ID: " + ID + " LabelSize: " + TextLabel[ID].getWidth() + " " + TextLabel[ID].getHeight());
            System.out.println("ID: " + ID + " Location: " + TextLabel[ID].getX() + " " + TextLabel[ID].getY());
        }
        try {
            int LocX = Integer.parseInt(PosX);
            int LocY = Integer.parseInt(PosY);
            TextLabel[ID].setLocation(LocX, LocY);
        } catch (Exception e) {
            StringToPos(TextLabel, ID, PosX, PosY);
        }
        frame.add(TextLabel[ID]);
    }

    void create(int ID, ImageIcon icon, String PosX, String PosY) {
        IconLabel[ID] = new JLabel();

        // Auto Ratio if Undecorated or not :D
        icon = new ImageIcon(icon.getImage().getScaledInstance(
                icon.getIconWidth(),
                Frame.ratio(icon.getIconHeight()),
                Image.SCALE_DEFAULT));

        IconLabel[ID].setSize(icon.getIconWidth(), icon.getIconHeight());
        IconLabel[ID].setIcon(icon);

        try {
            int LocX = Integer.parseInt(PosX);
            int LocY = Integer.parseInt(PosY);
            IconLabel[ID].setLocation(LocX, LocY);
        } catch (Exception e) {
            StringToPos(IconLabel, ID, PosX, PosY);
        }
        for (int i = 0; i < IconLabel.length; i++) {

            if (IconLabel[ID] != null) {
                IconLabel[ID].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        Menus menus = new Menus();

                        if (ID == 1 || ID == 2) {
                            frame.getContentPane().removeAll();
                            frame.repaint();
                            menus.Buttons();
                        }
                        if (ID == 7) {
                            Dice dice = new Dice();
                            Random rand = new Random();
                            int random = rand.nextInt(1, 6 + 1);
                            dice.setDice(random);
                        }
                    }
                });

            }
        }

        frame.add(IconLabel[ID]);
    }

    void refreshJFrame() {
        frame = Frame.frame;
    }

    void StringToPos(JLabel[] label, int ID, String PosX, String PosY) {
        switch (PosX) {
            case "Left":
                label[ID].setLocation(0, (int) label[ID].getY());
                break;

            case "Right":
                label[ID].setLocation(frame.getWidth() - label[ID].getWidth(), (int) label[ID].getY());
                break;

            case "Middle":
                label[ID].setLocation(frame.getWidth() / 2 - label[ID].getWidth() / 2, (int) label[ID].getY());
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
                label[ID].setLocation((int) label[ID].getX(),
                        frame.getHeight() - label[ID].getHeight() - label[ID].getHeight());
                break;

            case "Middle":
                label[ID].setLocation((int) label[ID].getX(), frame.getHeight() / 2);
                break;

            case "Preview":
                label[ID].setLocation(label[ID].getX(), frame.getHeight() / 4);
                break;

            default:
                label[ID].setLocation((int) label[ID].getLocation().getX(), 0);
                break;
        }
    }
}