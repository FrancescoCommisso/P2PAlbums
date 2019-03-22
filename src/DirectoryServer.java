
import java.io.IOException;
import java.net.*;

public class DirectoryServer{

    private static final int DS_PORT= 1234;
    private InetAddress dsIP;
    private int dsID;
    private int leftNeighbor;
    private int rightNeighbor;


    public DirectoryServer(String dsIP,int dsID) {
        try {
            this.dsIP=InetAddress.getByName(dsIP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.dsID = dsID;
    }

    private InetAddress getDSIP() {
        return dsIP;
    }

    public void startServer() throws IOException {
        System.out.println("startServer()");
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
                default: System.out.println("Host #"+dsID+" rceived a bad message: "+message);
            }

        }
    }


    private byte[] init(){
        return this.getDSIP().toString().getBytes();
    }

    private void informAndUpdate(){

    }

    private void queryForContent(){

    }

    private void exit(){

    }


}

