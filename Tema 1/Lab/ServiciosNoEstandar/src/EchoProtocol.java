import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class EchoProtocol implements Runnable {
	
	private Socket socket;
	private Logger logger;
	
	public EchoProtocol (Socket socket, Logger logger) {
		
		this.socket = socket;
		this.logger = logger;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			//We send the message to the connected client
			
			out.println("This is an echo server. Type whatever you want me to echo.");
			
			boolean salir = false;
			String line;
			
			//if there's nothing left to print, we close the connection

			while (!salir) {
				
				socket.setSoTimeout(60000);
				
				line = in.readLine();
				
				if (line != null) {
					
					if (line.equals(".")) {
						
						out.println("The connection has been closed.");
						salir = true;
						System.out.println("Waiting for a new client...");
						
					} else {
					
						out.println(line);
					
					}
					
				}

				

			}

			socket.close();
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}

	}
		
	}
