import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static Socket socket            = null;
    private static DataInputStream input   = null;
    private static DataOutputStream out     = null;

    public static void main(String[] args) {
        int i=2;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 5000);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected");


        while(i>=0) {
            try
            {
                out.writeUTF("abcd");
            }
            catch(UnknownHostException u)
            {
                System.out.println(u);
            }
            catch(IOException e)
            {
                System.out.println(e    );
            }
            i--;
        }


    }
}
