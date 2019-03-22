import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        DirectoryServer ds = new DirectoryServer(Constants.SERVER_1_IP,1);
        Client client = new Client();

        Thread thread1 = new Thread(() -> {
            try {
                ds.openUDPSocket();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        TimeUnit.SECONDS.sleep(10);

        client.init();



    }
}
