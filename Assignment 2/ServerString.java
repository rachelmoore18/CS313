import java.io.IOException;
import java.net.*;

public class ServerString {

    private DatagramSocket socket;
    private byte[] buffer;

    public ServerString() throws IOException {

        System.out.println("Waiting for client");

        socket = new DatagramSocket(5000);
        buffer = new byte[512];


        if(socket.isConnected()){
            System.out.println("Found client");
        }
    }

    public void ServerReceive() throws IOException, SocketException, NumberFormatException {
        DatagramPacket request1 = new DatagramPacket(buffer, buffer.length);
        socket.receive(request1);
        InetAddress clientAddress = request1.getAddress();
        int clientPort = request1.getPort();
        String messageOne = new String(request1.getData());

        System.out.println("Message Received: " + messageOne);

        String msg1 = messageOne.toUpperCase();
        DatagramPacket response1 = new DatagramPacket(msg1.getBytes(), msg1.getBytes().length, clientAddress, clientPort);
        socket.send(response1);

        socket.close();
    }

    public static void main(String[] args){
        try {
            ServerString server = new ServerString();

            while(true){
                server.ServerReceive();
            }
        }catch(Exception e){e.printStackTrace();}
    }


}
