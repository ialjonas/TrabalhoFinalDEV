/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

/**
 *
 * @author Ial
 */
public class DAOException extends Exception {

	/**
	 * Creates a new instance of <code>CadastroDAOException</code> without
	 * detail message.
	 */
	public DAOException() {
	}

	/**
	 * Constructs an instance of <code>CadastroDAOException</code> with the
	 * specified detail message.
	 *
	 * @param msg
	 *            the detail message.
	 */
	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

}
