import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static ServerSocket socket;
    public static Socket clientSocket;
    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        clientSocket = socket.accept();
        OutputStream output = clientSocket.getOutputStream();
    }

    public static void schreibeNachricht(Socket clientSocket, String nachricht) {
    }

    public void laufen() throws IOException {
            NachrichtLesen nachrichtLesen = new NachrichtLesen();
            nachrichtLesen.start();
            SchreibeNachrichtServer schreibeNachrichtServer = new SchreibeNachrichtServer();
            schreibeNachrichtServer.start();



    }

    void schreibeNachricht( String nachricht) throws IOException {
        PrintWriter printWriter =
                new PrintWriter(
                        new OutputStreamWriter(
                                clientSocket.getOutputStream()));
        printWriter.print(nachricht);
        printWriter.flush();
    }
}
