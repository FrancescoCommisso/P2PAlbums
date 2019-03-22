import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        DirectoryServer ds = new DirectoryServer(Constants.SERVER_1_IP,1);
        Client client = new Client();

        Thread thread1 = new Thread(() -> {
            try {
                ds.startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        client.init();



    }
}
