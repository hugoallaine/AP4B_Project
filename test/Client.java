import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    
    private static final int PORT = 6969;
    private static final String HOSTNAME = "localhost";
    public static void main(String[] args) {
        try{
            Socket socket = new Socket(HOSTNAME, PORT);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("salam");
            dos.flush();
            dos.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}