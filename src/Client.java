
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", 1234);

            // Send data
            out = new PrintWriter(client.getOutputStream(), true);
            // Get data
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            inputHandler inputHandler = new inputHandler();
            Thread t = new Thread(inputHandler);
            t.start();

            String inMess;
            while ((inMess = in.readLine()) != null) {
                System.out.println(inMess + " from server");
                // TODO: send the mess to text area
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            // ignore
        }
    }

    class inputHandler implements Runnable {

        @Override
        public void run() {
            try {
                // sysin -> text box .get text
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String mess = inReader.readLine();
                    if (mess.equals("/quit")) {
                        inReader.close();
                        shutdown();
                    } else {
                        out.println(mess);
                    }
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

//    public void sendMessage(String message) {
//        if (out != null) {
//            out.println(message);
//        }
//    }
//
//    class inputHandler implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
//                while (!done) {
//                    String mess = inReader.readLine();
//                    if (mess.equals("/quit")) {
//                        inReader.close();
//                        shutdown();
//                    } else {
//                        // Send the message to the server
//                        out.println(mess);
//                        // Update the chat window with the sent message
//                        frmChat.this.updateChat("You: " + mess);
//                    }
//                }
//            } catch (IOException e) {
//                shutdown();
//            }
//        }
//    }
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
