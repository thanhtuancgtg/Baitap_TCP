
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.*;

public class tcpClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        Socket s = null;
        try {
            s = new Socket("localhost", 5678);
            System.out.println("client da duoc tao");

            // xu ly

            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            for (int i = '0'; i <= '9'; i++) {

                // goi
                os.write(i);

                // nhan
                int ch = is.read();
                System.out.println((char) ch);
                Thread.sleep(1000);
            }

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }

    }

}