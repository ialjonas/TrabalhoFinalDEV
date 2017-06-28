
package dados;

import java.util.List;

import negocio.Bem;
import negocio.Usuario_PF;
import negocio.Usuario_PJ;

import java.sql.*;
import java.util.ArrayList;

public class Usuario_PfDAOJavaDb {
	private static Usuario_PfDAOJavaDb ref;

	public static Usuario_PfDAOJavaDb getInstance(){
		if (ref == null){
			ref = new Usuario_PfDAOJavaDb();
		}
		return ref;
	}

	private Usuario_PfDAOJavaDb(){
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public boolean adicionar(Usuario_PF pf) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
            		"INSERT INTO USUARIO_PF (usuario_cpf, usuario_nome, usuario_email) VALUES (?,?,?)" //                             1        2         3            4          5             6
            );
            stmt.setString(1, pf.getCPF());
            stmt.setString(2, pf.getNome());
            stmt.setString(3, pf.getEmail());
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	
    public List<Usuario_PF> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM USUARIO_PF");
            List<Usuario_PF> listaPF = new ArrayList<Usuario_PF>();
            while(resultado.next()) {
                String CPF = resultado.getString("usuario_cpf");
                String nome = resultado.getString("usuario_nome");
                String email = resultado.getString("usuario_email");
                Usuario_PF pf = new Usuario_PF (CPF, nome, email);
        
                listaPF.add(pf);
            }
            return listaPF;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
	
	
	
	
	
	
}