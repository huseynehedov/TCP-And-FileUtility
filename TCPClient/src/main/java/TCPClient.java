import util.FileUtility;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("192.168.1.106", 6789);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] bytes = FileUtility.readBytes("C:\\Users\\Techno Clinic\\Pictures\\Camera Roll\\aze.png");
        outputStream.write(bytes);
        socket.close();
    }
}
