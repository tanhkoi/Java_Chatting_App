
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;

    public Server() {
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(1234);
            pool = Executors.newCachedThreadPool();
            while (!done) {
                Socket client = server.accept();
               
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (IOException ex) {
            shutdown();
        }
    }

    public void broadcast(String mess) {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMess(mess);
            }
        }
    }

    public void shutdown() {
        try {
            done = true;
            if (!server.isClosed()) {
                server.close();
                for (ConnectionHandler ch : connections) {
                    ch.shutdown();
                }
            }
        } catch (IOException e) {
            // ignore
        }
    }

    class ConnectionHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;

        public ConnectionHandler(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            try {

                // Send data to client
                out = new PrintWriter(client.getOutputStream(), true);
                // Get data from client
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                // TODO: check all cases
                nickname = in.readLine();
                System.out.println(nickname + " da ket noi!");
                broadcast(nickname + " tham gia doan chat!");

                String mess;
                while ((mess = in.readLine()) != null) {
                    if (mess.startsWith("/quit")) {
                        broadcast(nickname + " da thoat group chat!");
                    } else {
                        broadcast(nickname + ": " + mess);
                    }
                }
            } catch (IOException ex) {
                shutdown();
            }
        }

        public void sendMess(String mess) {
            out.println(mess);
        }

        public void shutdown() {
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

//        @Override
//        public void run() {
//            try {
//                
//                // Send data to client
//                out = new PrintWriter(client.getOutputStream(), true);
//                // Get data from client
//                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//                out.println("Nhap mot nickname: ");
//                nickname = in.readLine(); // TODO: check all cases
//
//                System.out.println(nickname + " da ket noi!");
//                broadcast(nickname + " tham gia doan chat!");
//
//                String mess;
//                while ((mess = in.readLine()) != null) {
//                    if (mess.startsWith("/nick ")) {
//                        String[] messSplit = mess.split(" ", 2);
//                        if (messSplit.length == 2) {
//                            broadcast(nickname + " thay doi nickname cua ho thanh " + messSplit[1]);
//                            System.out.println(nickname + " thay doi nickname cua ho thanh " + messSplit[1]);
//                            nickname = messSplit[1];
//                            out.println("Thay doi thanh cong: " + nickname);
//                        } else {
//                            out.println("Nickname khong hop le!");
//                        }
//                    } else if (mess.startsWith("/quit")) {
//                        broadcast(nickname + " roi doan chat!");
//                    } else {
//                        //
//                        broadcast(nickname + ": " + mess);
//                    }
//                }
//            } catch (IOException ex) {
//                shutdown();
//            }
//        }
}
