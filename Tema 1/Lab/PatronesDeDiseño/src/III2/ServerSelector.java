package III2;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;


public class ServerSelector {
	
	final static int BUFSZ = 256;
	public static void main(String[] args) throws IOException {
		
		final int port;
		final int port2;
		ServerSocketChannel ssc;
		Selector selector;
		
			port = 9000;
			port2 = 8000; 
			//abre serversocketchannel TCP con configuracion no bloqueante
			ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(port));
			ssc.configureBlocking(false);
			//abre DatagramChannel UDP con configuracion no bloqueante
			DatagramChannel dcc = DatagramChannel.open();
			dcc.socket().bind(new InetSocketAddress(port2));
			dcc.configureBlocking(false);
			// abre el selector y registra el datagramchannel y el serversocketchannel
			selector = Selector.open();
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			SelectionKey k = dcc.register(selector, SelectionKey.OP_READ, new ClientRecord());
			
			TCPProtocol protocolo = new EchoProtocol(BUFSZ,selector); 
			EchoProtocolUDP prot = new EchoProtocolUDP();
			// crea los servicios echo
			try {
				System.out.println("Servidor en puerto " + port +"para TCP");
				System.out.println("Servidor en puerto " + port2 +"para UDP");

				Iterator<SelectionKey> iter;
				SelectionKey key;
				while(ssc.isOpen() || dcc.isOpen()) {
					selector.select(); //bloquea hasta que haya algún canal activo
					iter=selector.selectedKeys().iterator(); // keys seleccionadas
					while(iter.hasNext()) {
						key = iter.next();
						iter.remove(); // lo quita de los activos

						if(key.isAcceptable()) protocolo.handleAccept(key);
						
						if(key.isReadable()) {
							if( key.equals(k) ) { // compara la clave con la de UDP
								prot.handleRead(key);								
							}else {
								protocolo.handleRead(key);
							}						
						}
						
						if(key.isValid() && key.isWritable()) {
							if (key.equals(k)) {
								prot.handleWrite(key);
							
							}else {
								protocolo.handleWrite(key);
							}
						}
					}
				}
			} catch(IOException e) {
				System.out.println("IOException, puerto "+port+ ". Stack trace:");
				e.printStackTrace();
				}
			}
	
	
}