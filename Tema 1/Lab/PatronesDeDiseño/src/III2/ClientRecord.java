package III2;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class ClientRecord {
	ByteBuffer req;
    SocketAddress sc;

    public ClientRecord() {
        req = ByteBuffer.allocate(255);
    }
}