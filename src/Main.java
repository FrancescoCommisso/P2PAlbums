import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Main {

    //THREADS AINT THE WAY TO GO ...

    public static void main(String args[]) throws UnknownHostException, InterruptedException {
        DirectoryServer ds = new DirectoryServer(1234, InetAddress.getLocalHost(),11);
        Client client = new Client(1235, InetAddress.getLocalHost(),21);

        Thread thread1 = new Thread(() -> {
            try {
                ds.startUDPServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                client.startUDPServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        TimeUnit.SECONDS.sleep(5);
        Thread thread3 = new Thread(() -> {
            try {
                client.sendDatagram("respond",client.getIp(),1234);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        thread3.start();

    }
}
