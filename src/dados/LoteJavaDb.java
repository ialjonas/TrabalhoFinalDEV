
package dados;

import java.util.List;

import negocio.Bem;
import negocio.Lote;

import java.sql.*;
import java.util.ArrayList;

public class LoteJavaDb {
	private static LoteJavaDb ref;

	public static LoteJavaDb getInstance(){
		if (ref == null){
			ref = new LoteJavaDb();
		}
		return ref;
	}

	private LoteJavaDb(){
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

	private static Connection getConnection() throws SQLException {
		// DBTF_Dev_2017-1 sera o nome do diretorio criado localmente
		return DriverManager.getConnection("jdbc:derby:DBTF_Dev_2017-1");
	}
	
	public boolean adicionar(Lote l) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LOTE (BEM_ID_foreign_key) VALUES (?)"
                    );
            String bemId = Integer.toString(l.getBemId());
            stmt.setString(1, bemId);
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	public Lote getLotePorLoteID(int loteId) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM LOTE WHERE lote_id=?"
                    );
            stmt.setString(1, Integer.toString(loteId));
            ResultSet resultado = stmt.executeQuery();
            Lote l = null;
            if(resultado.next()) {
                int lote_id = Integer.parseInt(resultado.getString("lote_id"));
                int bem_id= Integer.parseInt(resultado.getString("BEM_ID_foreign_key"));
                l = new Lote(lote_id,bem_id);
            }
            return l;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
	
	
    public List<Lote> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LOTE");
            List<Lote> listaLotes = new ArrayList<Lote>();
            while(resultado.next()) {
                int bemId=Integer.parseInt(resultado.getString("BEM_ID_foreign_key"));
                Lote l = new Lote (bemId);
                listaLotes.add(l);
            }
            return listaLotes;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
}