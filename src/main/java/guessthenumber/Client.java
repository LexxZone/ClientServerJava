package guessthenumber;

import java.net.*;
import java.io.*;

/**
 * 13.01.2019
 *
 * @author Alexey Dvoryaninov  ( lexxzone@gmail.com )
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);
        BufferedWriter clientWriter = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader clientReader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        // Logic block ----

        // ------
        clientWriter.write(getHttpHeader() + 0);
        clientWriter.flush();
        //clientWriter.write(getHttpHeader());

        clientSocket.close();
    }

    public static String getHttpHeader() {
        String httpHeader = "HTTP/1.0 200 OK\nContent-Type: text/html; charset=UTF-8\n";
        return httpHeader;
    }
}
