package MenschAergereDichNicht;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Players {

    static int[][] Felder = new int[48 + 1][2];
    static int PersonalTeam = 0;
    static int current = 0;
    Game game = new Game();
    JLabel[] Playerlabel = new JLabel[16 + 1];
    JLabel[] Playername = new JLabel[Playerlabel.length];
    int[] LastField = new int[Playerlabel.length];
    int TeamSize = 4;
    String[] Teams = new String[Playerlabel.length / TeamSize];
    static JFrame frame = Frame.frame;

    public static void main(String[] args) {
        Frame.FrameUI();
    }

    public void Player() {
        refreshJFrame();

        for (int i = 1; i < Playerlabel.length; i++) {

            int IconNummer = 0;

            if (i <= 4) {
                IconNummer = 1;
            } else if (i <= 8) {
                IconNummer = 2;
            } else if (i <= 12) {
                IconNummer = 3;
            } else if (i <= 16) {
                IconNummer = 4;
            }
            Playerlabel[i] = new JLabel();
            ImageIcon icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Characters\\Player" + IconNummer + ".png");

            Playerlabel[i].setIcon(new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth(),
                    Frame.ratio(icon.getIconHeight(), !frame.isUndecorated()),
                    Image.SCALE_SMOOTH)));
            Playerlabel[i].setSize(icon.getIconWidth(), icon.getIconHeight());

            if (LastField != null) {
                game.Move(Felder, Playerlabel, i, LastField[i]);
            }

            frame.add(Playerlabel[i]);
        }

        setTeam();
        clickPlayer(1);
        System.out.println("Personalteam: " + PersonalTeam);

    }

    public void clickPlayer(int schritte) {

        for (int i = 1; i < Playerlabel.length; i++) {
            Playerlabel[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    getFields();
                    int Player = getPlayer(Playerlabel, (JLabel) e.getSource());
                    getStartPoint(getTeam(Player));
                    int fieldNumber = getPlayerField(Playerlabel, Player);
                    int differenz = getTeam(Player) * 10 - 10;

                    if (getTeam(Player) == PersonalTeam) {

                        if (fieldNumber == Felder.length - 1) {
                            return;
                        }

                        if (fieldNumber + schritte >= 40) {
                            MovementHandler(fieldNumber + schritte - differenz, Player);
                        } else
                            MovementHandler(fieldNumber + 1, Player);
                    }
                }
            });

        }
    }

    public void MovementHandler(int Feld, int Spieler) {
        getStartPoint(getTeam(Spieler));
        getFields();
        Game game = new Game();
        game.Move(Felder, Playerlabel, Spieler, Feld);
    }

    public void SortTeam(int Team) {

        for (int i = 1; i <= 4; i++) {
            getStartPoint(Team);

            switch (Team) {
                case 1:
                    MovementHandler(i, i);
                    break;
                case 2:
                    MovementHandler(i, i + 4);
                    break;
                case 3:
                    MovementHandler(i, i + 8);
                    break;
                case 4:
                    MovementHandler(i, i + 12);
                    break;
            }
        }

    }

    static void getStartPoint(int Team) {

        if (Team == 1) {
            Felder[1] = new int[] { 504, 938 };
            Felder[2] = new int[] { 504, 850 };
            Felder[3] = new int[] { 592, 850 };
            Felder[4] = new int[] { 592, 938 };
        } else if (Team == 2) {
            Felder[1] = new int[] { 504, 145 };
            Felder[2] = new int[] { 504, 58 };
            Felder[3] = new int[] { 592, 58 };
            Felder[4] = new int[] { 592, 145 };
        } else if (Team == 3) {
            Felder[1] = new int[] { 1297, 145 };
            Felder[2] = new int[] { 1297, 58 };
            Felder[3] = new int[] { 1386, 58 };
            Felder[4] = new int[] { 1386, 145 };
        } else if (Team == 4) {
            Felder[1] = new int[] { 1297, 938 };
            Felder[2] = new int[] { 1297, 850 };
            Felder[3] = new int[] { 1386, 938 };
            Felder[4] = new int[] { 1386, 850 };
        }

        for (int i = 0; i <= 4; i++) {
            Felder[i] = new int[] { Frame.ratio(Felder[i][0], false),
                    Frame.ratio(Felder[i][1], !frame.isUndecorated()) };
        }
    }

    void getFields() {
        refreshJFrame();

        Felder[5] = new int[] { 857, 938 };
        Felder[6] = new int[] { 857, 850 };
        Felder[7] = new int[] { 857, 760 };
        Felder[8] = new int[] { 857, 675 };
        Felder[9] = new int[] { 857, 585 };
        Felder[10] = new int[] { 768, 585 };
        Felder[11] = new int[] { 682, 585 };
        Felder[12] = new int[] { 592, 585 };
        Felder[13] = new int[] { 505, 585 };
        Felder[14] = new int[] { 505, 498 };
        Felder[15] = new int[] { 505, 410 };
        Felder[16] = new int[] { 592, 410 };
        Felder[17] = new int[] { 682, 410 };
        Felder[18] = new int[] { 769, 410 };
        Felder[19] = new int[] { 857, 410 };
        Felder[20] = new int[] { 857, 320 };
        Felder[21] = new int[] { 857, 235 };
        Felder[22] = new int[] { 857, 145 };
        Felder[23] = new int[] { 857, 58 };
        Felder[24] = new int[] { 945, 58 };
        Felder[25] = new int[] { 1033, 58 };
        Felder[26] = new int[] { 1033, 145 };
        Felder[27] = new int[] { 1033, 234 };
        Felder[28] = new int[] { 1033, 320 };
        Felder[29] = new int[] { 1033, 409 };
        Felder[30] = new int[] { 1122, 409 };
        Felder[31] = new int[] { 1210, 409 };
        Felder[32] = new int[] { 1298, 409 };
        Felder[33] = new int[] { 1386, 409 };
        Felder[34] = new int[] { 1386, 497 };
        Felder[35] = new int[] { 1386, 584 };
        Felder[36] = new int[] { 1299, 584 };
        Felder[37] = new int[] { 1209, 584 };
        Felder[38] = new int[] { 1122, 584 };
        Felder[39] = new int[] { 1034, 584 };
        Felder[40] = new int[] { 1034, 673 };
        Felder[41] = new int[] { 1034, 761 };
        Felder[42] = new int[] { 1034, 850 };
        Felder[43] = new int[] { 1034, 938 };
        Felder[44] = new int[] { 945, 938 };
        Felder[45] = new int[] { 945, 850 };
        Felder[46] = new int[] { 945, 760 };
        Felder[47] = new int[] { 945, 673 };
        Felder[48] = new int[] { 945, 585 };

        for (int i = 0; i < Felder.length; i++) {
            Felder[i] = new int[] { Frame.ratio(Felder[i][0], false),
                    Frame.ratio(Felder[i][1], !frame.isUndecorated()) };
        }
    }

    ImageIcon getIcon(int IconNummer) {
        ImageIcon icon = new ImageIcon("MenschAergereDichNicht\\Sprites\\Characters\\Player" + IconNummer + ".png");
        return icon;
    }

    void playerNames() {
        for (int i = 1; i < Playerlabel.length; i++) {
            Playername[i] = new JLabel();
            Playername[i].setText(Teams[i]);

            Playername[i].setLocation(Playerlabel[i].getX(),
                    (Playerlabel[i].getY() - Playerlabel[1].getHeight() / 2));
        }

    }

    static int getPlayerField(JLabel[] Playerlabel, int PlayerNum) {

        for (int i = 0; i < Felder.length; i++) {
            if (Playerlabel[PlayerNum].getX() == Felder[i][0] && Playerlabel[PlayerNum].getY() == Felder[i][1]) {
                return i;
            }
        }
        System.out.println("Feld nicht gefunden!");
        System.out.println();
        return -1;
    }

    static int getPlayer(JLabel[] Button, JLabel Player) {
        for (int i = 1; i < Button.length; i++) {
            if (Button[i] == Player) {
                return i;
            }
        }
        System.out.println("Spieler nicht gefunden!");
        return -1;
    }

    void setTeam() {
        for (int i = 1; i < Teams.length; i++) {

            String Nickname = "Player(" + i + ")";

            if (Teams[i] == null) {
                Teams[i] = Nickname;
                PersonalTeam = i;
            }
        }
    }

    int getTeam(int Spieler) {
        if (Spieler <= 4) {
            return 1;
        } else if (Spieler <= 8) {
            return 2;
        } else if (Spieler <= 12) {
            return 3;
        } else if (Spieler <= 16) {
            return 4;
        }
        System.out.println("Team nicht gefunden!");
        return -1;
    }

    public void refresh() {

        frame.getContentPane().removeAll();

        Player();

        for (int i = 1; i < Playerlabel.length; i++) {
            Playerlabel[i].setIcon((getIcon(i)));
            game.Move(Felder, Playerlabel, i, LastField[i]);
        }

    }

    public void save() {
        for (int i = 1; i < Playerlabel.length; i++) {
            LastField[i] = getPlayerField(Playerlabel, i);
        }
    }

    public void refreshJFrame() {
        frame = Frame.frame;
    }
}
