package III3;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
	private static final int TO = 3000;
	private static final int MAXT = 6;

	public static void main(String[] args) throws IOException {
		String mensaje;
		byte[] env;
		byte rec[];
		String sal = null;
		Scanner sc;

		Socket socket = new Socket("localhost", Integer.parseInt(args[0]));

		socket.setSoTimeout(TO);
		System.out.println("Conectando al servidor..");
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		int tries = 0;
		boolean reci = false;
		do {
			sc = new Scanner(System.in);
			mensaje = sc.nextLine();

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
					System.out.println("conexión interrumpida " + tries);
				}
			} while (!reci && tries < MAXT);
			if(reci) {
			sal = new String(rec);
			System.out.println(sal);
			}else {
				sal=".";
				System.out.println("Respuesta no recibida");
			}
		} while (!sal.equals(".") );

		System.out.println("Cerrando conexión");
		sc.close();

		in.close();
		out.close();
		socket.close();
	}
}