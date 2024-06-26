package MenschAergereDichNicht;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

public class Server extends JPanel {

    Game game = new Game();
    String LocalIP;
    int MinPort = 1;
    int MaxPort = 10;
    Font customFont;
    static ServerSocket socket;
    static Boolean result = false;
    static ArrayList<Integer> Server = new ArrayList<>();
    static InetSocketAddress sockaddr;

    public static void main(String[] args) {
        new Frame().Init();
        // createServer();

    }

    void ServerMenu() {
        removeAll();
        repaint();
        createServer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread konnte nicht warten!");
        }

        scanPorts(1);

        try {
            JLabel Server1 = new JLabel("Localhost: " + Server.get(0));
            Server1.setLocation(getWidth() / 4, getHeight() / 4);
            Server1.setSize(250, 125);

            Font CustomFont = new Font("Poor Richard", Font.PLAIN, 50);
            Server1.setFont(CustomFont);
            add(Server1);
        } catch (Exception e) {
            removeAll();
            repaint();
            game.createText(9, "No Servers Menu", "Left", "Top");
        }
        game.createText(1, "Back", "Middle", "Bottom");
        game.create(3, new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\Multiplayermenu.png"), "Left", "Top");
    }

    static void createServer() {
        Thread TestThread = new Thread(() -> {
            try {
                socket = new ServerSocket(5);
            } catch (IOException e) {
                System.out.println("Server läuft bereits!");
            }

            Socket[] client = new Socket[4];
            DataInputStream[] InputStream = new DataInputStream[4];

            for (int i = 1; i <= 4; i++) {
                try {
                    client[i] = socket.accept();
                    InputStream[i] = new DataInputStream(client[i].getInputStream());
                    System.out.println("Socket: " + i + " Verbunden");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        TestThread.start();
    }

    Thread SendThread = new Thread(() -> {

    });

    void scanPorts(int StartPort) {
        for (int i = StartPort; i <= MaxPort; i++) {
            if (checkPort(i)) {
                Server.add(StartPort);
                System.out.println("Local Port: " + i + " gefunden!");
            } else
                System.out.println("Local Port: " + i + " nicht verfügbar!");
        }
        System.out.println(Server);
    }

    boolean checkPort(int CurrentPort) {
        try {
            LocalIP = Inet4Address.getLocalHost().getHostAddress();
        } catch (Exception e) {
            System.out.println("IP nicht gefunden!");
        }
        Thread PortThread = new Thread(() -> {

            try (Socket client = new Socket(LocalIP, CurrentPort)) {
                result = true;
            } catch (Exception e) {
                result = false;
            }
        });
        PortThread.start();
        return result;
    }
}
