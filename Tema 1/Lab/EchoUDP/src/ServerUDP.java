import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatagramSocket socket = null;

		try {
			InetAddress localAddr = InetAddress.getByName("localhost");
			int port = 3000;
			socket = new DatagramSocket(port, localAddr);
			byte[] buffer = new byte[2048];
			DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
			while (true) {
				
				socket.receive(datagram);
				System.out.println("Nueva peticion de servicio");
				
				// We start a thread for the current request
				
				ServerUDPImpl serverThread = new ServerUDPImpl(datagram);
				serverThread.start();
				
			}
		} catch (IOException e) {
			
			System.err.println("Error E/S en: " + e.getMessage());

		} finally {
			
			if (socket != null)
				socket.close();
			
		}

	}
}
