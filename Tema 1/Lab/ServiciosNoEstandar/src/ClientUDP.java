import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {

	private static final int TIMEOUT = 3000;
	private static final int MAXTRIES = 6;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		if ((args.length < 1) || (args.length > 2)) {
			throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
		}
		InetAddress serverAddress = InetAddress.getByName(args[0]); // IP Servidor
		int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 6789;
		
		System.out.println("Cliente iniciado. Conectándose a "+ serverAddress.getHostAddress());
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket socket = new DatagramSocket();
		String mensaje=stdIn.readLine();
		while(!mensaje.equals(".")) {	
			byte[] bytesToSend = mensaje.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(bytesToSend, //Datagrama a enviar
					bytesToSend.length, serverAddress, servPort);
			socket.send(sendPacket);
			DatagramPacket receivePacket =
					//Datagrama a recibir
			new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
			socket.receive(receivePacket); // Podria no llegar nunca el datagrama de ECO
			System.out.println("ECO:"+ new String(receivePacket.getData()));
			mensaje=stdIn.readLine();
		}
		
		System.out.println("Cerrando conexión.");
		socket.close();

	}
}