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
    private Race race;

    public Server() throws IOException {
        System.out.println("Server initialising...");
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for client...");
    }

    private String getClientInput() {
        return this.in.nextLine();
    }

    private void sendToClient(String msg) {
        this.out.println(msg);
    }

    public void init() throws IOException {
        while (true) {
            this.socket = this.serverSocket.accept();
            System.out.println("Client connected...");

            try {
                this.in = new Scanner(this.socket.getInputStream());
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.race = new Race();

                String msg;
                while (this.in.hasNextLine()) {
                    msg = this.getClientInput();

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
                            sendToClient(Integer.toString(this.race.getTurtles()));
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