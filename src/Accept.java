import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class Accept extends Thread {
	
	
	public static HashMap<Socket, MessageReader> sockReader = new HashMap<Socket, MessageReader>();
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		Socket socket = null;
		try {
			socket = Server.socket.accept();
			Server.clients.add(socket);
			Server.names.put("null",socket);
			Server.socketnames.put(socket, "null");
			System.out.println("Connected: " + socket.getInetAddress() + ":" + socket.getPort());
			Accept accept = new Accept();
			accept.start();
			MessageReader messageReader = new MessageReader(socket);
			messageReader.start();
			sockReader.put(socket, messageReader);
			this.stop();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
