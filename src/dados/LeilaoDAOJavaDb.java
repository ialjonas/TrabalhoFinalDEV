
package dados;

import java.util.List;
import negocio.Leilao;
import java.sql.*;
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
		// Retirar do comentÃ¡rio se necessÃ¡rio
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
            stmt.setString(1, Integer.toString(l.getLoteId()));
            stmt.setString(2, l.getDataIni());
            stmt.setString(3, l.getDataFim());
            stmt.setString(4, Double.toString(l.getArremate()));
            stmt.setString(5, l.getCriador());
            stmt.setString(6, l.getVencedor());
            
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	
    public List<Leilao> getTodos() throws DAOException { //retorna lista de todos os leilões
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO");
            List<Leilao> listaLeiloes = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("leilao_id"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloes.add(l);
            }
            return listaLeiloes;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getAtivos() throws DAOException { //retorna lista de leilões ativos
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO WHERE leilao_dataFim > CURRENT TIMESTAMP");
            List<Leilao> listaLeiloesAtivos = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("leilao_id"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesAtivos.add(l);
                
            }
            return listaLeiloesAtivos;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getEncerrados() throws DAOException { //retorna lista de leilões já encerrados
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO WHERE leilao_dataFim < CURRENT TIMESTAMP");
            List<Leilao> listaLeiloesEncerrados = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("leilao_id"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesEncerrados.add(l);
                
            }
            return listaLeiloesEncerrados;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
    
    public List<Leilao> getTipoOferta() throws DAOException { //retorna lista de leilões de Oferta
        try {
        	Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILAO WHERE leilao_tipo=?");
            stmt.setString(1, "Oferta");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesOferta = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("leilao_id"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesOferta.add(l);
                
            }
            return listaLeiloesOferta;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getTipoDemanda() throws DAOException { //retorna lista de leilões de Demanda
        try {
        	Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILAO WHERE leilao_tipo=?");
            stmt.setString(1, "Demanda");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesDemanda = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("leilao_id"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesDemanda.add(l);
                
            }
            return listaLeiloesDemanda;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
    
    public List<Leilao> getLanceAberto() throws DAOException { //retorna lista de leilões de lance aberto
        try {
        	Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILAO WHERE leilao_tipo_lance=?");
            stmt.setString(1, "Aberto");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesLanceAberto = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("leilao_id"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesLanceAberto.add(l);
                
            }
            return listaLeiloesLanceAberto;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getLanceFechado() throws DAOException { //retorna lista de leilões de lance fechado
        try {
        	Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILAO WHERE leilao_tipo_lance=?");
            stmt.setString(1, "Fechado");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesLanceFechado = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("leilao_id"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("leilao_dataInicio");
                String dataFim = resultado.getString("leilao_dataFim");
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String criador = resultado.getString("leilao_criador");
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, criador, vencedor,tipo_leilao,tipo_lance);
                listaLeiloesLanceFechado.add(l);
                
            }
            return listaLeiloesLanceFechado;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
	
	
	
}