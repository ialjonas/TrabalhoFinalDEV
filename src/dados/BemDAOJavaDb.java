
package dados;

import java.util.List;

import negocio.Bem;

import java.sql.*;
import java.util.ArrayList;

public class BemDAOJavaDb {
	private static BemDAOJavaDb ref;

	public static BemDAOJavaDb getInstance(){
		if (ref == null){
			ref = new BemDAOJavaDb();
		}
		return ref;
	}

	private BemDAOJavaDb(){
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
	
	public boolean adicionar(Bem b) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO BEM (bem_descricao, bem_detalhes, bem_categoria) VALUES (?,?,?)" //                             1        2         3            4          5             6
                    );
            stmt.setString(1, b.getDescricao());
            stmt.setString(2, b.getDetalhes());
            stmt.setString(3, b.getCategoria());
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	public Bem getBemPorBemID(int bemId) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM BEM WHERE bem_id=?");
            stmt.setString(1, Integer.toString(bemId));
            ResultSet resultado = stmt.executeQuery();
            Bem b = null;
            if(resultado.next()) {
                int bem_Id = Integer.parseInt(resultado.getString("bem_id"));
                String descricao = resultado.getString("bem_descricao");
                String detalhes = resultado.getString("bem_detalhes");
                String categoria = resultado.getString("bem_categoria");
                b = new Bem(bem_Id,descricao,detalhes,categoria);
            }
            return b;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
	
    public List<Bem> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM BEM");
            List<Bem> listaBens = new ArrayList<Bem>();
            while(resultado.next()) {
            	int bemId=Integer.parseInt(resultado.getString("bem_id"));
                String descricao = resultado.getString("bem_descricao");
                String detalhes = resultado.getString("bem_detalhes");
                String categoria = resultado.getString("bem_categoria");
                Bem b = new Bem (bemId,descricao, detalhes, categoria);
        
                listaBens.add(b);
            }
            return listaBens;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
	
	
	
	
	
	
}