
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class tcpServer {
    public static int serverport = 5678;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(serverport);
            System.out.println("Server da duoc tao " + serverport);
            while (true) {
                Socket s = ss.accept();
                // xu ly
                luongxuly xl = new luongxuly(s);
                xl.start();

            }
        } catch (IOException e1) {
            // TODO: handle exception

        }

    }
}
