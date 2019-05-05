import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static Socket socket            = null;
    private static DataInputStream input   = null;
    private static DataOutputStream out     = null;
    private static String msg;


    public static void main(String[] args) {
        int i=2;
        Scanner scanner = new Scanner(System.in);
        /*try {
            socket = new Socket(InetAddress.getLocalHost(), 5000);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("Connected");


        while(true) {
            try
            {
                socket = new Socket(InetAddress.getLocalHost(), 5000);
                out = new DataOutputStream(socket.getOutputStream());
                msg = scanner.next();
                out.writeUTF(msg);
                socket.close();
                out.close();
            }
            catch(UnknownHostException u)
            {
                //System.out.println(u);
            }
            catch(IOException e)
            {
                //System.out.println(e    );
            }


        }


    }
}
