package III2;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;


public class EchoProtocolUDP {
	
	
	public void handleRead(SelectionKey key) throws IOException {
		DatagramChannel ch = (DatagramChannel) key.channel();
		ClientRecord cln = (ClientRecord) key.attachment();
		cln.req.clear();
		cln.sc = ch.receive(cln.req); // guarda el SocketAddress
		if (cln.sc != null) {
			
			key.interestOps(SelectionKey.OP_WRITE);
		}
	}

	
	public void handleWrite(SelectionKey key) throws IOException {

		
		DatagramChannel clntChan = (DatagramChannel) key.channel();
		ClientRecord cln = (ClientRecord) key.attachment();
		cln.req.flip();
		clntChan.send(cln.req, cln.sc);
		if (!cln.req.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		}
	

	}

}

