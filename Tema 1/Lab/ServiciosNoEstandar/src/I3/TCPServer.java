package I3;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class TCPServer {

	public static void main(String[] args) throws IOException {

		ServerSocket sockfd = new ServerSocket(7890);
		sockfd.setSoTimeout(6000);
		sockfd.setReuseAddress(true);
		
		Executor service = Executors.newCachedThreadPool();
		
		while (true) {
			Socket clntSock = sockfd.accept();
			System.out.println("Cliente aceptado");
			service.execute(new EchoProtocol(clntSock));
			
		}
	}

}