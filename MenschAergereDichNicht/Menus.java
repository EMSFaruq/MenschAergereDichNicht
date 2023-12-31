package MenschAergereDichNicht;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menus {

    JFrame frame = Frame.frame;
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
        Frame.FrameUI();
    }

    void startMenu() {
        img = new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\Mainmenu.png");
        game.create(5, img, "0", "0");
    }

    void Buttons() {
        int MiddleX = frame.getWidth() / 2;
        int MiddleY = frame.getHeight() / 2 + frame.getHeight() / 4;
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
                    Frame.ratio(img.getIconWidth(), false),
                    Frame.ratio(img.getIconHeight(), false),
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
            frame.add(Button[i]);
            Button[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (Game.getObject(Button, (JLabel) e.getSource())) {
                        case 1:
                            frame.getContentPane().removeAll();
                            frame.repaint();
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
                            frame.dispose();
                            break;

                        default:
                            break;
                    }
                }

            });
        }
        game.createText(1, "Local", "0", "0");
        game.refreshJFrame();
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
                frame.getContentPane().removeAll();
                frame.repaint();
                game.create(2, EscImg, "Left", "Top");

            }

            if (Menu == "Close") {
                frame.getContentPane().removeAll();
                frame.repaint();
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
