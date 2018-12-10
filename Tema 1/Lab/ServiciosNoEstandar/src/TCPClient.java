import java.io.*;
import java.net.*;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class TCPClient {

	static String IP;
	static int serverPort;

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in).useDelimiter("\\s");
		
		System.out.println("java Client ");
		
		IP = args[0];
		serverPort = Integer.parseInt(args[1]);
		
		String incoming;
		
		InetAddress serverAddr = InetAddress.getByName(IP);

		try {
			
			Socket sockfd = new Socket(serverAddr, serverPort);
			System.out.println("Local connection: " + serverAddr);

			BufferedReader in = new BufferedReader(new InputStreamReader(sockfd.getInputStream()));
			PrintWriter out = new PrintWriter(sockfd.getOutputStream(), true);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			
			System.out.println("Tamaño actual del buffer de escritura: " + sockfd.getSendBufferSize());
			System.out.println("Cambiando tamaño a 512...");
			sockfd.setSendBufferSize(512);
			System.out.println("Tamaño actual del buffer de escritura: " + sockfd.getSendBufferSize());
			sockfd.setSendBufferSize(1313280);
			
			System.out.println("Connected to " + IP + ":" + serverPort);
			
			System.out.println(in.readLine());

			while ((userInput = stdIn.readLine()) != null) {
					
					
					out.println(userInput);
					System.out.println("Waiting for a response...");
					incoming = in.readLine();
					
					if(incoming == null) {
						
						userInput = null;
						
						out.close();
						in.close();
						stdIn.close();
						sockfd.close();
						sc.close();
						
					} else {
						
					if (!userInput.equals(".")) {
						
						System.out.println("echo: " + incoming);
						
					} else {
						
						System.out.println(incoming);
						
					}
					
					}

			}
			
			out.close();
			in.close();
			stdIn.close();
			sockfd.close();
			sc.close();


		} catch (UnknownHostException e) {

			System.err.println("Unknown: " + serverAddr);
			System.exit(1);

		} catch (IOException e) {
			
			System.err.println("Server has closed.");
			System.exit(1);

			
		}

	}

}