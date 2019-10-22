import java.io.IOException;
import java.net.*;

public class Server2 {

    private DatagramSocket socket;
    private byte[] buffer;

    public Server2() throws IOException {

        System.out.println("Waiting for client");

        socket = new DatagramSocket(5000);
        buffer = new byte[512];


        if(socket.isConnected()){
            System.out.println("Found client");
        }
    }

    public void ServerReceive() throws IOException, SocketException, NumberFormatException {
        DatagramPacket request2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(request2);
        InetAddress clientAddress2 = request2.getAddress();
        int clientPort2 = request2.getPort();
        Integer messageTwo = Integer.parseInt(new String(request2.getData()).trim());

        System.out.println("Message Received: " + messageTwo);

        int msg2 = (messageTwo)*2;
        String msgTwo = Integer.toString(msg2);
        DatagramPacket response2 = new DatagramPacket(msgTwo.getBytes(), msgTwo.getBytes().length, clientAddress2, clientPort2);
        socket.send(response2);

        socket.close();
    }

    public static void main(String[] args){
        try {
            Server2 server = new Server2();

            while(true){
                server.ServerReceive();
            }
        }catch(Exception e){e.printStackTrace();}
    }


}
