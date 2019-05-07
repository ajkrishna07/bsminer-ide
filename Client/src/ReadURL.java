import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadURL {

    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("http://localhost/main.c");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
                e.printStackTrace();
        }
        String inputLine;
        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
                System.out.println(inputLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
