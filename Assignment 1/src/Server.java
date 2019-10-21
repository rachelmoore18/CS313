//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server = new ServerSocket(5000);
    private Socket socket;

    public Server() throws IOException {
        System.out.println("Waiting for client");
        this.socket = this.server.accept();
        if (this.socket.isConnected()) {
            System.out.println("Found client");
        }

    }

    public void ServerReceive() throws IOException, ClassNotFoundException, NumberFormatException {
        ObjectInputStream streamI = new ObjectInputStream(this.socket.getInputStream());
        String messageOne = (String)streamI.readObject();
        String messageTwo = (String)streamI.readObject();
        System.out.println("Message Received: " + messageOne);
        System.out.println("Message Received: " + messageTwo);
        ObjectOutputStream streamO = new ObjectOutputStream(this.socket.getOutputStream());
        streamO.writeObject(messageOne.toUpperCase());
        int intMessage = Integer.parseInt(messageTwo);
        streamO.writeObject(intMessage * 2);
        streamI.close();
        streamO.close();
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();

            while(true) {
                server.ServerReceive();
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
}
