import java.net.InetAddress;

public class Server  {

    int tcpPort;
    InetAddress ip;

    public Server(int tcpPort, InetAddress ip){
        this.tcpPort = tcpPort;
        this.ip = ip;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getTcpPort() {
        return tcpPort;
    }


    protected void startServer(){

    }




}
