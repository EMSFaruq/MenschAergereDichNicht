package MenschAergereDichNicht;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    static String Menu;
    JFrame frame = Frame.frame;
    Frame f = new Frame();
    Color BackgroundColor = new Color(229, 221, 144);
    Font DefaultFont = new Font("Poor Richard", Font.BOLD, 12);

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
        setBackground(BackgroundColor);

        JLabel Preview = previewLabel();
        Preview.setLocation(getWidth() / 2 - Preview.getWidth() / 2, getHeight() / 3 - Preview.getHeight() / 2);
        add(Preview);
        for (int i = 0; i < button.length; i++) {

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
            float fontsize = 50;
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
            button[i] = create(width, height, text, Color.black, BackgroundColor,
                    DefaultFont.deriveFont(fontsize), stroke, roundness,
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
                width = wRatio(icon.getIconWidth());
                height = hRatio(icon.getIconHeight());
                icon = new ImageIcon(icon.getImage().getScaledInstance(wRatio(icon.getIconWidth()),
                        hRatio(icon.getIconHeight()), Image.SCALE_SMOOTH));

            } else {
                width = wRatio(width);
                height = hRatio(height);
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
                g2d.setColor(BackgroundColor);
                g2d.fillRoundRect(strokesize / 2, strokesize / 2, width - strokesize, height - strokesize, round,
                        round);
            }
        } else {
            if (icon == null) {
                g2d.setColor(BackgroundColor);
                g2d.fillRect(strokesize / 2, strokesize / 2, width - strokesize, height - strokesize);
            }
        }

        // Text
        g2d.setColor(Color.black);
        g2d.setFont(font);

        // Ai Generated
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
        label.setOpaque(false);
        return label;
    }

    // Only Method Ai Generated cuz im bad. jk just had no clue
    public boolean isMouseListenerAdded(JLabel label) {
        for (MouseListener listener : label.getMouseListeners()) {
            if (listener instanceof MouseAdapter) {
                return true; // MouseAdapter (oder MouseListener) bereits hinzugefügt
            }
        }
        return false; // Kein MouseAdapter (oder MouseListener) gefunden
    }

    public boolean panelGotMouseListener() {
        for (MouseListener listener : getMouseListeners()) {
            if (listener instanceof MouseAdapter) {
                return true; // MouseAdapter (oder MouseListener) bereits hinzugefügt
            }
        }
        return false; // Kein MouseAdapter (oder MouseListener) gefunden
    }

    void localMenu() {
        removeAll();
        setBackground(new Color(43, 43, 43));

        // Players
        setPlayers();
        setLocal();

        // Board
        JLabel board = create(0, 0, "", null, null, null, 0, 0,
                new ImageIcon("MenschAergereDichNicht\\Assets\\Board\\Board.png"));
        board.setLocation(center(board.getWidth(), getWidth()), 0);
        add(board);
    }

    JLabel[] player = new JLabel[16];

    void setPlayers() {
        for (int i = 0; i < player.length; i++) {
            if (player[i] == null) {
                ImageIcon playerIcon = new ImageIcon(
                        "MenschAergereDichNicht/Assets/Characters/Player" + ((i / 4) + 1) + ".png");
                player[i] = create(0, 0, "", null, null, null, 0, 0, playerIcon);
                int currentI = i;
                player[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        int team = 0;
                        if (currentI < 4) {
                            team = 0;
                        } else if (currentI < 8) {
                            team = 1;

                        } else if (currentI < 12) {
                            team = 2;
                        } else if (currentI < 16) {
                            team = 3;
                        }
                        handleMovement(player[currentI], 1, team);
                    }
                });
            }
            add(player[i]);
        }
    }

    static boolean sorted = false;

    void setLocal() {
        if (!sorted) {
            for (int i = 0; i < player.length / 4; i++) {
                SortTeam(i);
            }
            sorted = true;
        }
    }

    public void SortTeam(int team) {

        for (int i = 0; i < player.length / 4; i++) {
            if (team == 0) {
                player[i].setLocation(getField(i, team));

            } else if (team == 1) {
                player[i + 4].setLocation(getField(i, team));

            } else if (team == 2) {
                player[i + 8].setLocation(getField(i, team));

            } else if (team == 3) {
                player[i + 12].setLocation(getField(i, team));
            }
        }

    }

    void handleMovement(JLabel player, int steps, int team) {
        int currentField = 0;
        for (int i = 0; i < field.length; i++) {
            getStartPoint(team);
            if (player.getLocation() == new Point(field[i][0], field[i][1])) {
                currentField = i;
                break;
            } else {
                player.setLocation(0, 0);
                currentField = 0;
            }
        }

        int newField = currentField + steps;
        Point newLoc = new Point(field[newField][0], field[newField][1]);
        player.setLocation(newLoc);

    }

    static void getStartPoint(int team) {

        if (team == 0) {
            field[0] = new int[] { 505, 940 };
            field[1] = new int[] { 505, 850 };
            field[2] = new int[] { 593, 850 };
            field[3] = new int[] { 593, 940 };
        } else if (team == 1) {
            field[0] = new int[] { 505, 145 };
            field[1] = new int[] { 505, 55 };
            field[2] = new int[] { 593, 55 };
            field[3] = new int[] { 593, 145 };
        } else if (team == 2) {
            field[0] = new int[] { 1300, 145 };
            field[1] = new int[] { 1300, 55 };
            field[2] = new int[] { 1388, 55 };
            field[3] = new int[] { 1388, 145 };
        } else if (team == 3) {
            field[0] = new int[] { 1300, 940 };
            field[1] = new int[] { 1300, 850 };
            field[2] = new int[] { 1388, 940 };
            field[3] = new int[] { 1388, 850 };
        }

    }

    static int[][] field = new int[48][];

    Point getField(int fieldNr, int team) {
        field = new int[][] {
                { 0, 0 },
                { 0, 0 },
                { 0, 0 },
                { 0, 0 },
                { 857, 938 },
                { 857, 850 },
                { 857, 760 },
                { 857, 675 },
                { 857, 585 },
                { 768, 585 },
                { 682, 585 },
                { 592, 585 },
                { 505, 585 },
                { 505, 498 },
                { 505, 410 },
                { 592, 410 },
                { 682, 410 },
                { 769, 410 },
                { 857, 410 },
                { 857, 320 },
                { 857, 235 },
                { 857, 145 },
                { 857, 58 },
                { 945, 58 },
                { 1033, 58 },
                { 1033, 145 },
                { 1033, 234 },
                { 1033, 320 },
                { 1033, 409 },
                { 1122, 409 },
                { 1210, 409 },
                { 1298, 409 },
                { 1386, 409 },
                { 1386, 497 },
                { 1386, 584 },
                { 1299, 584 },
                { 1209, 584 },
                { 1122, 584 },
                { 1034, 584 },
                { 1034, 673 },
                { 1034, 761 },
                { 1034, 850 },
                { 1034, 938 },
                { 945, 938 },
                { 945, 850 },
                { 945, 760 },
                { 945, 673 },
                { 945, 585 } };
        getStartPoint(team);

        for (

                int i = 0; i < field.length; i++) {
            field[i][0] = wRatio(field[i][0]);
            field[i][1] = hRatio(field[i][1]);
        }
        return new Point(field[fieldNr][0], field[fieldNr][1]);
    }

    static Socket client;
    boolean searched;

    JLabel mpBack;

    void mpMenu() {

        removeAll();
        setBackground(BackgroundColor);

        // Backbutton
        if (mpBack == null) {
            mpBack = backButton();
        }
        add(mpBack);

        // Multiplayer Title
        JLabel MPTitle = create(750, 250, "Multiplayer", Color.black, null, DefaultFont.deriveFont(125f), 0,
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
            JLabel serverLabel = create(1250, 250, "No Servers Found!", Color.black, BackgroundColor,
                    DefaultFont.deriveFont(125f), 0, 0,
                    null);
            serverLabel.setLocation(
                    center(serverLabel.getWidth(), getWidth()),
                    center(serverLabel.getHeight(), getHeight()));
            add(serverLabel);

            JLabel refresh = create(125, 125, "", Color.black, BackgroundColor, null, 5, 45,
                    new ImageIcon("MenschAergereDichNicht/Assets/Menu/Refresh.png"));
            refresh.setLocation(getWidth() / 6 * 5, center(refresh.getHeight(), getHeight()));

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

    JLabel back;
    JLabel SettingsTitle;
    JLabel fullscreen;
    JLabel monitor;

    int nextScreen() {
        int next = 0;
        if (f.currentDeviceNumber() < f.getDevices().length - 1) {
            next = f.currentDeviceNumber() + 1;
        } else {
            next = 0;
        }
        return next;
    }

    void settingsMenu() {
        removeAll();
        setBackground(BackgroundColor);

        // Back Button
        if (back == null) {
            back = backButton();
        }
        add(back);

        // Settings
        if (SettingsTitle == null) {
            SettingsTitle = create(750, 250, "Settings", Color.black, null, DefaultFont.deriveFont(125f), 0, 0,
                    null);
        }
        SettingsTitle.setLocation(center(SettingsTitle.getWidth(), getWidth()), 0);
        add(SettingsTitle);

        // Fullscreen Button
        if (fullscreen == null) {
            String fullText;

            if (frame.isUndecorated()) {
                fullText = "Window";
            } else {
                fullText = "Fullscreen";
            }
            fullscreen = create(250, 125, fullText, Color.BLACK, BackgroundColor, DefaultFont.deriveFont(50f), 5, 45,
                    null);
            fullscreen.setLocation(getWidth() / 5 * 2 - fullscreen.getWidth(),
                    center(fullscreen.getHeight(), getHeight()));
            fullscreen.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        frame.setVisible(false);
                        frame.dispose();
                        frame.setUndecorated(!frame.isUndecorated());
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        frame.setVisible(true);
                    }
                }
            });
        }
        add(fullscreen);

        // Monitor Button
        if (monitor == null) {
            monitor = create(250, 125, "Device: " + nextScreen(), Color.BLACK, BackgroundColor,
                    DefaultFont.deriveFont(50f),
                    5,
                    45, null);
        }
        monitor.setLocation(getWidth() / 5 * 3,
                center(monitor.getHeight(), getHeight()));
        monitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    GraphicsDevice[] devices = f.getDevices();
                    GraphicsConfiguration gc = f.getDefaultDevice().getDefaultConfiguration();
                    frame.setVisible(false);
                    frame.dispose();
                    Rectangle deviceInfo = gc.getBounds();
                    frame.setSize(deviceInfo.getSize());
                    setSize(deviceInfo.getSize());
                    frame.setLocation(deviceInfo.getLocation());
                    frame.setVisible(true);
                }
            }
        });
        add(monitor);

    }

    JLabel Preview;
    JLabel pauseBack;

    void pauseMenu() {
        removeAll();
        setBackground(BackgroundColor);

        if (Preview == null) {
            Preview = previewLabel();
        }
        Preview.setLocation(getWidth() / 2 - Preview.getWidth() / 2, getHeight() / 3 - Preview.getHeight() / 2);
        add(Preview);

        if (pauseBack == null) {
            pauseBack = backButton();
        }
        add(back);
    }

    int center(int SizeOfObject, int centerOf) {
        return centerOf / 2 - SizeOfObject / 2;
    }

    JLabel backButton() {
        int ButtonWidth = 250;
        int ButtonHeight = 125;

        JLabel back = create(ButtonWidth, ButtonHeight, "Back", Color.black, BackgroundColor,
                DefaultFont.deriveFont(75f), 5, 45, null);
        back.setLocation(getWidth() / 2 - back.getWidth() / 2, getHeight() / 5 * 4 - back.getHeight() / 2);
        if (!isMouseListenerAdded(back)) {
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

    JLabel previewLabel() {
        return create(50, 50, "", Color.black, BackgroundColor, null, 0, 0,
                new ImageIcon("MenschAergereDichNicht/Assets/Board/Preview.png"));
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

    int wRatio(int originalSize) {
        return originalSize * getWidth() / 1920;
    }

    int hRatio(int originalSize) {
        return originalSize * getHeight() / 1080;
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (Menu.equalsIgnoreCase("MAIN")) {
            mainMenu();
            repaint();
        } else if (Menu.equalsIgnoreCase("LOCAL")) {
            localMenu();
            repaint();
        } else if (Menu.equalsIgnoreCase("MP")) {
            mpMenu();
            repaint();
        } else if (Menu.equalsIgnoreCase("SETTINGS")) {
            settingsMenu();
            repaint();
        } else if (Menu.equalsIgnoreCase("PAUSE")) {
            pauseMenu();
            repaint();
        }
    }
}