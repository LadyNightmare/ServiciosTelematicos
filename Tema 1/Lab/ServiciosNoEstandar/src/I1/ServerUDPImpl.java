package I1;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDPImpl extends Thread {

	private byte [] data;
	private InetAddress address;
	private int port;
	
	public ServerUDPImpl (DatagramPacket dp) {
		
		this.data = dp.getData();
		this.address = dp.getAddress();
		this.port = dp.getPort();
		
	}
	
	public void run () {
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			
			if (!(new String (packet.getData()).equals("."))) {
				
				socket.send(packet);
				
			}
			
			socket.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
	}

}
