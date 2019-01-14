package origin; /**
 * 12.01.2019
 *
 * @author Alexey Dvoryaninov  ( lexxzone@gmail.com )
 */

import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        int count = 0;
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server is started...");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client accepted " + (++count));
            ;
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String request = reader.readLine();
            Thread.sleep(3000);
            String response = "Client #" + count + ", your request's length is " + request.length();
            writer.write(response);
            writer.newLine();
            writer.flush();

            reader.close();
            writer.close();
            clientSocket.close();
        }
//        serverSocket.close();

    }

}
