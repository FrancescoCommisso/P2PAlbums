import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class DirectoryServer{

    private String IPAddress;
    private int directoryServerID;
    private int leftNeighbor;
    private int rightNeighbor;


    DirectoryServer(String IPAddress, int directoryServerID) {

        this.IPAddress =IPAddress;
        this.directoryServerID = directoryServerID;
    }

    public static int createFakePortFromID(int id){
        return 9000 + id;
    }

    private void createUDPSocket() throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(createFakePortFromID(directoryServerID),InetAddress.getByName(Constants.LOCAL_IP));
        System.out.println("DirectoryServer: "+ directoryServerID + " creating UDP Socket at: "+ serverSocket.getLocalAddress() +":"+serverSocket.getLocalPort());

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
                default: System.out.println("Host #"+ directoryServerID +" received a bad message: "+message);
            }
        }
    }

    void openUDPSocket(){
        Thread thread1 = new Thread(() -> {
            try {
                this.createUDPSocket();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
    }

    private void startListeningTCP() throws IOException {
        String clientMessage;
        String response;
        ServerSocket welcomeSocket = new ServerSocket(createFakePortFromID(directoryServerID),0,InetAddress.getByName(Constants.LOCAL_IP));
        System.out.println("DirectoryServer: "+ directoryServerID + " creating TCP Socket at: "+ welcomeSocket.getInetAddress().toString() + ":" + welcomeSocket.getLocalPort());

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientMessage = inFromClient.readLine();
            System.out.println("after clientMESSAGE");

            System.out.println("Received: " + clientMessage);
            response = clientMessage.toUpperCase() + 'n';
            outToClient.writeBytes(response);
        }
    }




    void openTCPSocket(){

        Thread thread1 = new Thread(() -> {
            try {
                this.startListeningTCP();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
    }



    void sendTCPMessage(String data,InetAddress directoryServerIP,int remortePort) throws IOException {
        String response;

        Socket clientSocket = new Socket(directoryServerIP, remortePort);
        System.out.println("sending: " + data + " to " + clientSocket.getLocalAddress()+":"+clientSocket.getPort());

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        outToServer.writeBytes(data + '\n');
        response = inFromServer.readLine();
        System.out.println("FROM SERVER: " + response);
        clientSocket.close();
    }


    private byte[] init(){
        return this.IPAddress.getBytes();
    }

    private void informAndUpdate(){

    }

    private void queryForContent(){

    }

    private void exit(){

    }


}

