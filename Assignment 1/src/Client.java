import java.io.IOException;
import java.net.InetAddress;
import java.net.*;

public class Client {

    String hostname = "127.0.0.1";
    int port = 5000;

    private DatagramSocket socket = null;
    private InetAddress IPAddress = null;
    private byte[] buffer = null;



    public Client() throws IOException, SocketTimeoutException, InterruptedException {
        System.out.println("Client Started");
        IPAddress = InetAddress.getByName(hostname);
        socket = new DatagramSocket();
        buffer = new byte[512];
    }

    public void SendPacket() throws IOException {
        String First = "text";
        DatagramPacket request1 = new DatagramPacket( First.getBytes(), First.getBytes().length, IPAddress, port);
        socket.send(request1);

    }

    public void ClientReceive() throws IOException, ClassNotFoundException {
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);
        String messageOne = new String(buffer, 0, response.getLength());

        System.out.println("Message Received: " + messageOne);
    }


    public static void main(String[] args){
        try {
            Client client = new Client();
            client.SendPacket();
            client.ClientReceive();
        }catch(Exception e){e.printStackTrace();}
    }


}
