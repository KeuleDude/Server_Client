import java.io.IOException;
import java.net.Socket;

public class Accept extends Thread {

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
			this.stop();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
