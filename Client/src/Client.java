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
        try
        {
            socket = new Socket(InetAddress.getLocalHost(), 5000);
            System.out.println("Connected");

            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("abcd");
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

    }
}
