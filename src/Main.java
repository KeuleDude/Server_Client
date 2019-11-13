import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Server s = new Server(1337);
            s.laufen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
