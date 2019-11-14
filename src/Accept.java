import java.io.IOException;
import java.net.Socket;

public class Accept extends Thread{

    @Override
    public void run() {
        while(true){
            try {
                Socket socket = Server.socket.accept();
                Server.clients.add(socket);
                System.out.println("Connected: " + socket.getInetAddress()+ ":" + socket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
