
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {

        InetAddress addressOfTheServer = InetAddress.getByName("127.0.0.1");

        Socket client = new Socket(addressOfTheServer, 3000);
        System.out.println("Client is created: ");

        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter outputStream = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

        Thread messagePrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String messageFromServer = inputFromServer.readLine();
                        if (messageFromServer != null) {
                            System.out.println("Message from the server: " + messageFromServer);
                        }
                        if (messageFromServer.equals("exit")) {
                            client.close();
                            break;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        messagePrinter.start();

        while (true) {

            String line = keyboardInput.readLine();

            outputStream.println(line);
            outputStream.flush();

        }

    }
}
