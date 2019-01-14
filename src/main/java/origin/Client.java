package origin;

import java.net.*;
import java.io.*;

/**
 * 12.01.2019
 *
 * @author Alexey Dvoryaninov  ( lexxzone@gmail.com )
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("127.0.0.1", 8000);

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));


        writer.write("Get me some information ");
        writer.flush();
        System.out.println(reader.readLine());
        writer.close();
        reader.close();
        clientSocket.close();
    }

}
