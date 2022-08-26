
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class luongxuly extends Thread {
    Socket s;

    public luongxuly(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
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
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
