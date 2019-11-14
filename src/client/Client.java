package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int PUERTO = 1234;
    private final String HOST = "localhost";
    private Socket socket;
    private Scanner userInput;
    private Scanner in;
    private PrintWriter out;

    public Client() throws IOException {
        System.out.println("Initialising client...");
        socket = new Socket(HOST, PUERTO);
        this.userInput = new Scanner(System.in);
    }

    public void init() throws IOException {
        System.out.println("Client initialised!");

        this.userInput = new Scanner(System.in);
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream(), true);
        while (userInput.hasNextLine()) {
            out.println(userInput.nextLine());
            System.out.println(in.nextLine());
        }
    }
}