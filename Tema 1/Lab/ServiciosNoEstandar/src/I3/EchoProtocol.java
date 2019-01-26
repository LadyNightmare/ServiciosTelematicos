package I3;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;


public class EchoProtocol implements Runnable {
	private Socket soc;

	public EchoProtocol(Socket socket) {
		this.soc = socket;
		
		try {
			
			socket.setSoTimeout(60000);
			
		} catch (SocketException e) {

			e.printStackTrace();
			
		}
		
	}

	@Override
	public void run() {
		
		try {
			
			String fin =".";
			byte[] buffer = new byte[2048];
			InputStream in = soc.getInputStream();
			OutputStream out = soc.getOutputStream();
			int tam = 0;
			boolean comprobado = false;

			do {
				
				buffer = new byte[2048];
				tam = in.read(buffer);
				out.write(buffer, 0, tam);
				
				comprobado = (new String(buffer,0,tam).equals(fin));
			
			} while (!comprobado);

			in.close();
			out.close();
			soc.close();

		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

	}

}