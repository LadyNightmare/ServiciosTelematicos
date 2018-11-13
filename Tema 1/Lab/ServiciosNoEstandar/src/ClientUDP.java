import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {

	private static final int BUFFER_LENGTH = 2048;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if ((args.length < 1) || (args.length > 2)) {
			throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
		}
		try {
			
			// Server IP and port
			
			InetAddress serverAddress = InetAddress.getByName(args[0]); 
			int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 6789;
			
			System.out.println("We're connected to the server with IP " + serverAddress + " through the port " + servPort +
					"\nEnter your message:");

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String message=stdIn.readLine();
			
			DatagramSocket socket = new DatagramSocket();
			
			byte [] bytesToSend = new byte[BUFFER_LENGTH];
			byte [] bytesToRecevive = new byte[BUFFER_LENGTH];
			
			
			while (!message.equals(".")) {
				
				bytesToSend = message.getBytes();
				
				System.out.println(message);
				
				DatagramPacket sendPacket = new DatagramPacket(bytesToSend, BUFFER_LENGTH, serverAddress, servPort);
				socket.send(sendPacket);
				
				/*DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
				socket.receive(receivePacket);
				servPort = receivePacket.getPort();
				System.out.println("ECO:"+ new String(receivePacket.getData()));*/
				
				message=stdIn.readLine();
				
			}			
			
			socket.close();

		} catch (Exception e) {

			System.out.println("xd");
			System.out.println(e.getMessage());

		}

	}

}
