package III3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class Server {

	final static int BUFFSIZE = 512;
	static int port = 7000;
	static int count = 0;
	public static void Startnio() throws Exception {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		ServerSocket server = serverChannel.socket();
		server.bind(new InetSocketAddress(port));
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server began listening on port: " + port);

		while (true) {
			int num = selector.select();
			
			if (num == 0) {
				continue;
			}
			
			Set keys = selector.selectedKeys();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					Socket client = server.accept();

					count++;
					System.out.println("Client Connected...." + "you have "
							+ count + " clients connected");
					SocketChannel clientChannel = client.getChannel();
					clientChannel.configureBlocking(false);
					clientChannel.register(selector, SelectionKey.OP_READ);

				} else {
					if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
						SocketChannel client = null;
						client = (SocketChannel) key.channel();
						
						EchoProtocol cliente = new EchoProtocol(BUFFSIZE, selector);
						
					}
				}
				
				it.remove();

			}

		}
	}

}