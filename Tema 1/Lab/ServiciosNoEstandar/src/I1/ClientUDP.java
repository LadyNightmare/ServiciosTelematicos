package I1;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ClientUDP {

	private static final int TIMEOUT = 3000;
	private static final int MAXTRIES = 6;
	private static int tries = 0;

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
		String mensaje = stdIn.readLine();;
		boolean receivedResponse = false;
		
		DatagramPacket sendPacket;
		DatagramPacket receivePacket;
		
		while(!mensaje.equals(".")) {
			
			do {
				
				byte[] bytesToSend = mensaje.getBytes();
				sendPacket = new DatagramPacket(bytesToSend, //Datagrama a enviar
						bytesToSend.length, serverAddress, servPort);
				receivePacket =
						//Datagrama a recibir
				new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
				
				socket.send(sendPacket);
				socket.setSoTimeout(TIMEOUT); // Temporizador para cada envio
				try {
				socket.receive(receivePacket);
				receivedResponse = true;
				} catch (SocketTimeoutException e) { // Expiro el temporizador
				tries += 1;
				System.out.println("Timeout, "+(MAXTRIES - tries)+" mas");
				}
				} while ((!receivedResponse) && (tries < MAXTRIES));
			
			if (receivedResponse) {
				System.out.println("Received: " + new String(
				receivePacket.getData()));
				socket.setSoTimeout(0);
				} else {
				System.out.println("No hubo respuesta del servidor.");
				}
			
			mensaje=stdIn.readLine();
			
		}
		
		System.out.println("Cerrando conexión.");
		socket.close();

	}
}