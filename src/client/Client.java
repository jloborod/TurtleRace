package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int PORT = 1234;
    private final String HOST = "localhost";
    private Socket socket;
    private Scanner userInput;
    private Scanner in;
    private PrintWriter out;

    public Client() throws IOException {
        System.out.println("Initialising client...");
        this.socket = new Socket(HOST, PORT);
        this.userInput = new Scanner(System.in);
    }

    private String getUserInput() {
        return this.userInput.nextLine();
    }

    private String getServerInput() {
        return this.in.nextLine();
    }

    private void sendToServer(String msg) {
        this.out.println(msg);
    }

    private void menu() {
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
                this.sendToServer(this.getUserInput());
                this.sendToServer(this.getUserInput());
                System.out.println(this.getServerInput());
                break;
            default:
                System.out.println("Uknown");
        }
        this.menu();
    }

    public void init() throws IOException {
        System.out.println("Client initialised!");

        this.userInput = new Scanner(System.in);
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream(), true);

        this.menu();

//        while (this.userInput.hasNextLine()) {
//            this.sendToServer(this.getUserInput());
//            System.out.println(this.getServerInput());
//        }
    }
}