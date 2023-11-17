import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    private static final int PORT = 6969;
    private static boolean running = true;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server is running...");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Arrêt du serveur...");
                running = false;
            }));
            while (running) {
                Socket socket = server.accept();
                System.out.println("Client connected");
                DataInputStream dis = new DataInputStream(socket.getInputStream());  
                String str = (String) dis.readUTF();
                System.out.println("Client says: " + str);
            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}