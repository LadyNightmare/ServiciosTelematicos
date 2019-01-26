package III1;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class TCPServerSelector {

	final static int BUFSZ = 256;

	public static void main(String[] args) throws IOException {

		final int port;
		final int port2;
		ServerSocketChannel ssc;
		Selector selector;

		port = 7000;
		port2 = 8000;
		ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(port));
		ssc.configureBlocking(false);

		ServerSocketChannel dcc = ServerSocketChannel.open();
		dcc.socket().bind(new InetSocketAddress(port2));
		dcc.configureBlocking(false);

		selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		SelectionKey k;
		SelectionKey select ;
		select = k = dcc.register(selector, SelectionKey.OP_ACCEPT);

		TCPProtocol protocolo = new EchoProtocol(BUFSZ, selector);
		TCPProtocol proto = new EchoProtocolR();

		try {
			System.out.println("Servidor en puerto " + port + " para TCP");
			System.out.println("Servidor en puerto " + port2 + " para TCP Reverse");

			Iterator<SelectionKey> iter;
			SelectionKey key;
			while (ssc.isOpen() || dcc.isOpen()) {
				selector.select();
				iter = selector.selectedKeys().iterator();
				
				while (iter.hasNext()) {
					key = iter.next(); 
					
					if (key.isAcceptable()) {

						if (select.equals(key)) {
							SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
							String address = (new StringBuilder(sc.socket().getInetAddress().toString())).append(":")
									.append(sc.socket().getPort()).toString();
							sc.configureBlocking(false);
							k=sc.register(key.selector(), SelectionKey.OP_READ, address); // guarda la key del SocketChannel 
							System.out.println("Conexión aceptada en " + address);
							
						} else {
							protocolo.handleAccept(key);
						}
						System.out.println(key.toString());
						System.out.println(k.toString());
					}

					if (key.isReadable()) {
						if (k.equals(key)) {
							proto.handleRead(key);

						} else {
							protocolo.handleRead(key); 
						}
						System.out.println("READ");
						System.out.println(key.toString());
						System.out.println(k.toString());

					}

					if (key.isValid() && key.isWritable()) {
						if (k.equals(key)) {
							proto.handleWrite(key);

						} else {
							protocolo.handleWrite(key);
						}
						System.out.println("WRITE");
						System.out.println(key.toString());
						System.out.println(k.toString());
					}
					iter.remove();
				}
			}
		} catch (IOException e) {
			System.out.println("IOException, puerto " + port + ". Stack trace:");
			e.printStackTrace();
		}
	}

}