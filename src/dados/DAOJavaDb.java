
package dados;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class DAOJavaDb {
	private static DAOJavaDb ref;

	public static DAOJavaDb getInstance() throws DAOException {
		if (ref == null)
			ref = new DAOJavaDb();
		return ref;
	}

	private DAOJavaDb() throws DAOException {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException ex) {
			throw new DAOException("JdbcOdbDriver not found!!");
		}
		// Cria o banco de dados vazio
		// Retirar do comentário se necessário
		/*
		 * try { createDB(); } catch (Exception ex) { System.out.println(
		 * "Problemas para criar o banco: "+ex.getMessage()); System.exit(0); }
		 */
	}

	private static void createDB() throws DAOException {
		try {
			Connection con = DriverManager.getConnection("jdbc:derby:DBTF_Dev_2017-1;create=true");
			Statement sta = con.createStatement();
			String sql = "CREATE TABLE Pessoas ("
					+ "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
					+ "NOME VARCHAR(100) NOT NULL," + "TELEFONE CHAR(8) NOT NULL," + "SEXO CHAR(1) NOT NULL" + ")";
			sta.executeUpdate(sql);
			sta.close();
			con.close();
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage());
		}
	}

	private static Connection getConnection() throws SQLException {
		// DBTF_Dev_2017-1 sera o nome do diretorio criado localmente
		return DriverManager.getConnection("jdbc:derby:DBTF_Dev_2017-1");
	}
}