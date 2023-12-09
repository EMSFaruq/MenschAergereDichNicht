package MenschAergereDichNicht;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestPlayer {

    JFrame frame = Frame.frame;
    static JLabel[] Test = new JLabel[2];
    static int[][] Felder = new int[45][1];

    public static void main(String[] args) {
        Frame.FrameUI();
    }

    public void Player() {
        ImageIcon test = new ImageIcon("MenschAergereDichNicht\\Sprites\\Characters\\TestPlayer.png");
        Test[1] = new JLabel();
        Test[1].setIcon(test);
        Test[1].setSize(test.getIconWidth(), test.getIconHeight());
        frame.add(Test[1]);

        MovementHandler(1, 1);

        JLabel TestPos = new JLabel();
        TestPos.setBounds(1290, -50, 215, 120);
        Font font = new Font(Font.SERIF, 0, 20);
        TestPos.setFont(font);
        frame.add(TestPos);

        KeyListener keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int Speed = 1;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Test[1].setLocation(Test[1].getX() + Speed, Test[1].getY());
                    TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY());
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Test[1].setLocation(Test[1].getX() - Speed, Test[1].getY());
                    TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY());
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Test[1].setLocation(Test[1].getX(), Test[1].getY() - Speed);
                    TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY());
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Test[1].setLocation(Test[1].getX(), Test[1].getY() + Speed);
                    TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY());
                }

                if (e.getKeyCode() == KeyEvent.VK_PLUS) {

                    try {
                        MovementHandler(1, getField(1) + 1);
                        TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY() + ", Feld: " + getField(1));
                    } catch (Exception e1) {
                        TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY() + ", Feld: Außerhalb!");
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                    try {
                        MovementHandler(1, getField(1) - 1);
                        TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY() + ", Feld: " + getField(1));
                    } catch (Exception e1) {
                        TestPos.setText("X: " + Test[1].getX() + ", Y: " + Test[1].getY() + ", Feld: Außerhalb!");
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        Test[1].addKeyListener(keyListener);
        Test[1].setFocusable(true); // Wichtig, um den Focus auf das Label zu setzen
        // Test[1].requestFocusInWindow(); // Wichtig, um den Fokus auf das Label zu
        // setzen
    }

    static void MovementHandler(int PlayerNum, int FeldNummer) {
        Felder[0] = new int[] { Frame.ratio(0, false), 0 };
        Felder[1] = new int[] { Frame.ratio(857, false), Frame.ratio(938, false) };
        Felder[2] = new int[] { Frame.ratio(857, false), Frame.ratio(850, false) };
        Felder[3] = new int[] { Frame.ratio(857, false), Frame.ratio(760, false) };
        Felder[4] = new int[] { Frame.ratio(857, false), Frame.ratio(675, false) };
        Felder[5] = new int[] { Frame.ratio(857, false), Frame.ratio(585, false) };
        Felder[6] = new int[] { Frame.ratio(768, false), Frame.ratio(585, false) };
        Felder[7] = new int[] { Frame.ratio(682, false), Frame.ratio(585, false) };
        Felder[8] = new int[] { Frame.ratio(592, false), Frame.ratio(585, false) };
        Felder[9] = new int[] { Frame.ratio(505, false), Frame.ratio(585, false) };
        Felder[10] = new int[] { Frame.ratio(505, false), Frame.ratio(498, false) };
        Felder[11] = new int[] { Frame.ratio(505, false), Frame.ratio(410, false) };
        Felder[12] = new int[] { Frame.ratio(592, false), Frame.ratio(410, false) };
        Felder[13] = new int[] { Frame.ratio(682, false), Frame.ratio(410, false) };
        Felder[14] = new int[] { Frame.ratio(769, false), Frame.ratio(410, false) };
        Felder[15] = new int[] { Frame.ratio(857, false), Frame.ratio(410, false) };
        Felder[16] = new int[] { Frame.ratio(857, false), Frame.ratio(320, false) };
        Felder[17] = new int[] { Frame.ratio(857, false), Frame.ratio(235, false) };
        Felder[18] = new int[] { Frame.ratio(857, false), Frame.ratio(145, false) };
        Felder[19] = new int[] { Frame.ratio(857, false), Frame.ratio(58, false) };
        Felder[20] = new int[] { Frame.ratio(945, false), Frame.ratio(58, false) };
        Felder[21] = new int[] { Frame.ratio(1033, false), Frame.ratio(58, false) };
        Felder[22] = new int[] { Frame.ratio(1033, false), Frame.ratio(145, false) };
        Felder[23] = new int[] { Frame.ratio(1033, false), Frame.ratio(234, false) };
        Felder[24] = new int[] { Frame.ratio(1033, false), Frame.ratio(320, false) };
        Felder[25] = new int[] { Frame.ratio(1033, false), Frame.ratio(409, false) };
        Felder[26] = new int[] { Frame.ratio(1122, false), Frame.ratio(409, false) };
        Felder[27] = new int[] { Frame.ratio(1210, false), Frame.ratio(409, false) };
        Felder[28] = new int[] { Frame.ratio(1298, false), Frame.ratio(409, false) };
        Felder[29] = new int[] { Frame.ratio(1386, false), Frame.ratio(409, false) };
        Felder[30] = new int[] { Frame.ratio(1386, false), Frame.ratio(497, false) };
        Felder[31] = new int[] { Frame.ratio(1386, false), Frame.ratio(584, false) };
        Felder[32] = new int[] { Frame.ratio(1299, false), Frame.ratio(584, false) };
        Felder[33] = new int[] { Frame.ratio(1209, false), Frame.ratio(584, false) };
        Felder[34] = new int[] { Frame.ratio(1122, false), Frame.ratio(584, false) };
        Felder[35] = new int[] { Frame.ratio(1034, false), Frame.ratio(584, false) };
        Felder[36] = new int[] { Frame.ratio(1034, false), Frame.ratio(673, false) };
        Felder[37] = new int[] { Frame.ratio(1034, false), Frame.ratio(761, false) };
        Felder[38] = new int[] { Frame.ratio(1034, false), Frame.ratio(850, false) };
        Felder[39] = new int[] { Frame.ratio(1034, false), Frame.ratio(938, false) };
        Felder[40] = new int[] { Frame.ratio(945, false), Frame.ratio(938, false) };
        Felder[41] = new int[] { Frame.ratio(945, false), Frame.ratio(850, false) };
        Felder[42] = new int[] { Frame.ratio(945, false), Frame.ratio(760, false) };
        Felder[43] = new int[] { Frame.ratio(945, false), Frame.ratio(673, false) };
        Felder[44] = new int[] { Frame.ratio(945, false), Frame.ratio(585, false) };

        Game game = new Game();
        game.Move(Felder, Test, PlayerNum, FeldNummer);

    }

    static int getField(int PlayerNum) {
        for (int i = 0; i <= Felder.length; i++) {
            if (Test[PlayerNum].getX() == Felder[i][0] && Test[PlayerNum].getY() == Felder[i][1]) {
                return i;
            }
        }
        System.out.println("TestFeld nicht gefunden!");
        return -1;
    }

}