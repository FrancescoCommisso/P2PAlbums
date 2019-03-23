import java.io.IOException;
<<<<<<< HEAD
=======
import java.net.InetAddress;
>>>>>>> da3216ecb1c5e68881b5d85318b80acfea8a860d
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
<<<<<<< HEAD
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
=======
        DirectoryServer ds1 = new DirectoryServer(Constants.SERVER_1_IP,1);
        DirectoryServer ds2 = new DirectoryServer(Constants.SERVER_2_IP,2);
        Client client = new Client();

        ds1.openUDPSocket();
//        ds2.openUDPSocket();
>>>>>>> da3216ecb1c5e68881b5d85318b80acfea8a860d

        ds1.openTCPSocket();
        ds2.openTCPSocket();
        TimeUnit.SECONDS.sleep(1);
        ds1.sendTCPMessage("You getting these messages boy?",InetAddress.getByName(Constants.SERVER_2_IP));

        client.init();

    }
}
