import java.io.IOException;
import java.util.Scanner;

public class SchreibeNachricht extends Thread{
    @Override
    public void run() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String nachricht = scanner.nextLine();
            try {
                Client.schreibeNachricht(Client.socket, nachricht);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
