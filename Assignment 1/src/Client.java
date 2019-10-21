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

    public void SendObject(Object obj) throws IOException{
        ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
        oStream.writeObject(obj);
    }

    public void ClientReceive() throws IOException, ClassNotFoundException {
        ObjectInputStream streamI = new ObjectInputStream(socket.getInputStream());
        String message = (String) streamI.readObject();
        System.out.println("Message Received: " + message);
    }


    public static void main(String[] args){
        try {
            Client client = new Client();
            client.SendObject("text");
            client.ClientReceive();
        }catch(Exception e){e.printStackTrace();}
    }


}
