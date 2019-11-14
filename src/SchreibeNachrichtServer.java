import java.io.IOException;
import java.util.Scanner;

public class SchreibeNachrichtServer extends Thread{
    @Override
    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String nachricht ="";
            nachricht = scanner.nextLine();
            try {
                Server.schreibeNachricht(nachricht);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
