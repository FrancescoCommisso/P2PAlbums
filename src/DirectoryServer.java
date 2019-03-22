
import java.io.IOException;
import java.net.*;

public class DirectoryServer{

    private static final int DS_PORT= 1234;
    private InetAddress IPAddress;
    private int directoryServerID;
    private int leftNeighbor;
    private int rightNeighbor;


    DirectoryServer(String IPAddress, int directoryServerID) {
        try {
            this.IPAddress =InetAddress.getByName(IPAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.directoryServerID = directoryServerID;
    }

    private InetAddress getDSIP() {
        return IPAddress;
    }

    void openUDPSocket() throws IOException {
        System.out.println("openUDPSocket()");
        DatagramSocket serverSocket = new DatagramSocket(DS_PORT);
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String message = new String( receivePacket.getData(),receivePacket.getOffset(),receivePacket.getLength());

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            switch (message){
                case "init":
                    sendData = this.init();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                    break;
                case "informAndUpdate": this.informAndUpdate();
                    break;
                case "queryForContent": this.queryForContent();
                    break;
                case "exit": this.exit();
                    break;
                default: System.out.println("Host #"+ directoryServerID +" rceived a bad message: "+message);
            }

        }
    }

    void startUDPServer(){
        Thread thread1 = new Thread(() -> {
            try {
                this.openUDPSocket();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
    }


    private byte[] init(){
        return this.getDSIP().toString().substring(1).getBytes();
    }

    private void informAndUpdate(){

    }

    private void queryForContent(){

    }

    private void exit(){

    }


}

