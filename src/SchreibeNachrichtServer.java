import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SchreibeNachrichtServer extends Thread {
	@Override
	public void run() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String nachricht = "";
			nachricht = scanner.nextLine();
			if (nachricht.equalsIgnoreCase(".quit")) {
				try {
					Server.schreibeNachricht("Closing Connection...");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Server.schreibeNachricht(".quit");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Server.socket.close();
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			} else {
				try {
					Server.schreibeNachricht(nachricht);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
