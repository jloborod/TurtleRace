package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int PORT = 1234;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Race race;

    public Server() throws IOException {
        System.out.println("Server initialising...");
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for client...");
    }

    private String getClientInput() throws IOException {
        return this.in.readUTF();
    }

    private void sendToClient(String msg) throws IOException {
        this.out.writeUTF(msg);
    }

    public void init() throws IOException {
        while (true) {
            this.socket = this.serverSocket.accept();
            System.out.println("Client connected...");

            try {
                this.in = new DataInputStream(this.socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());
                this.race = new Race();

                while (true) {
                    String msg = this.getClientInput();

                    switch (msg) {
                        case "1":
                            String name = this.getClientInput();
                            String dorsal = this.getClientInput();
                            Turtle turtle = new Turtle(name, dorsal);
                            System.out.println("Adding turtle");
                            this.race.addTurtle(turtle);
                            System.out.println("Turtle " + name + " with dorsal " + dorsal + " created! Sending message to the client");
                            sendToClient("Turtle " + name + " with dorsal " + dorsal + " has been successfully created!");
                            break;
                        case "2":
                            Integer position = Integer.parseInt(this.getClientInput());
                            this.race.removeTurtle(position);
                            System.out.println("Turtle removed. Sending message to the client");
                            sendToClient("Turtle successfully removed");
                            break;
                        case "3":
                            System.out.println("Sending turtles to the client");
                            sendToClient(this.race.getTurtles());
                            break;
                        case "5":
                            System.out.println("Terminating socket connection");
                            break;
                        default:
                            System.out.println("Unknown");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try {
                    this.socket.close();
                    this.serverSocket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}