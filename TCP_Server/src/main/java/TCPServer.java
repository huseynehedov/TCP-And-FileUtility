import lombok.SneakyThrows;
import util.FileUtility;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class TCPServer {
    public static void main(String[] args){
        readAsBytes();
    }

    @SneakyThrows
    public static void readAsBytes(){
        ServerSocket outFirstServerSocket = new ServerSocket(6789);
        while (true){
            System.out.println("Yeni socket ucun veya basqa sozle desek yeni musteri ucun gozleyirem...");
            Socket connection = outFirstServerSocket.accept();
            System.out.println("Yeni musteri geldi...");
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            BufferedInputStream inFromClient = new BufferedInputStream(connection.getInputStream());
            while (true){
                int b = inFromClient.read();
                if (b == -1){
                    break;
                }
                buffer.put((byte) b);
            }
            FileUtility.writeBytes(buffer.array(), "C:\\Users\\Techno Clinic\\Desktop\\aze.png");
        }
    }

    @SneakyThrows
    public byte[] readMessage(DataInputStream din){
        int msgLen = din.readInt();
        byte[] message = new byte[msgLen];
        din.readFully(message);
        return message;
    }

    @SneakyThrows
    public static void readAsString(){
        ServerSocket outFirstServerSocket = new ServerSocket(6789);
        while (true) {
            System.out.println("Yeni socket ucun veya basqa sozle desek yeni musteri ucun gozleyirem...");
            Socket connection = outFirstServerSocket.accept();
            System.out.println("Yeni musteri geldi...");
            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);
            String messageFromClient = br.readLine();
            System.out.println("Message From Client: " + messageFromClient);
        }
    }
}
