import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class cau1Client {
    public static void main(String[] args) throws IOException {
        Socket mySocket = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            mySocket = new Socket("localhost", 1234);
            dos = new DataOutputStream(mySocket.getOutputStream());
            dis = new DataInputStream(mySocket.getInputStream());
            Scanner input = new Scanner(System.in);
            String s = "";
            while (!s.equals("End")) {
                System.out.print("\nInput data : ");
                s = input.nextLine();
                dos.writeUTF(s);
                String str = dis.readUTF();
                System.out.print("Result from Server : " + str);
            }
        } catch (Exception e) {
            System.out.print("Disconnect");
            dis.close();
            dos.close();
            mySocket.close();
            e.printStackTrace();
        }
    }
}