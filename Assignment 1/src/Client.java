import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private Socket socket = null;

    public Client() throws IOException {
        System.out.println("Client Started");
        socket = new Socket("127.0.0.1", 5000);
    }

    public void SendObject(Object first, Object second) throws IOException{
        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        oStream.writeObject(first);
        oStream.writeObject(second);
    }

    public void ClientReceive() throws IOException, ClassNotFoundException {
        ObjectInputStream streamI = new ObjectInputStream(socket.getInputStream());
        String messageOne = (String) streamI.readObject();
        Integer messageTwo = (Integer) streamI.readObject();
        System.out.println("Message Received: " + messageOne);
        System.out.println("Message Received: " + messageTwo);
    }


    public static void main(String[] args){
        try {
            Client client = new Client();
            client.SendObject("text", "220");
            client.ClientReceive();
        }catch(Exception e){e.printStackTrace();}
    }


}
