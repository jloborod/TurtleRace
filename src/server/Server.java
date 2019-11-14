package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private final int PORT = 1234;
    private ServerSocket serverSocket;
    private Socket socket;

    public Server() throws IOException {
        System.out.println("Server initialising...");
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for client...");
    }

    public void init() throws IOException {
        while (true) {
            this.socket = this.serverSocket.accept();
            System.out.println("Client connected...");

            try {
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine()) {
                    String msg = in.nextLine();
                    System.out.println(msg + " <- from client");
                    out.println(msg + " <- from server :)");
                }
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}