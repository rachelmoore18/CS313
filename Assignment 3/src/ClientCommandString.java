import com.sun.security.ntlm.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCommandString {

    private Socket socket = null;

    public ClientCommandString() throws IOException {
        System.out.println("Client Started");
        socket = new Socket("127.0.0.1", 5000);
    }

    public void SendCommandString(String cmd) throws IOException{
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject(cmd);
    }

    public void ClientReceive() throws IOException, ClassNotFoundException {
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        String serverResult = (String) inStream.readObject();
        System.out.println("Message Received: " + serverResult);
    }


    public static void main(String[] args){
        try {
            ClientCommandString client = new ClientCommandString();
            client.SendCommandString("getFirstName John Smith");
            client.ClientReceive();
        }catch(Exception e){e.printStackTrace();}
    }


}
