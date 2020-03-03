import javax.print.DocFlavor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
	public static ServerSocket socket;
	public static ArrayList<Socket> clients;
	public static HashMap<String, Socket> names;
	public static HashMap<Socket, String> socketnames;

	public Server(int port) throws IOException {
		socket = new ServerSocket(port);
		clients = new ArrayList<>();
		names = new HashMap<>();
		socketnames = new HashMap<Socket, String>();
		Accept accept = new Accept();
		accept.start();
	}

	public void laufen() throws IOException {
		System.out.println("Welcome to the Server Messaging System. Clients can now connect to your Server.");
		SchreibeNachrichtServer schreibeNachrichtServer = new SchreibeNachrichtServer();
		schreibeNachrichtServer.start();
	}

	static void schreibeNachricht(String nachricht) throws IOException {
		if (clients.size() <= 0) {
			System.err.println("There is no Client Connected. So nobody recieved your Message");
			return;
		}
		for (Socket clientSocket : clients) {
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			printWriter.print(nachricht);
			printWriter.flush();
		}
	}

	static void schreibeNachricht(String nachricht, Socket socket) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printWriter.print(nachricht);
		printWriter.flush();

	}
}
