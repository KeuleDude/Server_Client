import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageReader extends Thread{
	
	private final Socket client;
	private final DataInputStream inputStream;
	
	public MessageReader(Socket client) throws IOException {
		this.client = client;
        this.inputStream = new DataInputStream(client.getInputStream());
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				NachrichtLesen.leseNachricht(client);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
