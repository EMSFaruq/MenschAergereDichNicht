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

            Playerlabel[i]
                    .setIcon(new ImageIcon(icon.getImage().getScaledInstance(Frame.ratio(icon.getIconWidth(), false),
                            Frame.ratio(icon.getIconHeight(), false), Image.SCALE_SMOOTH)));
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
                    getStartPoint(getTeam(Player), Felder);
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
        getStartPoint(getTeam(Spieler), Felder);
        getFields();
        Game game = new Game();
        game.Move(Felder, Playerlabel, Spieler, Feld);
    }

    public void SortTeam(int Team) {

        for (int i = 1; i <= 4; i++) {
            getStartPoint(Team, Felder);

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

    static void getStartPoint(int Team, int[][] Feld) {

        if (Team == 1) {
            Feld[1] = new int[] { Frame.ratio(504, false), Frame.ratio(938, true) };
            Feld[2] = new int[] { Frame.ratio(504, false), Frame.ratio(850, true) };
            Feld[3] = new int[] { Frame.ratio(592, false), Frame.ratio(850, true) };
            Feld[4] = new int[] { Frame.ratio(592, false), Frame.ratio(938, true) };
        } else if (Team == 2) {
            Feld[1] = new int[] { Frame.ratio(504, false), Frame.ratio(145, true) };
            Feld[2] = new int[] { Frame.ratio(504, false), Frame.ratio(58, true) };
            Feld[3] = new int[] { Frame.ratio(592, false), Frame.ratio(58, true) };
            Feld[4] = new int[] { Frame.ratio(592, false), Frame.ratio(145, true) };
        } else if (Team == 3) {
            Feld[1] = new int[] { Frame.ratio(1297, false), Frame.ratio(145, true) };
            Feld[2] = new int[] { Frame.ratio(1297, false), Frame.ratio(58, true) };
            Feld[3] = new int[] { Frame.ratio(1386, false), Frame.ratio(58, true) };
            Feld[4] = new int[] { Frame.ratio(1386, false), Frame.ratio(145, true) };
        } else if (Team == 4) {
            Feld[1] = new int[] { Frame.ratio(1297, false), Frame.ratio(938, true) };
            Feld[2] = new int[] { Frame.ratio(1297, false), Frame.ratio(850, true) };
            Feld[3] = new int[] { Frame.ratio(1386, false), Frame.ratio(938, true) };
            Feld[4] = new int[] { Frame.ratio(1386, false), Frame.ratio(850, true) };
        }
    }

    void getFields() {
        refreshJFrame();

        Felder[5] = new int[] { Frame.ratio(857, false), Frame.ratio(938, true) };
        Felder[6] = new int[] { Frame.ratio(857, false), Frame.ratio(850, true) };
        Felder[7] = new int[] { Frame.ratio(857, false), Frame.ratio(760, true) };
        Felder[8] = new int[] { Frame.ratio(857, false), Frame.ratio(675, true) };
        Felder[9] = new int[] { Frame.ratio(857, false), Frame.ratio(585, true) };
        Felder[10] = new int[] { Frame.ratio(768, false), Frame.ratio(585, true) };
        Felder[11] = new int[] { Frame.ratio(682, false), Frame.ratio(585, true) };
        Felder[12] = new int[] { Frame.ratio(592, false), Frame.ratio(585, true) };
        Felder[13] = new int[] { Frame.ratio(505, false), Frame.ratio(585, true) };
        Felder[14] = new int[] { Frame.ratio(505, false), Frame.ratio(498, true) };
        Felder[15] = new int[] { Frame.ratio(505, false), Frame.ratio(410, true) };
        Felder[16] = new int[] { Frame.ratio(592, false), Frame.ratio(410, true) };
        Felder[17] = new int[] { Frame.ratio(682, false), Frame.ratio(410, true) };
        Felder[18] = new int[] { Frame.ratio(769, false), Frame.ratio(410, true) };
        Felder[19] = new int[] { Frame.ratio(857, false), Frame.ratio(410, true) };
        Felder[20] = new int[] { Frame.ratio(857, false), Frame.ratio(320, true) };
        Felder[21] = new int[] { Frame.ratio(857, false), Frame.ratio(235, true) };
        Felder[22] = new int[] { Frame.ratio(857, false), Frame.ratio(145, true) };
        Felder[23] = new int[] { Frame.ratio(857, false), Frame.ratio(58, true) };
        Felder[24] = new int[] { Frame.ratio(945, false), Frame.ratio(58, true) };
        Felder[25] = new int[] { Frame.ratio(1033, false), Frame.ratio(58, true) };
        Felder[26] = new int[] { Frame.ratio(1033, false), Frame.ratio(145, true) };
        Felder[27] = new int[] { Frame.ratio(1033, false), Frame.ratio(234, true) };
        Felder[28] = new int[] { Frame.ratio(1033, false), Frame.ratio(320, true) };
        Felder[29] = new int[] { Frame.ratio(1033, false), Frame.ratio(409, true) };
        Felder[30] = new int[] { Frame.ratio(1122, false), Frame.ratio(409, true) };
        Felder[31] = new int[] { Frame.ratio(1210, false), Frame.ratio(409, true) };
        Felder[32] = new int[] { Frame.ratio(1298, false), Frame.ratio(409, true) };
        Felder[33] = new int[] { Frame.ratio(1386, false), Frame.ratio(409, true) };
        Felder[34] = new int[] { Frame.ratio(1386, false), Frame.ratio(497, true) };
        Felder[35] = new int[] { Frame.ratio(1386, false), Frame.ratio(584, true) };
        Felder[36] = new int[] { Frame.ratio(1299, false), Frame.ratio(584, true) };
        Felder[37] = new int[] { Frame.ratio(1209, false), Frame.ratio(584, true) };
        Felder[38] = new int[] { Frame.ratio(1122, false), Frame.ratio(584, true) };
        Felder[39] = new int[] { Frame.ratio(1034, false), Frame.ratio(584, true) };
        Felder[40] = new int[] { Frame.ratio(1034, false), Frame.ratio(673, true) };
        Felder[41] = new int[] { Frame.ratio(1034, false), Frame.ratio(761, true) };
        Felder[42] = new int[] { Frame.ratio(1034, false), Frame.ratio(850, true) };
        Felder[43] = new int[] { Frame.ratio(1034, false), Frame.ratio(938, true) };
        Felder[44] = new int[] { Frame.ratio(945, false), Frame.ratio(938, true) };
        Felder[45] = new int[] { Frame.ratio(945, false), Frame.ratio(850, true) };
        Felder[46] = new int[] { Frame.ratio(945, false), Frame.ratio(760, true) };
        Felder[47] = new int[] { Frame.ratio(945, false), Frame.ratio(673, true) };
        Felder[48] = new int[] { Frame.ratio(945, false), Frame.ratio(585, true) };

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
