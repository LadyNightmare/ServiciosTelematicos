package III2;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class EchoProtocol implements TCPProtocol {
	private int bufsize;
	private Selector selector;
	ByteBuffer buf = ByteBuffer.allocate(256);

	public EchoProtocol(int bufsize, Selector selector) {
		this.bufsize = bufsize;
		this.selector = selector;
	}

	@Override
	public void handleAccept(SelectionKey key) throws IOException {
		SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
		String address = (new StringBuilder(sc.socket().getInetAddress().toString())).append(":")
				.append(sc.socket().getPort()).toString(); // consigue la IP y el puerto
		sc.configureBlocking(false);
		sc.register(key.selector(), SelectionKey.OP_READ, address); 
		System.out.println("Conexión aceptada en: " + address);

	}

	@Override
	public void handleRead(SelectionKey key) throws IOException {
		SocketChannel ch = (SocketChannel) key.channel();
		long bytesRead = ch.read(buf); // lee en el buffer
		if (bytesRead == -1) { // no hay datos, se ha cerrado a conexión
			System.out.println("Se ha cerrado la conexión");
			ch.close();
		} else if (bytesRead > 0)
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException {

		buf.flip();
		SocketChannel clntChan = (SocketChannel) key.channel();
		clntChan.write(buf); //envia el mismo mensaje que recibió 
		if (!buf.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		}
		buf.compact();

	}

}