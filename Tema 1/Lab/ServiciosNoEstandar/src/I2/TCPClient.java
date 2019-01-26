package I2;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

	private static final int MAXT = 6;

	public static void main(String[] args) throws IOException {
		String mensaje;
		byte[] env;
		byte rec[];
		String sal = null;
		Scanner sc;

		Socket socket = null;

		socket = new Socket("localhost", 12345);
		
		System.out.println("java Client ");
		System.out.println("Local connection: " + InetAddress.getByName("localhost"));
		System.out.println("Connected to 127.0.0.1:" + socket.getLocalPort());
		System.out.println("This is an echo server. Type me whatever you want me to echo.");
		
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		int tries = 0;
		boolean reci = false;
		do {
			sc = new Scanner(System.in);
			mensaje = sc.nextLine();
			
			System.out.println("Waiting for a response...");

			env = mensaje.getBytes();
			rec = new byte[env.length];
			reci = false;
			do {
				out.write(env);
				try {

					in.read(rec);
					reci = true;
				} catch (InterruptedIOException e) {
					tries += 1;
					System.out.println("Interrupted connection: " + tries + " try/tries.");
				}
			} while (!reci && tries < MAXT);
			if(reci) {
			sal = new String(rec);
			System.out.println(sal);
			}else {
				sal=".";
				System.out.println("No answer.");
			}
		} while (!sal.equals(".") );

		System.out.println("The connection has been closed.");
		sc.close();

		in.close();
		out.close();
		socket.close();
	}
}