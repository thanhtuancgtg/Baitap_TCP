import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DeMau1_Client {
    private static Scanner inFromServer;
    int port;
    String ip = "";

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Nhap dia chi IP: ");
            String IP = scan.nextLine();
            System.out.print("Nhap port: ");
            String Port = scan.nextLine();
            Socket client = new Socket(IP, Integer.parseInt(Port));
            System.out.println("Client da duoc tao");
            inFromServer = new Scanner(client.getInputStream());
            PrintStream outToServer = new PrintStream(client.getOutputStream());
            while (true) {
                System.out.println(
                        "Nhap chuc nang:\n1.Dao chuoi\n2.Viet hoa\n3.Dem tu(so luong tu)\n4.Dem Tu(So lan xuat hien)\n5.Pig Latin\nExit(write exit)");
                String text = scan.nextLine();
                outToServer.println(text);
                if (text.equals("exit")) {
                    System.out.println(inFromServer.nextLine());
                    break;
                } else {
                    System.out.println(inFromServer.nextLine());
                    outToServer.println(scan.nextLine());
                    System.out.println(inFromServer.nextLine());
                    scan.nextLine();
                }

            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}