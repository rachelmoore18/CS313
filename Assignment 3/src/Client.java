


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;

public class Client {

    private Socket socket = null;

    public Client() throws IOException {
        System.out.println("Client Started");
        socket = new Socket("127.0.0.1", 5000);
    }

    public void SendCommandString(String cmd) throws IOException{
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject(cmd);
    }
    
    public void SendCommandObject(String command , String operands) throws IOException {
    	   
    	   ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
    	   Clientobject co = new Clientobject();
    	   co.setCommand(command);
    	   co.setOperand(operands);
    	   outStream.writeObject(co);
    	   
    	
    	
    }

    public void ClientReceive() throws IOException, ClassNotFoundException {
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        String serverResult = (String) inStream.readObject();
        System.out.println("Message Received: " + serverResult);
    }
    
    


    public static void main(String[] args){
        try {
            Client client = new Client();
            
            client.SendCommandObject("getFirstName", "John Smiths");
            client.ClientReceive();
        }catch(Exception e){e.printStackTrace();}
    }
    
    


}
