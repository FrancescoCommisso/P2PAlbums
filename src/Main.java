import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import com.sun.tools.internal.jxc.ap.Const;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        DirectoryServer ds1 = new DirectoryServer(Constants.SERVER_1_IP,1);
        DirectoryServer ds2 = new DirectoryServer(Constants.SERVER_2_IP,2);
        Client client = new Client();

        ds1.openUDPSocket();
        ds2.openUDPSocket();

        ds1.openTCPSocket();

        ds2.openTCPSocket();
        TimeUnit.SECONDS.sleep(1);

        ds1.sendTCPMessage("Hello fuckwad",InetAddress.getByName(Constants.SERVER_2_IP));

        client.init();

    }
}
