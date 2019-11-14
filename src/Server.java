import javax.print.DocFlavor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static ServerSocket socket;
    public static ArrayList<Socket> clients;
    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        clients = new ArrayList<>();
        Accept accept = new Accept();
        accept.start();
    }


    public void laufen() throws IOException {

            SchreibeNachrichtServer schreibeNachrichtServer = new SchreibeNachrichtServer();
            schreibeNachrichtServer.start();
            NachrichtLesen nachrichtLesen = new NachrichtLesen();
            nachrichtLesen.start();
            CheckConnected checkConnected = new CheckConnected();
            checkConnected.start();



    }

    static void schreibeNachricht(String nachricht) throws IOException {
        if(clients.size()<=0){
            System.err.println("There is no Client Connected. So nobody recieved your Message");
            return;
        }
        for(Socket clientSocket : clients) {
            PrintWriter printWriter =
                    new PrintWriter(
                            new OutputStreamWriter(
                                    clientSocket.getOutputStream()));
            printWriter.print(nachricht);
            printWriter.flush();
        }
    }
}
