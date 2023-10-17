package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

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

    public void broadcast(String mess) throws IOException {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMess(mess);
            }
        }
    }

    public void broadcastIMG(byte[] filebytes) throws IOException {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendIMG(filebytes);
            }
        }
    }

    public void shutdown() {
        try {
            done = true;
            if (server != null) {
                if (!server.isClosed()) {
                    server.close();
                    for (ConnectionHandler ch : connections) {
                        ch.shutdown();
                    }
                }
            }
        } catch (IOException e) {
            // ignore
        }
    }

    class ConnectionHandler implements Runnable {

        private Socket client;
        private DataOutputStream dos;
        private DataInputStream dis;
        private String nickname;

        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {

                dos = new DataOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());

                while (true) {
                    String messageType = dis.readUTF();
                    if (messageType.equals("text")) {
                        String text = dis.readUTF();
                        broadcast(text);
                        System.out.println("Received text: " + text);
                    } else if (messageType.equals("image")) {
                        int length = dis.readInt();
                        byte[] imageBytes = new byte[length];
                        dis.readFully(imageBytes);
                        broadcastIMG(imageBytes);

                        System.out.println("Received image: received_image.png");
                    } else if (messageType.equals("file")) {
                        String filename = dis.readUTF();
                        int length = dis.readInt();
                        byte[] fileBytes = new byte[length];
                        dis.readFully(fileBytes);

                        FileOutputStream fos = new FileOutputStream(filename);
                        fos.write(fileBytes);
                        fos.close();

                        System.out.println("Received file: " + filename);
                    }
                }
            } catch (IOException ex) {
                shutdown();
            }
        }

        public void sendMess(String mess) throws IOException {
            dos.writeUTF("text");
            dos.writeUTF(mess);
        }

        public void sendIMG(byte[] fileBytes) throws IOException {
            dos.writeUTF("image");
            dos.writeInt(fileBytes.length);
            dos.write(fileBytes);
        }

        public void shutdown() {
            try {
                dos.close();
                dis.close();
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

}
