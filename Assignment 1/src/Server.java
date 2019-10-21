import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket socket;

    public Server() throws IOException {
        server = new ServerSocket(5000);
        System.out.println("Waiting for client");

        socket = server.accept();

        if(socket.isConnected()){
            System.out.println("Found client");
        }
    }

    public void ServerReceive() throws IOException, ClassNotFoundException, NumberFormatException {
        ObjectInputStream streamI = new ObjectInputStream(socket.getInputStream());
        String messageOne = (String) streamI.readObject();
        String messageTwo = (String) streamI.readObject();
        System.out.println("Message Received: " + messageOne);
        System.out.println("Message Received: " + messageTwo);

        ObjectOutputStream streamO = new ObjectOutputStream(socket.getOutputStream());
        streamO.writeObject(messageOne.toUpperCase());
        int intMessage = Integer.parseInt(messageTwo);
        streamO.writeObject(intMessage*2);

        streamI.close();
        streamO.close();
    }

    public static void main(String[] args){
        try {
            Server server = new Server();

            while(true){
                server.ServerReceive();
            }
        }catch(Exception e){e.printStackTrace();}
    }


}
