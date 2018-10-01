import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if ((args.length < 1) || (args.length > 2)) {
			throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
		}
		try {
			
			// Server IP and port
			
			InetAddress serverAddress = InetAddress.getByName(args[0]); 
			int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 6789;
			
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String mensaje=stdIn.readLine();
			byte[] bytesToSend = mensaje.getBytes();
			DatagramSocket socket = new DatagramSocket();

			//Data to send

			DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, servPort);
			socket.send(sendPacket);
			
			//Data to be received
			
			DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
			socket.receive(receivePacket); 
			System.out.println("ECO:"+ new String(receivePacket.getData()));
			socket.close();

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

}
