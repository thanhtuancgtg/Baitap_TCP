package TCP;

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

                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                while (true) {

                    // nhận và in
                    int ch = is.read();
                    if (ch == -1)
                        break;
                    System.out.println((char) ch);

                    // gởi lại Client
                    os.write(ch);
                }
                s.close();

            }
        } catch (IOException e1) {
            // TODO: handle exception

        }

    }
}
