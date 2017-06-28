
package dados;

import java.util.List;

import negocio.Bem;
import negocio.Leilao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LeilaoDAOJavaDb {
	private static LeilaoDAOJavaDb ref;

	public static LeilaoDAOJavaDb getInstance(){
		if (ref == null){
			ref = new LeilaoDAOJavaDb();
		}
		return ref;
	}

	private LeilaoDAOJavaDb(){
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
	
	public boolean adicionar(Leilao l) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LEILAO (LOTE_ID_foreign_key, leilao_dataInicio, leilao_dataFim, leilao_arremate, leilao_criador, leilao_vencedor) VALUES (?,?,?,?,?,?)" //                             1        2         3            4          5             6
                    );
            String loteId = Integer.toString(l.getLoteId());
            
            DateFormat dataString = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); //converter Data para String
            String dataIni = dataString.format(l.getDataIni());
            String dataFim = dataString.format(l.getDataFim());
            String arremate = Double.toString(l.getArremate());
            stmt.setString(1, loteId);
            stmt.setString(2, dataIni);
            stmt.setString(3, dataFim);
            stmt.setString(4, arremate);
            stmt.setString(5, l.getCriador());
            stmt.setString(6, l.getVencedor());
            
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	
    public List<Leilao> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO");
            List<Leilao> listaLeiloes = new ArrayList<Leilao>();
            while(resultado.next()) {
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
        
                listaLeiloes.add(l);
            }
            return listaLeiloes;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getAtivos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO WHERE leilao_dataFim > CURRENT_TIMESTAMP");
            List<Leilao> listaLeiloesAtivos = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesAtivos.add(l);
                
            }
            return listaLeiloesAtivos;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getEncerrados() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO WHERE leilao_dataFim < CURRENT_TIMESTAMP");
            List<Leilao> listaLeiloesEncerrados = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesEncerrados.add(l);
                
            }
            return listaLeiloesEncerrados;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
	
	
	
	
	
}