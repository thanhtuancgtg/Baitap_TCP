package Simple;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 9540;

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            System.out.println("Server is created: ");

            Socket s = ss.accept();
            System.out.println("Client connected to server ");

            String remoteIP = s.getInetAddress().getHostAddress();
            String remotePort = ":" + s.getLocalPort();

            PrintStream welcome = new PrintStream(s.getOutputStream());
            welcome.println("Hi, say something!!!");

            String msg_in = "", msg_out = "";
            while (!msg_in.equals("bye") && !msg_out.equals("bye")) {
                Scanner inFromClient = new Scanner(s.getInputStream());
                msg_in = inFromClient.nextLine();
                System.out.println("Client: " + msg_in);

                PrintStream outToClient = new PrintStream(s.getOutputStream());
                Scanner scan = new Scanner(System.in);
                msg_out = scan.nextLine();
                outToClient.println(msg_out);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
