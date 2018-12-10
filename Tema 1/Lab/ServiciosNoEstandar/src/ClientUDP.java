import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {

	private static final int TIMEOUT = 3000;
	private static final int MAXTRIES = 6;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		if ((args.length < 1) || (args.length > 2)) {
			throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
		}
		
		System.out.println("Cliente iniciado.");
		
		DatagramPacket recibir;
		DatagramPacket enviar;
		String recibido;
		String mensaje; 
		byte[] resp;
		int conect = 0;
		InetAddress serverAdd = InetAddress.getByName(args[0]);
		int port = Integer.parseInt(args[1]);
		DatagramSocket ds= new DatagramSocket();
		ds.setSoTimeout(TIMEOUT);
		Scanner sc;
		do {
			sc = new Scanner(System.in);
			mensaje = sc.nextLine();

			resp = new byte[mensaje.length()];

			enviar = new DatagramPacket(mensaje.getBytes(),mensaje.getBytes().length,serverAdd,port);
			recibir = new DatagramPacket(resp,resp.length);

			int intentos = 0;
			boolean respuesta = false;

			do {
				ds.send(enviar);
				try {
					ds.receive(recibir);
					if(conect >= 1 && port == ds.getPort()) { 
						respuesta = true;
					}else {
						respuesta = false;
					}
				}catch (InterruptedIOException e) {
					intentos+=1;
					System.out.println("Timeout, " + intentos + " tries have been made.");

				}

			}while(!respuesta && intentos < MAXTRIES);

			if(respuesta) {
				recibido = new String(recibir.getData());
				System.out.println("Respuesta: "+ recibido);
				if(conect == 0) { // stores the port of the thread
					conect = 1;
					port = recibir.getPort();
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
