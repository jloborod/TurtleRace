package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int PORT = 1234;
    private final String HOST = "localhost";
    private Socket socket;
    private Scanner userInput;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() throws IOException {
        System.out.println("Initialising client...");
        this.socket = new Socket(HOST, PORT);
        this.userInput = new Scanner(System.in);
    }

    private String getUserInput() {
        return this.userInput.nextLine();
    }

    private String getServerInput() throws IOException {
        return this.in.readUTF();
    }

    private void sendToServer(String msg) throws IOException {
        this.out.writeUTF(msg);
    }

    private void menu() throws IOException {
        System.out.println("OPTIONS:");
        System.out.println("1. Add Turtle");
        System.out.println("2. Remove Turtle");
        System.out.println("3. Show Turtles");
        System.out.println("4. Start race");
        System.out.println("5. Exit");

        String option = this.getUserInput();

        switch (option) {
            case "1":
                this.sendToServer(option);
                System.out.print("Introduce turtle name: ");
                this.sendToServer(this.getUserInput());
                System.out.print("Introduce turtle dorsal: ");
                this.sendToServer(this.getUserInput());
                System.out.println(this.getServerInput());

                this.menu();
                break;
            case "2":
                this.sendToServer(option);
                System.out.print("Introduce turtle position: ");
                this.sendToServer(this.getUserInput());
                System.out.println(this.getServerInput());

                this.menu();
                break;
            case "3":
                this.sendToServer(option);
                System.out.println(this.getServerInput());

                this.menu();
                break;
            case "4":
                System.out.println("Coming soon...");

                this.menu();
                break;
            case "5":
                System.out.println("Exiting...");
                this.sendToServer(option);
                this.socket.close();

                break;
            default:
                System.out.println("Unknown option");

                this.menu();
                break;
        }
    }

    public void init() throws IOException {
        System.out.println("Client initialised!");

        this.userInput = new Scanner(System.in);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        this.menu();
    }
}