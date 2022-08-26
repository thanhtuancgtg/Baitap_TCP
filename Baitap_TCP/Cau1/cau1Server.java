import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class cau1Server {

    private static ServerSocket serverSocket = null;

    public static void main(String[] args) throws IOException {
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            serverSocket = new ServerSocket(1234);
            System.out.print("Server created........ \n");
            Socket clientSocket = null;
            clientSocket = serverSocket.accept();
            dos = new DataOutputStream(clientSocket.getOutputStream());
            dis = new DataInputStream(clientSocket.getInputStream());
            String inline = "";
            while (!inline.equals("End")) {
                inline = dis.readUTF();
                char ch[] = inline.toCharArray();
                if (Character.isDigit(ch[0])) {
                    int i = Integer.parseInt(inline);
                    switch (i) {
                        case 0:
                            inline = "Zero";
                            break;
                        case 1:
                            inline = "One";
                            break;
                        case 2:
                            inline = "Two";
                            break;
                        case 3:
                            inline = "Three";
                            break;
                        case 4:
                            inline = "Four";
                            break;
                        case 5:
                            inline = "Five";
                            break;
                        case 6:
                            inline = "Six";
                            break;
                        case 7:
                            inline = "Sevent";
                            break;
                        case 8:
                            inline = "Eight";
                            break;
                        case 9:
                            inline = "Nine";
                            break;
                        default:
                            inline = "No integer";
                            break;
                    }
                    // gửi về cho client
                    dos.writeUTF(inline);
                }
                System.out.println("Received  Client : " + inline);
            }
        } catch (Exception e) {
            dos.close();
            serverSocket.close();
            dis.close();
            System.out.print(e.getMessage());
        }
    }
}