package MenschAergereDichNicht;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    static String Menu;
    JFrame frame = Frame.frame;

    public static void main(String[] args) {
        Frame.main(args);
    }

    void setMenu(String Menu) {
        GamePanel.Menu = Menu;
    }

    void setPanel() {
        setSize(frame.getWidth(), frame.getHeight());
        setLocation(0, 0);
        setLayout(null);
    }

    JLabel[] button = new JLabel[4];

    void mainMenu() {
        // Panel Design
        removeAll();
        setBackground(new Color(229, 221, 144));

        for (int i = 0; i < button.length; i++) {
            JLabel Preview = create(0, 0, "", Color.black, new Color(229, 221, 144), null, 0, 0,
                    new ImageIcon("MenschAergereDichNicht/Assets/Board/Preview.png"));
            Preview.setLocation(getWidth() / 2 - Preview.getWidth() / 2, getHeight() / 3 - Preview.getHeight() / 2);
            add(Preview);

            if (button[i] == null) {
                button[i] = new JLabel();
            }

            int buttonHeight = getHeight() / 3 * 2;
            int differenz = 15;
            String text = "";
            int locX = 0;
            int locY = 0;
            int width = 250;
            int height = 125;
            int roundness = 45;
            int fontsize = 50;
            int stroke = 5;

            if (i == 0) {
                text = "Local";
                fontsize = 75;
                locX = getWidth() / 2 - button[i].getWidth() - differenz;
                locY = buttonHeight - button[i].getHeight() / 2 - differenz;
            }
            if (i == 1) {
                text = "MP";
                fontsize = 75;
                locX = getWidth() / 2 + differenz;
                locY = buttonHeight - button[i].getHeight() / 2 - differenz;
            }
            if (i == 2) {
                text = "Settings";
                fontsize = 60;
                locX = getWidth() / 2 - button[i].getWidth() - differenz;
                locY = buttonHeight + button[i].getHeight() / 2 + differenz;
            }
            if (i == 3) {
                text = "Exit";
                fontsize = 75;
                locX = getWidth() / 2 + differenz;
                locY = buttonHeight + button[i].getHeight() / 2 + differenz;
            }
            button[i] = create(width, height, text, Color.black, new Color(229, 221, 144),
                    new Font("Poor Richard", Font.BOLD, fontsize), stroke, roundness,
                    null);
            button[i].setLocation(locX, locY);
            add(button[i]);

            if (!isMouseListenerAdded(button[i])) {
                int currentI = i;
                button[i].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (button[currentI] == button[0]) {
                                setMenu("Local");
                            }
                            if (button[currentI] == button[1]) {
                                setMenu("MP");
                            }
                            if (button[currentI] == button[2]) {
                                setMenu("Settings");
                            }
                            if (button[currentI] == button[3]) {
                                frame.dispose();
                            }
                        }
                    }
                });
            }
        }
    }

    JLabel create(int width, int height, String text, Color foreground, Color background, Font font, int strokesize,
            int round, ImageIcon icon) {

        if (icon != null) {

            if (width == 0 || height == 0) {
                width = icon.getIconWidth();
                height = icon.getIconHeight();
            } else {
                icon = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            }
            round = 0;

        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        if (icon != null) {
            g2d.drawImage(icon.getImage(), 0, 0, null);
        }
        if (strokesize > 0) {
            // Box and BorderLine
            g2d.setStroke(new BasicStroke(strokesize));
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(strokesize / 2, strokesize / 2, width - strokesize, height - strokesize, round, round);
            if (round > 0) {
                g2d.setColor(new Color(229, 221, 144));
                g2d.fillRoundRect(strokesize / 2, strokesize / 2, width - strokesize, height - strokesize, round,
                        round);
            }
        } else {
            if (icon == null) {
                g2d.setColor(new Color(229, 221, 144));
                g2d.fillRect(strokesize / 2, strokesize / 2, width - strokesize, height - strokesize);
            }
        }

        // Text
        g2d.setColor(Color.black);
        g2d.setFont(font);
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getAscent();
        int textX = (width - textWidth) / 2;
        int textY = (height + textHeight) / 2 - 10;
        g2d.drawString(text, textX, textY);
        g2d.dispose();
        icon = new ImageIcon(image);

        // Button
        JLabel label = new JLabel();
        label.setSize(width, height);
        label.setIcon(icon);
        label.setForeground(foreground);
        label.setBackground(background);
        label.setOpaque(true);
        return label;
    }

    // Only thing Ai Generated cuz im bad. jk just had no clue
    public boolean isMouseListenerAdded(JLabel label) {
        for (MouseListener listener : label.getMouseListeners()) {
            if (listener instanceof MouseAdapter) {
                return true; // MouseAdapter (oder MouseListener) bereits hinzugefügt
            }
        }
        return false; // Kein MouseAdapter (oder MouseListener) gefunden
    }

    void localMenu() {
        removeAll();
        setBackground(new Color(229, 221, 144));
        JLabel back = backButton();
        add(back);
    }

    static Socket client;
    boolean searched;

    void mpMenu() {

        removeAll();
        setBackground(new Color(229, 221, 144));

        // Backbutton
        JLabel back = backButton();
        add(back);

        // Multiplayer Title
        JLabel MPTitle = create(750, 250, "Multiplayer", Color.black, null, new Font("Poor Richard", Font.BOLD, 125), 0,
                0,
                null);
        MPTitle.setLocation(center(MPTitle.getWidth(), getWidth()), 0);
        add(MPTitle);

        // Try connect to Servers
        int[] ports = new int[] {
                1, 2, 3, 4, 5
        };
        if (!searched) {
            for (int i = 1; i <= ports.length; i++) {
                tryConnect(i);
            }
        }
        searched = true;
        if (client == null) {
            JLabel serverLabel = create(1250, 250, "No Servers Found!", Color.black, new Color(229, 221, 144),
                    new Font("Poor Richard", Font.BOLD, 125), 0, 0,
                    null);
            serverLabel.setLocation(
                    center(serverLabel.getWidth(), getWidth()),
                    center(serverLabel.getHeight(), getHeight()));
            add(serverLabel);

            JLabel refresh = create(125, 125, "", Color.black, new Color(229, 221, 144), null, 5, 45,
                    new ImageIcon("MenschAergereDichNicht/Assets/Menu/Refresh.png"));
            refresh.setLocation(getWidth() / 6 * 5, center(refresh.getHeight(), getHeight()));
            // System.out.println(refresh.getSize());
            // System.out.println(refresh.getLocation(getLocation()));

            refresh.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        for (int i = 0; i < ports.length; i++) {
                            refresh.setIcon(
                                    rotateIcon(new ImageIcon("MenschAergereDichNicht/Assets/Menu/Refresh.png"), 90));
                            tryConnect(ports[i]);
                        }
                    }
                }
            });
            add(refresh);
        }
    }

    ImageIcon rotateIcon(ImageIcon icon, double degree) {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(icon.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.rotate(degree);
        return new ImageIcon(image);
    }

    void tryConnect(int port) {
        try {
            System.out.println("Try Connecting on Port: " + port + "!");
            client = new Socket("localhost", port);
            System.out.println("Connected to Server!");
        } catch (Exception e) {
            try {
                System.out.println("Server is offline!");
            } catch (Exception e1) {
                System.out.println("Couldn't wait!");
            }
        }
    }

    void settingsMenu() {
        removeAll();
        setBackground(new Color(229, 221, 144));
        JLabel back = backButton();
        add(back);
    }

    int center(int SizeOfObject, int centerOf) {
        return centerOf / 2 - SizeOfObject / 2;
    }

    JLabel backButton() {
        int ButtonWidth = 250;
        int ButtonHeight = 125;

        JLabel back = create(ButtonWidth, ButtonHeight, "Back", Color.black, new Color(229, 221, 144),
                new Font("Poor Richard", Font.BOLD, 75), 5, 45, null);
        if (!isMouseListenerAdded(back)) {
            back.setLocation(getWidth() / 2 - back.getWidth() / 2, getHeight() / 5 * 4 - back.getHeight() / 2);
            back.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (Menu.equals("MP") && client != null) {
                            client = null;
                        }
                        searched = false;
                        setMenu("Main");
                    }
                }
            });
        }
        return back;
    }

    int getObjectInIndex(Object compare, Object[] objects) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == compare) {
                return i;
            }
        }
        try {
            System.out.println("Objekt konnte nicht zuegeordnet werden!");
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        if ("Main".equals(Menu)) {
            mainMenu();
            repaint();
        } else if ("Local".equals(Menu)) {
            localMenu();
            repaint();
        } else if ("MP".equals(Menu)) {
            mpMenu();
            repaint();
        } else if ("Settings".equals(Menu)) {
            settingsMenu();
            repaint();
        }
    }
}
