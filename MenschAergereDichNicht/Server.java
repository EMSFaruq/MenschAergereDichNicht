package MenschAergereDichNicht;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class Server {

    JFrame frame = Frame.frame;
    Game game = new Game();
    String LocalIP;
    int MinPort = 1;
    int MaxPort = 10;
    Font customFont;
    static Boolean result = false;
    static ArrayList <Integer> Server = new ArrayList<>();
    static InetSocketAddress sockaddr;
    public static void main(String[] args) {
        Frame.FrameUI();
    //    createServer();

    }

    void ServerMenu() {
        frame.getContentPane().removeAll();
        frame.repaint();
        createServer();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread konnte nicht warten!");
        }

        scanPorts(1);
        
        try {
            JLabel Server1 = new JLabel("Localhost: " + Server.get(0));
            Server1.setLocation(frame.getWidth() / 4, frame.getHeight() / 4);
            Server1.setSize(250, 125);

            Font CustomFont = new Font("Poor Richard", Font.PLAIN, 50);
            Server1.setFont(CustomFont);
            frame.add(Server1);
        } catch (Exception e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            game.createText(9, "No Servers Menu", "Left", "Top");            
        }
        game.createText(1, "Back", "Middle", "Bottom");
        game.create(3, new ImageIcon("MenschAergereDichNicht\\Sprites\\Menu\\Multiplayermenu.png"), "Left", "Top", true);
    }

    static void createServer() {
        Thread tesThread = new Thread(() -> {
        try {
            ServerSocket socket = new ServerSocket(5);
            
            for (int i = 1; i <= 4; i++) {
                socket.accept();
                System.out.println("Socket " + i + ": Verbunden!");
            }
            System.out.println(socket);

        } catch (IOException e) {
            System.out.println("Server läuft bereits!");
        }

        });
        tesThread.start();
    }

    void scanPorts(int StartPort) {
        for (int i = StartPort; i <= MaxPort; i++) {
            if(checkPort(i)) {
                Server.add(StartPort);
                System.out.println("Local Port: " + i + " gefunden!");
            }else
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
        
        try(Socket client = new Socket(LocalIP, CurrentPort)){
            result = true;
        } catch (Exception e) {
            result = false;
        }
        });
        PortThread.start();
        return result;
    }

    void refreshJFrame() {
        frame = Frame.frame;
    }
}
