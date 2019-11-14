import java.net.Socket;

public class CheckConnected extends Thread {

    @Override
    public void run() {
        for(int i =0;i<Server.clients.size();i++){
            Socket clientSocket = Server.clients.get(i);
            boolean connected = clientSocket.isConnected() && !clientSocket.isClosed();
            if(!connected){
                Server.clients.remove(i);
            }
        }
    }
}
