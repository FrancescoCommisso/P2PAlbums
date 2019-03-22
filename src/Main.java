import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {
        DirectoryServer ds = new DirectoryServer(Constants.SERVER_1_IP,1);
        Client client = new Client();

        ds.startUDPServer();

        client.init();



    }
}
