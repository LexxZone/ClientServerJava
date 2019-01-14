package socketcalc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 13.01.2019
 *
 * @author Alexey Dvoryaninov  ( lexxzone@gmail.com )
 */
public class Phone {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Phone(String port) {
        try {
            serverSocket = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public Phone(String ip, String port) {
        try {
            clientSocket = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException e) {
            System.err.print(e);
        }

    }

    public void accept() {
        try {
            clientSocket = serverSocket.accept();
            createStreams();
        } catch (IOException e) {
            System.err.print(e);

        }
    }

    void createStreams() {

        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.print(e);
        }
        return "";

    }

    public void writeLine(String message) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            System.err.print(e);
    }
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            serverSocket.close();
    } catch (IOException e) {
            System.err.print(e);
    }
    }
}
