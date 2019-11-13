import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Socket socket;
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost",1337);
            System.out.println(socket.isConnected());
            laufen(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void laufen(Socket socket){
            NachrichtLesenClient nachrichtLesenClient = new NachrichtLesenClient();
            nachrichtLesenClient.start();
            SchreibeNachricht schreibeNachricht = new SchreibeNachricht();
            schreibeNachricht.start();

    }


    public static void schreibeNachricht(Socket socket, String nachricht) throws IOException {
        PrintWriter printWriter =
                new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
        printWriter.print(nachricht);
        printWriter.flush();
    }
}
