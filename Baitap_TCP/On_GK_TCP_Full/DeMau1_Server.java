
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DeMau1_Server {
    public static int count(String s) {
        int i = 0, count = 1;
        char[] s1 = s.toCharArray();

        while (s1[i] == ' ') {
            i++;
        }

        for (int j = i; j < s1.length - 1; j++) {
            if (s1[j] == ' ' && s1[j + 1] != ' ') {
                count++;
            }
        }

        return count;
    }

    public static Boolean isVowel(char c) {

        if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' ||
                c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U')

            return true;

        return false;
    }

    public static String findPigLatin(String input) {

        int size = input.length();
        String pigL = "";
        int i;

        for (i = 0; i < size; i++) {
            if (isVowel(input.charAt(i))) {

                break;
            }
        }

        if (i == size) {

            return "";
        }
        if (isVowel(input.charAt(0))) {
            pigL = input.substring(i);
            pigL = pigL + "way";
        } else {
            pigL = input.substring(i);

            pigL = pigL + input.substring(0, i - 0);

            pigL = pigL + "ay";

        }

        return pigL;
    }

    static int countOccurrences(String str, String word) {

        String a[] = str.split(" ");

        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (word.equals(a[i]))
                count++;
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket server = new ServerSocket(7);
            System.out.println("Server da duoc tao!! tai Dang cho client tai" + server.getInetAddress().getHostAddress()
                    + server.getLocalPort());
            Socket client = server.accept();
            System.out.println("Client da ket noi den server tai " + client.getRemoteSocketAddress());
            String remoteIP = client.getInetAddress().getHostAddress();
            String remotePort = ":" + client.getLocalPort();
            System.out.println("remoteIP: " + remoteIP + "/remotePort " + remotePort);

            PrintStream outToClient = new PrintStream(client.getOutputStream());
            Scanner inFromClient = new Scanner(client.getInputStream());
            while (true) {
                String Choose = inFromClient.nextLine();
                switch (Choose) {
                    case "1": {
                        outToClient.println("Nhap chuoi:");
                        String str = inFromClient.nextLine();
                        String str1 = new StringBuffer(str).reverse().toString();
                        outToClient.println("Server result : " + str1);
                        break;
                    }
                    case "2": {
                        outToClient.println("Nhap chuoi:");
                        String str = inFromClient.nextLine();
                        // outToClient.println("Server result : " + str.toUpperCase());
                        char[] charArray = str.toCharArray();
                        boolean foundSpace = true;
                        // s??? d???ng v??ng l???p for ????? duy???t c??c ph???n t??? trong charArray
                        for (int i = 0; i < charArray.length; i++) {
                            // n???u ph???n t??? trong m???ng l?? m???t ch??? c??i
                            if (Character.isLetter(charArray[i])) {
                                // ki???m tra kho???ng tr???ng c?? tr?????c ch??? c??i
                                if (foundSpace) {
                                    // ?????i ch??? c??i th??nh ch??? in hoa b???ng ph????ng th???c toUpperCase()
                                    charArray[i] = Character.toUpperCase(charArray[i]);
                                    foundSpace = false;
                                }
                            } else {
                                foundSpace = true;
                            }
                        }
                        // chuy???n ?????i m???ng char th??nh string
                        str = String.valueOf(charArray);
                        outToClient.println("Server result: " + str);
                        break;
                    }
                    case "3": {
                        outToClient.println("Nhap chuoi:");
                        String str = inFromClient.nextLine();
                        outToClient.println("Server result : " + count(str));
                        break;
                    }
                    case "4": {
                        outToClient.println("Nhap chuoi:");
                        String str = inFromClient.nextLine();
                        outToClient.println("Nhap tu can dem:");
                        String kyTu;
                        kyTu = inFromClient.nextLine();
                        // int count = 0;
                        // // duy???t t??? ?????u ?????n cu???i chu???i
                        // for (int i = 0; i < str.length(); i++) {
                        // // N???u k?? t??? t???i v??? tr?? th??? i b???ng 'a' th?? t??ng count l??n 1
                        // if (str.charAt(i) == kyTu.charAt(i)) {
                        // count++;
                        // }
                        // }
                        outToClient.println(
                                "Server result : So lan xuat hien cua tu vua nhap la" + countOccurrences(str, kyTu));
                        break;

                    }
                    case "5": {
                        outToClient.println("Nhap Mang:");
                        String str = inFromClient.nextLine();
                        String str1 = findPigLatin(str);
                        String kq;
                        if (str.equals("")) {
                            kq = str;
                        } else {
                            kq = str1;
                        }
                        outToClient.println("Server result : " + kq);

                    }
                    case "exit": {
                        outToClient.println("Goodbye!!!");
                        break;
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}