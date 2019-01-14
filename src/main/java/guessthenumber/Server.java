package guessthenumber;

import java.net.*;
import java.io.*;

/**
 * 13.01.2019
 *
 * @author Alexey Dvoryaninov  ( lexxzone@gmail.com )
 */
public class Server {
    public void setSecretCode(int secretCode) {
        this.secretCode = secretCode;
    }

    private int secretCode = 74;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Server server = new Server();
            Socket clientServerSocket = serverSocket.accept();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(clientServerSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientServerSocket.getInputStream()));
            //String request = reader.readLine();

            // Logic block ----
            Thread.sleep(1000);
            String regex = "[0-9]+";
            String response = null;
            boolean checker = true;
            boolean responseGen = false;
            String lineContent = "";
            while (checker) {
                lineContent = reader.readLine();
                if (lineContent != null && lineContent.matches(regex)) {
                    System.out.println();
                    responseGen = true;
                    checker = false;
                }
            }
            if (responseGen) {
                int clientAnswer = Integer.parseInt(lineContent);
                if (clientAnswer == server.secretCode) {
                    response = "Это был верный ответ! Серверное число (" + server.secretCode +
                            ") угадано !\r\nЕсли захотите сыграть еще, то серверное чило изменено";
                    // логика по изменению загаданного числа
                } else if (clientAnswer < server.secretCode) {
                    response = "Серверное число больше нежели ваше предположение!";
                } else if (clientAnswer > server.secretCode) {
                    response = "Серверное число меньше нежели ваше предположение!";
                } else {
                    response = "Ошибочка...";
                }
            }
            if (response == null) {
                response = "Неправильный формат запроса или внутренняя ошибка";
            }

            // ------

            //String response;
            writer.write(response);
            writer.newLine();
            writer.newLine();
            writer.flush();

            writer.close();
            reader.close();
            clientServerSocket.close();
        }

        //serverSocket.close();
    }
}
