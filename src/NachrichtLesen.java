import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NachrichtLesen extends Thread{

    @Override
    public void run() {
        while(true) {
            try {
                leseNachricht();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void leseNachricht() throws IOException {
        System.out.print("");
        for(int i =0;i<Server.clients.size();i++) {
            Socket clientSocket = Server.clients.get(i);
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));
            char[] buffer = new char[200];
            int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
            String nachricht = new String(buffer, 0, anzahlZeichen);
            if(nachricht.equalsIgnoreCase(".quit")){
                System.out.println("Connection closed by " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                Server.clients.remove(i);
                System.out.println("There are " + Server.clients.size() + " more Client(s) connected");
                clientSocket.close();
                return;
            }
            if(nachricht.startsWith(".name ")) {
            	nachricht = nachricht.substring(6);
            	Server.names.set(i, nachricht);
            	System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " changed his Name to '" + nachricht +"'");
            	return;
            }
            System.out.println("Message from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " :");
            System.out.println(nachricht);
        }
    }
}
