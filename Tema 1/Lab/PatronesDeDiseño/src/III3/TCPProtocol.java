package III3;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.nio.channels.SelectionKey;
import java.io.IOException;

public interface TCPProtocol {
	void handleAccept (SelectionKey key) throws IOException;
	void handleRead (SelectionKey key) throws IOException;
	void handleWrite (SelectionKey key) throws IOException;
}