import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {

	private static final int PORT = 3000;
	private static final int BUFFER_LENGTH = 2048;

	public static void main(String[] args) {
		// TODO Auto-generated method stub		

		DatagramSocket socket = null;

		try {

			socket = new DatagramSocket(PORT);

			byte[] buffer = new byte[BUFFER_LENGTH];
			DatagramPacket receivedDatagram = new DatagramPacket(buffer, BUFFER_LENGTH);
			DatagramPacket sentDatagram = new DatagramPacket(buffer, BUFFER_LENGTH);

			
			System.out.println("Created socket, port: " + PORT);


			while (true) {
				
				
				

				socket.receive(receivedDatagram);
				System.out.println("Paquete recibido");
				
				(new ServerUDPImpl(receivedDatagram)).start();

				socket.send(sentDatagram);
				System.out.println("Paquete enviado");

			}
		} catch (IOException e) {

			System.err.println("I/O error: " + e.getMessage());

		} finally {

			if (socket != null) {

				socket.close();

			}

		}

	}
}
