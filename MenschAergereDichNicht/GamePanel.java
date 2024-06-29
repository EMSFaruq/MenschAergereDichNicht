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
        System.out.println("Panel set to: " + getWidth() + " " + getHeight());
    }

    JLabel[] button = new JLabel[4];

    void mainMenu() {
        // Panel Design
        removeAll();
        setBackground(new Color(229, 221, 144));

        for (int i = 0; i < button.length; i++) {
            JLabel Preview = new JLabel();
            ImageIcon previewIcon = new ImageIcon("MenschAergereDichNicht\\Assets\\Board\\Board.png");
            previewIcon = new ImageIcon((previewIcon).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
            Preview.setIcon(previewIcon);
            Preview.setSize(previewIcon.getIconWidth(), previewIcon.getIconHeight());
            Preview.setLocation(getWidth() / 2 - Preview.getWidth() / 2, getHeight() / 3 - Preview.getHeight() / 2);
            add(Preview);

            if (button[i] == null) {
                button[i] = new JLabel();
            }
            add(button[i]);

            int buttonHeight = getHeight() / 3 * 2;
            int differenz = 15;
            String text = "";
            int width = 250;
            int height = 125;
            int fontsize = 50;
            int stroke = 5;

            if (i == 0) {
                text = "Local";
                fontsize = 75;
                button[i] = create(width, height, text, "Poor Richard", Font.BOLD, fontsize, stroke);
                button[i].setLocation(getWidth() / 2 - button[i].getWidth() - differenz,
                        buttonHeight - button[i].getHeight() / 2 - differenz);
            }
            if (i == 1) {
                text = "MP";
                fontsize = 75;
                button[i] = create(width, height, text, "Poor Richard", Font.BOLD, fontsize, stroke);
                button[i].setLocation(getWidth() / 2 + differenz,
                        buttonHeight - button[i].getHeight() / 2 - differenz);
            }
            if (i == 2) {
                text = "Settings";
                fontsize = 60;
                button[i] = create(width, height, text, "Poor Richard", Font.BOLD, fontsize, stroke);
                button[i].setLocation(getWidth() / 2 - button[i].getWidth() - differenz,
                        buttonHeight + button[i].getHeight() / 2 + differenz);
            }
            if (i == 3) {
                text = "Exit";
                fontsize = 75;
                button[i] = create(width, height, text, "Poor Richard", Font.BOLD, fontsize, stroke);
                button[i].setLocation(getWidth() / 2 + differenz,
                        buttonHeight + button[i].getHeight() / 2 + differenz);
            }

            if (!isMouseListenerAdded(button[i])) {
                int currentI = i;
                button[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            System.out.println("Click");
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
            add(button[i]);
        }

    }

    JLabel create(int width, int height, String text, String font, int style, int fontsize, int strokesize) {
        BufferedImage image = new BufferedImage(250, 125, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        int stroke = 3;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(new Color(229, 221, 144));
        g2d.fillRect(stroke / 2, stroke / 2, 250 - stroke, 125 - stroke);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(stroke / 2, stroke / 2, 250 - stroke, 125 - stroke);
        g2d.setFont(new Font(font, style, fontsize));
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getAscent();
        int textX = (250 - textWidth) / 2;
        int textY = (125 + textHeight) / 2 - 10;
        g2d.drawString(text, textX, textY);
        g2d.dispose();

        // Button
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel();
        label.setSize(250, 125);
        label.setIcon(icon);
        label.setForeground(Color.BLACK);
        label.setBackground(new Color(229, 221, 144));
        label.setOpaque(true);
        return label;
    }

    public static boolean isMouseListenerAdded(JLabel label) {
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
        JLabel back = create(250, 125, "Back", "Poor Richard", Font.BOLD, 75, 5);
        if (!isMouseListenerAdded(back)) {
            back.setLocation(getWidth() / 2 - back.getWidth() / 2, getHeight() / 5 * 4 - back.getHeight() / 2);
            back.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    setMenu("Main");
                }
            });
        }
        add(back);
    }

    void mpMenu() {
        removeAll();
        setBackground(new Color(229, 221, 144));
        JLabel back = create(250, 125, "Back", "Poor Richard", Font.BOLD, 75, 5);
        if (!isMouseListenerAdded(back)) {
            back.setLocation(getWidth() / 2 - back.getWidth() / 2, getHeight() / 5 * 4 - back.getHeight() / 2);
            back.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    setMenu("Main");
                }
            });
        }
        add(back);
    }

    void settingsMenu() {
        removeAll();
        setBackground(new Color(229, 221, 144));
        JLabel back = create(250, 125, "Back", "Poor Richard", Font.BOLD, 75, 5);
        if (!isMouseListenerAdded(back)) {
            back.setLocation(getWidth() / 2 - back.getWidth() / 2, getHeight() / 5 * 4 - back.getHeight() / 2);
            back.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    setMenu("Main");
                }
            });
        }
        add(back);
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
        } else if ("Local".equals(Menu)) {
            localMenu();
        } else if ("MP".equals(Menu)) {
            mpMenu();
        } else if ("Settings".equals(Menu)) {
            settingsMenu();
        }

        repaint();
    }
}
