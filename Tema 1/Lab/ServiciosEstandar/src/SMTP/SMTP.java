package SMTP;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SMTP {
	/*
	 *
	 */

	public static void main(String[] args) {

		String word = "";
		System.out.println("Bienvenido al cliente de correo: ");
		StringBuilder sb = new StringBuilder();

		Socket socket = null;
		
		try {
			
			socket = new Socket("localhost", 25);
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

		try {

			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			
			String msg = null;
			byte [] read = new byte[2048];
			
			try (Scanner sc = new Scanner(System.in)) {
				
				System.out.println(InetAddress.getLocalHost().getHostName() );


				if (socket != null && outputStream != null && inputStream != null) {	

					msg = "HELO "+ InetAddress.getLocalHost().getHostName() ;

					outputStream.write(msg.getBytes());

					msg = "MAIL FROM: "+ System.getProperty("user.name")+"@"+InetAddress.getLocalHost().getHostName();
					outputStream.write(msg.getBytes());

					msg = "RCPT TO: "+System.getProperty("user.name")+"@"+InetAddress.getLocalHost().getHostName();
					outputStream.write(msg.getBytes());
					
					System.out.println(new String(read));
					msg = "DATA";
					outputStream.write(msg.getBytes());

					read = new byte[2048];
				}


				msg = "Subject "+sc.next();


				while (!word.equals(".")) {
					word = sc.next();
					sb.append(" " + word);

				}

				outputStream.writeBytes(sb.toString());
				outputStream.writeBytes("\r\n.\r\n");

			}

			outputStream.writeBytes("QUIT\r\n");

			outputStream.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

