import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

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

        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            
            if (inStream.getClass().equals(String.class)) {
            String command = (String) inStream.readObject();
            } else {
            	Object obj = inStream.readObject();
            	Clientobject co = Fagge(obj);
            }
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

    
    public Clientobject objectSet(Object obj) {
    	
    		Clientobject obj = (Clientobject) obj;
    		String result = completeObject(obj.getCommand(),obj.getOperand());
    		obj.setResult(result);
    		return obj;
    	
    	
    	
    }
    
    public String completeObject(String command , List<String> operand) {
    	
    		switch(command) {
            case "getFirstName":
                Send (operand.get(1));
                break;
            case "getLastName":
                Send(operand.get(2));
                break;
            case "getUpperCase":
                Send(operand.get(3));
                break;
            case "getLowerCase":
                Send(operand.get(4));
                break;
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
            Server server = new Server();

            server.ServerReceive();
        }catch(Exception e){}
    }


}
