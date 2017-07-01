
package dados;

import java.util.List;

import negocio.Bem;
import negocio.Lance;
import negocio.Lote;

import java.sql.*;
import java.util.ArrayList;

public class LanceDAOJavaDb {
	private static LanceDAOJavaDb ref;

	public static LanceDAOJavaDb getInstance(){
		if (ref == null){
			ref = new LanceDAOJavaDb();
		}
		return ref;
	}

	private LanceDAOJavaDb(){
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		// DBTF_Dev_2017-1 sera o nome do diretorio criado localmente
		return DriverManager.getConnection("jdbc:derby:DBTF_Dev_2017-1");
	}
	
	public boolean adicionar(Lance l) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LANCE (LEILAO_ID_foreign_key, usuario_id, lance_valor, lance_data) VALUES (?,?,?,?)"
                    );
            stmt.setString(1, Integer.toString(l.getLeilao_id()));
            stmt.setString(2, l.getUsuario_id());
            stmt.setString(3, Double.toString(l.getLance_valor()));
            stmt.setString(4, l.getLance_data());
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	public List<Lance> getLancesPorLeilaoID(int leilaoId) throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LANCE WHERE LEILAO_ID_foreign_key=?");
            List<Lance> listaLances = new ArrayList<Lance>();
            while(resultado.next()) {
            	int lance_id = Integer.parseInt(resultado.getString("lance_id"));
                int leilao_id = Integer.parseInt(resultado.getString("LEILAO_ID_foreign_key"));
                String usuario_id = resultado.getString("usuario_id");
                double lance_valor = Double.parseDouble(resultado.getString("lance_valor"));
                String lance_data = resultado.getString("lance_data");
                Lance l =new Lance(lance_id,leilao_id,usuario_id,lance_valor,lance_data);
                listaLances.add(l);
            }
            return listaLances;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
	
	
    public List<Lance> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LANCE");
            List<Lance> listaLances = new ArrayList<Lance>();
            while(resultado.next()) {
            	int lance_id = Integer.parseInt(resultado.getString("lance_id"));
                int leilao_id = Integer.parseInt(resultado.getString("LEILAO_ID_foreign_key"));
                String usuario_id = resultado.getString("usuario_id");
                double lance_valor = Double.parseDouble(resultado.getString("lance_valor"));
                String lance_data = resultado.getString("lance_data");
                Lance l =new Lance(lance_id,leilao_id,usuario_id,lance_valor,lance_data);
                listaLances.add(l);
            }
            return listaLances;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
}