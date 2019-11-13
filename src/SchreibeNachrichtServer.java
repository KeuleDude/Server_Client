import java.io.IOException;
import java.util.Scanner;

public class SchreibeNachrichtServer extends Thread{
    @Override
    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String nachricht = scanner.next();
            System.out.println(nachricht);
            Server.schreibeNachricht(Server.clientSocket, nachricht);
        }
    }
}
