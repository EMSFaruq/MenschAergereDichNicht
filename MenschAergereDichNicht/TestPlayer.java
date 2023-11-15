package MenschAergereDichNicht;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestPlayer {

    static JFrame frame = Frame.frame;
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
        Test[1].requestFocusInWindow(); // Wichtig, um den Fokus auf das Label zu setzen
    }

    static void MovementHandler(int PlayerNum, int FeldNummer) {
        Felder[0] = new int[] { 0, 0 };
        Felder[1] = new int[] { 857, 938 };
        Felder[2] = new int[] { 857, 850 };
        Felder[3] = new int[] { 857, 760 };
        Felder[4] = new int[] { 857, 675 };
        Felder[5] = new int[] { 857, 585 };
        Felder[6] = new int[] { 768, 585 };
        Felder[7] = new int[] { 682, 585 };
        Felder[8] = new int[] { 592, 585 };
        Felder[9] = new int[] { 505, 585 };
        Felder[10] = new int[] { 505, 498 };
        Felder[11] = new int[] { 505, 410 };
        Felder[12] = new int[] { 592, 410 };
        Felder[13] = new int[] { 682, 410 };
        Felder[14] = new int[] { 769, 410 };
        Felder[15] = new int[] { 857, 410 };
        Felder[16] = new int[] { 857, 320 };
        Felder[17] = new int[] { 857, 235 };
        Felder[18] = new int[] { 857, 145 };
        Felder[19] = new int[] { 857, 58 };
        Felder[20] = new int[] { 945, 58 };
        Felder[21] = new int[] { 1033, 58 };
        Felder[22] = new int[] { 1033, 145 };
        Felder[23] = new int[] { 1033, 234 };
        Felder[24] = new int[] { 1033, 320 };
        Felder[25] = new int[] { 1033, 409 };
        Felder[26] = new int[] { 1122, 409 };
        Felder[27] = new int[] { 1210, 409 };
        Felder[28] = new int[] { 1298, 409 };
        Felder[29] = new int[] { 1386, 409 };
        Felder[30] = new int[] { 1386, 497 };
        Felder[31] = new int[] { 1386, 584 };
        Felder[32] = new int[] { 1299, 584 };
        Felder[33] = new int[] { 1209, 584 };
        Felder[34] = new int[] { 1122, 584 };
        Felder[35] = new int[] { 1034, 584 };
        Felder[36] = new int[] { 1034, 673 };
        Felder[37] = new int[] { 1034, 761 };
        Felder[38] = new int[] { 1034, 850 };
        Felder[39] = new int[] { 1034, 938 };
        Felder[40] = new int[] { 945, 938 };
        Felder[41] = new int[] { 945, 850 };
        Felder[42] = new int[] { 945, 760 };
        Felder[43] = new int[] { 945, 673 };
        Felder[44] = new int[] { 945, 585 };

        Game game = new Game();
        game.Move(Felder, Test, PlayerNum, FeldNummer);

    }

    static int getField(int PlayerNum) {
        for (int i = 0; i <= Felder.length; i++) {
            if (Test[PlayerNum].getX() == Felder[i][0] && Test[PlayerNum].getY() == Felder[i][1]) {
                return i;
            }
        }
        return 0;
    }

}