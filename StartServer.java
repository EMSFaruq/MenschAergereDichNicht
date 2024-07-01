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
            ServerSocket socket = new ServerSocket(1);
            System.out.println("Waiting for Connection on Port: " + port);
            socket.accept();
            System.out.println("Connected");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            scanner.close();
            socket.close();
            main(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failure while starting Server!");
        }
    }
}