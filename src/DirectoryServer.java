
import java.net.InetAddress;

public class DirectoryServer extends UDPServer{

    public DirectoryServer(int udpPort, InetAddress ip, int id) {
        super(udpPort, ip, id);
    }
}

