import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public class UDPServer {

    private int udpPort;
    private InetAddress ip;
    private int id;

    public UDPServer(int udpPort,InetAddress ip, int id){
        this.udpPort = udpPort;
        this.ip = ip;
        this.id = id;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getId() {
        return id;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void sendDatagram(String applicationData, InetAddress destinationIp, int destinationPort) throws IOException {
        System.out.println("sendDataGram()");
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = applicationData.getBytes( StandardCharsets.UTF_8 );
        DatagramPacket packet = new DatagramPacket(buf, buf.length, destinationIp, destinationPort);
        socket.send(packet);
    }

    public void sendDatagram(String applicationData, SocketAddress socketAddress) throws IOException {
        System.out.println("sendDataGram()");
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = applicationData.getBytes( StandardCharsets.UTF_8 );
        DatagramPacket packet = new DatagramPacket(buf, buf.length, socketAddress);
        socket.send(packet);
    }

    protected void startUDPServer() throws IOException {
        DatagramSocket ds = new DatagramSocket(this.udpPort);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive;
        while (true)
        {
            System.out.println("UDP Server: "+ this.getId() + " Running on port: " + this.getUdpPort());

            DpReceive = new DatagramPacket(receive, receive.length);
            ds.receive(DpReceive);

            System.out.println("Server: " + id + " Received the message: " + data(receive) +" From client: " + DpReceive.getSocketAddress());

            if (data(receive).toString().equals("respond"))
            {

                sendDatagram("Im Responding",DpReceive.getSocketAddress());
//                break;
            }

            // Clear the buffer after every message.
            receive = new byte[65535];
        }
    }






    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
