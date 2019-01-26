package I1;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.*;
import java.net.*;

public class ServerUDP {

	public static void main(String args[]) throws Exception {

		System.out.println("Servidor iniciado.");

		int port = 54321;
		InetAddress localAddr = InetAddress.getByName("localhost");
		DatagramSocket ds;
		ds = new DatagramSocket(port, localAddr);

		if (args.length > 1) {
			throw new IllegalArgumentException("Parameter: [<Port>]");
		} else if (args.length > 0) {

			port = Integer.parseInt(args[0]);
			System.out.println("Vamos a usar el puerto " + port);

		}

		try {
			byte[] buffer = new byte[2048];
			DatagramPacket datagram = new DatagramPacket(buffer,
					buffer.length);
			while (true) {
				ds.receive(datagram);
				System.out.println("Nueva peticion de servicio");
				// Inicio de una hebra para la peticion actual
				(new ServerUDPImpl(datagram)).start();
			}
		} catch (IOException e) {
			System.err.println("Error E/S en: " + e.getMessage());
		} finally {
				if (ds != null)
					ds.close();
			}
		
	}
}
