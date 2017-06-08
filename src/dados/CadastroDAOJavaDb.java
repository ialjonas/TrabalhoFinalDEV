/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Júlio
 */
public class CadastroDAOJavaDb implements CadastroDAO {
	private static CadastroDAOJavaDb ref;

	public static CadastroDAOJavaDb getInstance() throws CadastroDAOException {
		if (ref == null)
			ref = new CadastroDAOJavaDb();
		return ref;
	}

	private CadastroDAOJavaDb() throws CadastroDAOException {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException ex) {
			throw new CadastroDAOException("JdbcOdbDriver not found!!");
		}
		// Cria o banco de dados vazio
		// Retirar do comentário se necessário
		/*
		 * try { createDB(); } catch (Exception ex) { System.out.println(
		 * "Problemas para criar o banco: "+ex.getMessage()); System.exit(0); }
		 */
	}

	private static void createDB() throws CadastroDAOException {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
			Statement sta = con.createStatement();
			String sql = "CREATE TABLE Pessoas ("
					+ "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
					+ "NOME VARCHAR(100) NOT NULL," + "TELEFONE CHAR(8) NOT NULL," + "SEXO CHAR(1) NOT NULL" + ")";
			sta.executeUpdate(sql);
			sta.close();
			con.close();
		} catch (SQLException ex) {
			throw new CadastroDAOException(ex.getMessage());
		}
	}

	private static Connection getConnection() throws SQLException {
		// derbyDB sera o nome do diretorio criado localmente
		return DriverManager.getConnection("jdbc:derby:derbyDB");
	}
}