package III2;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {

	static int serverPort = 8000;
	private static final int TO = 3000;
	private static final int MAXT = 6;
	
	public static void main(String[] args) throws IOException {
		DatagramPacket rec;
		DatagramPacket env;
			String vuelta;
			String mensaje; 
			byte[] mens;
			byte[] vuel;
			int conect = 0;
			InetAddress serverAdd = InetAddress.getByName("localhost");
			DatagramSocket ds= new DatagramSocket();
			ds.setSoTimeout(TO);
			Scanner sc;
			do {
				    sc = new Scanner(System.in);
					mensaje = sc.nextLine();
				
			
			 mens = mensaje.getBytes();
			 vuel = new byte[mensaje.length()];
	
			 env = new DatagramPacket(mens,mens.length,serverAdd,serverPort);
			 rec = new DatagramPacket(vuel,vuel.length);
			
			int intentos = 0;
			boolean respuesta = false;
			
			do {
				ds.send(env);
				try {
					ds.receive(rec);
					if(conect >= 1 && serverPort == ds.getPort()) { 
			    	respuesta = true;
					}else {
						respuesta = true;
					}
				}catch (InterruptedIOException e) {
					intentos+=1;
					System.out.println("Timeout intento "+intentos);
					
				}
				
			}while(!respuesta && intentos <= MAXT);
			
			if(respuesta) {
				vuelta = new String(rec.getData());
				System.out.println("Respuesta: "+vuelta);
				if(conect == 0) { //guarda el puerto de la hebra 
				conect = 1;
				serverPort = rec.getPort();
				}
			}else {
				System.out.println("No se ha recuperado respuesta");
				mensaje = ".";
			}
			
			}while(!mensaje.equals("."));
		sc.close();
		ds.close();
	}

}