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

    public void broadcast(String mess, String username) throws IOException {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMess(mess, username);
            }
        }
    }

    public void broadcastIMG(byte[] filebytes, String username) throws IOException {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendIMG(filebytes, username);
            }
        }
    }

    public void broadcastFile(byte[] filebytes, String username) throws IOException {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendFile(filebytes, username);
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

                        String username = dis.readUTF();
                        String text = dis.readUTF();
                        broadcast(text, username);
                        System.out.println("Received text: " + text);

                    } else if (messageType.equals("image")) {

                        String username = dis.readUTF();
                        int length = dis.readInt();
                        byte[] imageBytes = new byte[length];
                        dis.readFully(imageBytes);
                        broadcastIMG(imageBytes, username);
                        System.out.println("Received image: received_image.png");

                    } else if (messageType.equals("file")) {

                        String username = dis.readUTF();
                        String filename = dis.readUTF();
                        int length = dis.readInt();
                        byte[] fileBytes = new byte[length];
                        dis.readFully(fileBytes);
                        broadcast(filename, username);
                        System.out.println("Received file: " + filename);

                    }
                }
            } catch (IOException ex) {
                shutdown();
            }
        }

        public void sendMess(String mess, String username) throws IOException {
            dos.writeUTF("text");
            dos.writeUTF(username);
            dos.writeUTF(mess);
        }

        public void sendIMG(byte[] fileBytes, String username) throws IOException {
            dos.writeUTF("image");
            dos.writeUTF(username);
            dos.writeInt(fileBytes.length);
            dos.write(fileBytes);
        }

        public void sendFile(byte[] fileBytes, String username) throws IOException {
            dos.writeUTF("file");
            dos.writeUTF(username);
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
