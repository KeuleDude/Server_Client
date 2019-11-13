import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NachrichtLesen extends Thread{

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(leseNachricht());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    String leseNachricht() throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                Server.clientSocket.getInputStream()));
        char[] buffer = new char[200];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
        String nachricht = new String(buffer, 0, anzahlZeichen);
        return nachricht;
    }
}
