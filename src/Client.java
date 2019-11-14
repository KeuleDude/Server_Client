import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Socket socket;
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost",1337);
            System.out.println("Succesfully connected to Server: /127.0.0.1:1337");
            laufen(socket);
        } catch (IOException e) {
            System.err.println("Couldn`t reach Server. Please try again later");
        }

    }


    public static void laufen(Socket socket){
            SchreibeNachricht schreibeNachricht = new SchreibeNachricht();
            schreibeNachricht.start();
            NachrichtLesenClient nachrichtLesenClient = new NachrichtLesenClient();
            nachrichtLesenClient.start();

    }

    public static String leseNachricht() throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        char[] buffer = new char[200];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
        String nachricht = new String(buffer, 0, anzahlZeichen);
        return nachricht;
    }
    public static void schreibeNachricht(Socket socket, String nachricht) throws IOException {
        PrintWriter printWriter =
                new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
        printWriter.print(nachricht);
        printWriter.flush();
        if(nachricht.equalsIgnoreCase("quit")){
            System.out.println("Connetion closed");
            socket.close();
            System.exit(0);
        }
    }
}
