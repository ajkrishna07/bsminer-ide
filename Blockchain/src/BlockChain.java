import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    private static ArrayList<String> transaction1=new ArrayList<>();
    private static Socket socket = null;
    private static ServerSocket server = null;
    private static DataInputStream in = null;
    private static String line;
    private static int i=0;


    public static int difficulty = 2;

    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:

        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block " + i + "... ");
        blockchain.get(i++).mineBlock(difficulty);

        /*try {
            server = new ServerSocket(5000);
            socket = server.accept();
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        while(true) {
            try {

                server = new ServerSocket(5000);
                socket = server.accept();
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                line = in.readUTF();
                server.close();
                socket.close();
            } catch (IOException e) {
                //e.printStackTrace();
                try {
                    server.close();
                    socket.close();
                } catch (IOException ex) {
                    //ex.printStackTrace();
                }
                continue;
            }

            blockchain.add(new Block(line, blockchain.get(blockchain.size()-1).hash));
            System.out.println("Trying to Mine block " + i + "... ");
            blockchain.get(i++).mineBlock(difficulty);


            System.out.println("\nBlockchain is Valid: " + isChainValid());

            String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
            System.out.println(blockchain.get(0).data);


            System.out.println("\nThe block chain: ");
            System.out.println(blockchainJson);
        }

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
