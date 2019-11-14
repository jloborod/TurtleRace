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
    private Scanner in;
    private PrintWriter out;

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
                this.in = new Scanner(this.socket.getInputStream());
                this.out = new PrintWriter(socket.getOutputStream(), true);
                while (this.in.hasNextLine()) {
                    String msg = this.in.nextLine();
                    System.out.println(msg);
                    this.out.println(msg);
                }
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try {
                    this.socket.close();
                    this.serverSocket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}