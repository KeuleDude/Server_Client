import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NachrichtLesen {

	static void leseNachricht(Socket clientSocket) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		char[] buffer = new char[200];
		int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
		String nachricht = new String(buffer, 0, anzahlZeichen);
		if (nachricht.equalsIgnoreCase("/quit")) {
			System.out.println("Connection closed by " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
			Server.clients.remove(clientSocket);
			System.out.println("There are " + Server.clients.size() + " more Client(s) connected");
			Accept.sockReader.get(clientSocket).stop();
			clientSocket.close();

		} else if (nachricht.startsWith("/name ")) {
			nachricht = nachricht.substring(6);
			String key = Server.socketnames.get(clientSocket);
			Server.names.remove(key);
			Server.socketnames.remove(clientSocket);
			Server.names.put(nachricht, clientSocket);
			Server.socketnames.put(clientSocket, nachricht);
			System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " changed his Name to '"
					+ nachricht + "'");
		} else if (nachricht.equalsIgnoreCase("/list")) {
			if (Server.clients.size() == 1) {
				Server.schreibeNachricht("You are the only Person who is online");
				return;
			}
			String names = "Online Users: \n";
			for (Socket socket : Server.clients) {
				if (socket != clientSocket) {
					names = names + Server.socketnames.get(socket) + "\t" + socket.getInetAddress() + ":"
							+ socket.getPort() + "\n";
				}
			}
			Server.schreibeNachricht(names,clientSocket);
		}else if(nachricht.startsWith("/broadcast ")) {
			nachricht = nachricht.substring(11);
			Server.schreibeNachricht(nachricht);
		}else if(nachricht.startsWith("/msg ")) {
			nachricht = nachricht.substring(5);
			String[] split= nachricht.split(" ",2);
			String name = split[0];
			String nachrichtneu = split[1];
			if(!Server.names.containsKey(name)) {
				Server.schreibeNachricht("This users isnt Existing type /list for a List of users who exist.", clientSocket);
			}else {
			Socket socket = Server.names.get(name);
			Server.schreibeNachricht("Message from " + Server.socketnames.get(clientSocket) + ": " +  nachrichtneu, socket);
			}
		}
		
		else {
			System.out.println(
					"Message from " + Server.socketnames.get(clientSocket) + " : " + nachricht);
		}
	}
}
