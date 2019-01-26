package TFTP;

/******************************
 * 
 * Asignatura: Desarrollo de Servicios Telemáticos
 * Autora: Cristina Díaz García
 * 
*******************************/

public class TFTPException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TFTPException() {
		super();
	}
	public TFTPException(String s) {
		super(s);
	}
}