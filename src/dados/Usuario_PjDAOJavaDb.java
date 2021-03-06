
package dados;

import java.util.List;

import negocio.Bem;
import negocio.Usuario_PJ;

import java.sql.*;
import java.util.ArrayList;

public class Usuario_PjDAOJavaDb {
	private static Usuario_PjDAOJavaDb ref;

	public static Usuario_PjDAOJavaDb getInstance(){
		if (ref == null){
			ref = new Usuario_PjDAOJavaDb();
		}
		return ref;
	}

	private Usuario_PjDAOJavaDb(){
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public boolean adicionar(Usuario_PJ pj) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
            		"INSERT INTO USUARIO_PJ (usuario_cnpj, usuario_nome, usuario_email) VALUES (?,?,?)" //                             1        2         3            4          5             6
            );
            stmt.setString(1, pj.getCNPJ());
            stmt.setString(2, pj.getNome());
            stmt.setString(3, pj.getEmail());
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	
    public List<Usuario_PJ> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM USUARIO_PJ");
            List<Usuario_PJ> listaPJ = new ArrayList<Usuario_PJ>();
            while(resultado.next()) {
                String CNPJ = resultado.getString("usuario_cnpj");
                String nome = resultado.getString("usuario_nome");
                String email = resultado.getString("usuario_email");
                Usuario_PJ pj = new Usuario_PJ (CNPJ, nome, email);
        
                listaPJ.add(pj);
            }
            return listaPJ;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
	
	
	
	
	
	
}