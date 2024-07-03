import java.net.ServerSocket;
import java.util.Random;
import java.util.Scanner;

/**
 * StartServer
 */
public class StartServer {

    public static void main(String[] args) {
        Random random = new Random();
        new StartServer().startServer(random.nextInt(1, 5) + 1);
    }

    void startServer(int port) {
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for Connection on Port: " + port);
                socket.accept();
                System.out.println("Connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failure while starting Server!");
        }
    }
}