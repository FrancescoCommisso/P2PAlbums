import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client extends UDPServer {

    private static final int SERVER_1_ID = 1;
    private static final String SERVER_1_IP = "000.111.222.255";


    private enum DHTCall{
        INIT,INFORM_AND_UPDATE;
    }

    private String serverIps[];

    public Client(int udpPort,InetAddress ip,int id){
        super(udpPort,ip,id);
    }

    protected String[] getServerIps(){
        return this.serverIps;
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

    protected void init(){
        //  MAKE HTTP REQUEST TO SERVER_1_IP FOR
        //  IP'S AND ID'S OF OTHER SERVERS

        // this.serverIP = getOtherServers();
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
