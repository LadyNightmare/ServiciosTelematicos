import java.io.*;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class TCPServer {

	public static void main(String[] args) throws UnknownHostException {
		
		int serverPort;
		
		System.out.println("java Server ");
		
		serverPort = Integer.parseInt(args[0]);

		try {

			//Creating the server socket for the communication
			
			ServerSocket sockfd = new ServerSocket(serverPort);
			sockfd.setReceiveBufferSize(1);
			System.out.println("Server initialization..." + sockfd);
			System.out.println("Waiting for a new client...");
			
			Logger logger = Logger.getLogger("TCPCliente");
			Executor service = Executors.newCachedThreadPool();

			while (true) {
				

				//Creating the socket to accept the connection
				
				sockfd.setSoTimeout(60000);
				
				Socket newsockfd = sockfd.accept();
				System.out.println("New client, socket " + newsockfd);
				
				service.execute(new EchoProtocol(newsockfd, logger));
				
			}		

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}