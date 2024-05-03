package MenschAergereDichNicht;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menus {

    JPanel panel;
    static int PosX;
    static int PosY;
    static String CurrentButton;
    static JLabel PreviewPicture = new JLabel();
    static ImageIcon EscImg;
    static ImageIcon img = new ImageIcon();
    static Image newImage;
    static String GameMode;
    Game game = new Game();
    Server server = new Server();
    String Menu;

    public static void main(String[] args) {
        new Frame().Init();
    }

    JPanel getPanel() {
        return panel;
    }

    void startMenu() {
        img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\Mainmenu.png");
        game.create(5, img, "0", "0");
    }

    void test() {
        panel = getPanel();
        JLabel test = new JLabel();
        test.setLocation(50, 100);
        test.setSize(500, 250);
        test.setIcon(new ImageIcon(new ImageIcon("MenschAergereDichNicht\\Sprites\\Board\\Board.png").getImage()
                .getScaledInstance(500, 250, Image.SCALE_SMOOTH)));
        panel.add(test);

        try {
            Thread.sleep(5000);
            panel.setSize(200, 300);
            panel.setBackground(Color.white);
            panel.repaint();
            System.out.println("Resized");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Buttons() {
        int MiddleX = panel.getWidth() / 2;
        int MiddleY = panel.getHeight() / 2 + panel.getHeight() / 4;
        int Abstand = 25;

        JLabel[] Button = new JLabel[4 + 1];
        for (int i = 1; i <= 4; i++) {

            if (i == 1) {
                img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\LocalButton.png");
            } else if (i == 2) {
                img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\MultiplayerButton.png");
            } else if (i == 3) {
                img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\SettingsButton.png");
            } else if (i == 4) {
                img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\ExitButton.png");
            }

            img = new ImageIcon(img.getImage().getScaledInstance(
                    img.getIconWidth(),
                    img.getIconHeight(),
                    Image.SCALE_DEFAULT));

            if (i == 1 || i == 3) {
                PosX = MiddleX - img.getIconWidth() - Abstand;
            }
            if (i == 2 || i == 1) {
                PosY = MiddleY - img.getIconHeight() - Abstand;
            }
            if (i == 3 || i == 4) {
                PosY = MiddleY + Abstand;
            }
            if (i == 4 || i == 2) {
                PosX = MiddleX + Abstand;
            }

            Button[i] = new JLabel(img);
            Button[i].setSize(img.getIconWidth(), img.getIconHeight());
            Button[i].setLocation(PosX, PosY);
            panel.add(Button[i]);

            Button[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (Game.getObject(Button, (JLabel) e.getSource())) {
                        case 1:
                            panel.removeAll();
                            panel.repaint();
                            game.createPlayers();
                            game.createCube();
                            game.createGame();
                            GameMode = "Local";
                            break;
                        case 2:
                            game.createGame();
                            Server server = new Server();
                            server.ServerMenu();
                            break;

                        case 3:

                            break;
                        case 4:
                            Frame.frame.dispose();
                            break;

                        default:
                            break;
                    }
                }

            });
        }
        game.createText(1, "Local", "0", "0");
        previewPicture();
        startMenu();

    }

    void addEscMenu(String Menu) {

        if (GameMode != null) {
            if (GameMode.equalsIgnoreCase("Local")) {
                EscImg = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\EscMenu(Local).png");
            }
            if (GameMode.equalsIgnoreCase("MP")) {
                EscImg = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\EscMenu(MP).png");
            }
            if (Menu == "Open") {
                panel.removeAll();
                panel.repaint();
                game.create(2, EscImg, "Left", "Top");

            }

            if (Menu == "Close") {
                panel.removeAll();
                panel.repaint();
                game.refreshAll();
                game.wait(50);
            }
        }

    }

    void previewPicture() {
        ImageIcon PreviewPicture = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\Preview.png");
        Game game = new Game();
        game.create(4, PreviewPicture, "Middle", "Preview");

    }
}
