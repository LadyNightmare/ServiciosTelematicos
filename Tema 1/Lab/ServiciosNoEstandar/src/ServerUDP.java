import java.io.*;
import java.net.*;

public class ServerUDP {

	public static void main(String args[]) throws Exception {

		System.out.println("Servidor iniciado.");

		int port = 2510;

		if (args.length > 1) {
			throw new IllegalArgumentException("Parameter: [<Port>]");
		} else if (args.length > 0) {

			port = Integer.parseInt(args[0]);
			System.out.println("Vamos a usar el puerto " + port);

		}

		DatagramSocket socketServidor = null;

		try {

			socketServidor = new DatagramSocket(port);
			System.out.println("Socket inicializado: " + socketServidor.getLocalPort() + " " + socketServidor.getInetAddress());

		} catch (IOException e) {

			System.out.println("Error al crear el objeto socket servidor");
			System.exit(0);

		}

		byte [] recibirDatos = new byte[8192];
		byte [] enviarDatos = new byte[8192];

		while(true) {

			DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);

			try {        

				socketServidor.receive(recibirPaquete);

			} catch (IOException e) {

				System.out.println("Error al recibir");
				e.printStackTrace();
				socketServidor.close();
				System.exit(0);

			}

			String frase = new String(recibirPaquete.getData());

			InetAddress DireccionIP = recibirPaquete.getAddress();
			int puerto = recibirPaquete.getPort();

			enviarDatos = frase.getBytes();

			DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, DireccionIP, puerto);

			try {       

				socketServidor.send(enviarPaquete);
				socketServidor.close();

			} catch (IOException e) {

				System.out.println("Error al recibir23");
				socketServidor.close();
				System.exit(0);

			}
		}


	}
}
