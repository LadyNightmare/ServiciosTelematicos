package III1;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class EchoProtocolR implements TCPProtocol {

	ByteBuffer buf = ByteBuffer.allocate(256);

	@Override
	public void handleAccept(SelectionKey key) throws IOException {
		SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
		String address = (new StringBuilder(sc.socket().getInetAddress().toString())).append(":")
				.append(sc.socket().getPort()).toString();
		sc.configureBlocking(false);
		sc.register(key.selector(), SelectionKey.OP_READ, address);
		System.out.println("Conexión aceptada en " + address);

	}

	@Override
	public void handleRead(SelectionKey key) throws IOException {
		SocketChannel ch = (SocketChannel) key.channel();
		long bytesRead = ch.read(buf);
		if (bytesRead == -1) {
			System.out.println("Conexión cerrada.");
			ch.close();
		} else if (bytesRead > 0) {
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}

	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException {

		buf.flip();
		SocketChannel clntChan = (SocketChannel) key.channel();
	
	    byte[] received = new byte[buf.remaining()];
	    buf.get(received);
	    String r = reverse(received);    
	    received = r.getBytes();	    
	    System.out.println(r);
		clntChan.write(ByteBuffer.wrap(received));
		
		if (!buf.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		}
		buf.compact();

	}

	private String reverse(byte[] r) {
		String rev = new String(r);
		rev.trim();
		StringBuilder a = new StringBuilder();
		a.append(rev);
		return a.reverse().toString();

	}
}