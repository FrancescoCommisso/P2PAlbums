import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {

    private String serverIps[];

    public Client(){

    }

    protected String[] getServerIps(){
        return this.serverIps;
    }

    public void setServerIps(String[] serverIps) {
        this.serverIps = serverIps;
    }

    protected void init() throws IOException {
        System.out.println("init()");

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress server1IP = InetAddress.getByName("127.0.0.1");

        byte[] receiveData = new byte[1024];
        byte[] sendData = "init".getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, server1IP, Constants.SERVER_1_PORT);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData(),receivePacket.getOffset(), receivePacket.getLength());
        System.out.println("FROM SERVER:" + response);

        clientSocket.close();
    }




    public int hashContentName(String contentName){
        //sum up characters
        int sum = 0;
        for(int i = 0; i<contentName.length();i++){
            sum+= contentName.charAt(i);
        }

        // 4 Servers with ID's 1,2,3,4.
        // Have to add 1 because there is no serverID = 0
        return (sum % 4) + 1;
    }



    void informAndUpdate(String imgPath){
//        File imgFile = null;
//        BufferedImage img = null;
//
//        try {
//           imgFile = new File(imgPath);
//           img = ImageIO.read(imgFile);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//
//        int serverID = hashContentName(imgFile.getName());
    }



    public static void main(String args[]) throws IOException {

//        Client c = new Client("000.000.000.000");
//        c.sendDatagram();
////        int s = c.hashContentName("PhotoOfPerson");
////        System.out.print(s);


    }

}
