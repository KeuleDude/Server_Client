import java.io.IOException;
import java.util.Scanner;

public class SchreibeNachricht extends Thread{
    @Override
    public void run() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String nachricht = scanner.next();
            System.out.println(nachricht);
            try {
                Client.schreibeNachricht(Client.socket, nachricht);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
