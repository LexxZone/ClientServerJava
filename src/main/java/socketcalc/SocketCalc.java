package socketcalc;

/**
 * 13.01.2019
 *
 * @author Alexey Dvoryaninov  ( lexxzone@gmail.com )
 */
public class SocketCalc {

    public static void main(String[] args) {
        if (args.length < 3 || args.length > 4) {
            System.out.println("Usage: \r\n" +
                    "java SocketCalc server 8000 /\r\n" +
                    "java SocketCalc client 127.0.0.1 8000 45 35");
            return;
        }
        SocketCalc socketCalc = new SocketCalc();
        if (args[0].equals("server")) {
            socketCalc.runServer(args[1], args[2]);
        }
        if (args[0].equals("client")) {
            socketCalc.runClient(args[1], args[2], args[3], args[4]);
        }
    }

    private void runServer(String port, String operation) {
        Phone phone = new Phone(port);
        System.out.println("## Server is started with " + operation + " on " + port + " port ##");
        while (true) {
            phone.accept();
            String a = phone.readLine();
            String b = phone.readLine();
            int result = calculate(operation, a, b);
            String message = a + " " + operation + " " + b + " = " + result;
            System.out.println("Accepted: " + message);
            phone.writeLine(message);
            phone.close();
        }
    }

    private void runClient(String ip, String port, String arg1, String arg2) {
        Phone phone = new Phone(ip, port);
        phone.writeLine(arg1);
        phone.writeLine(arg2);
        String answer = phone.readLine();
        System.out.println(answer);
        phone.close();

    }

    private int calculate(String operation, String a, String b) {
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        int calcResult = 0;
        switch (operation) {
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
            case "+":
            default:
                return x + y;
        }
    }
}
