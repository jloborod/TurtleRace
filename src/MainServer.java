import server.Server;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.init();
    }
}
