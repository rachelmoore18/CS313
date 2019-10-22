import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommandString {

    private ServerSocket server;
    private Socket socket;

    public ServerCommandString() throws IOException {
        server = new ServerSocket(5000);
        System.out.println("Waiting for client");

        socket = server.accept();

        if(socket.isConnected()){
            System.out.println("Found client");
        }
    }

    public void ServerReceive() throws IOException, ClassNotFoundException, NumberFormatException {

        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            String command = (String) inStream.readObject();
            System.out.println("Received Command: " + command);

            String[] commandInfo = command.split(" ");


            // Process Command
            switch (commandInfo[0]) {
                case "getFirstName":
                    Send(commandInfo[1]);
                    break;
                case "getLastName":
                    Send(commandInfo[2]);
                    break;
                case "getUpperCase":
                    Send(commandInfo[1].toUpperCase());
                    break;
                case "getLowerCase":
                    Send(commandInfo[1].toLowerCase());
                    break;
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void Send(String data)
    {
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(data);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            ServerCommandString server = new ServerCommandString();

            server.ServerReceive();
        }catch(Exception e){}
    }


}
