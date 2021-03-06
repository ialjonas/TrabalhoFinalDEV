
package dados;

import java.util.List;

import negocio.Lance;
import negocio.Leilao;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LeilaoDAOJavaDb {
	private static LeilaoDAOJavaDb ref;
	Date agora = new Date(System.currentTimeMillis());
	Date d_temp;
	SimpleDateFormat formatarData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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
	}

	private static Connection getConnection() throws SQLException {
		// DBTF_Dev_2017-1 sera o nome do diretorio criado localmente
		return DriverManager.getConnection("jdbc:derby:DBTF_Dev_2017-1");
	}
	
	public boolean adicionar(Leilao l) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LEILAO (LOTE_ID_foreign_key, leilao_dataInicio, leilao_dataFim, leilao_arremate, leilao_vencedor,leilao_tipo, leilao_tipo_lance) VALUES (?,?,?,?,?,?,?)" //                             1        2         3            4          5             6
                    );
            stmt.setString(1, Integer.toString(l.getLoteId()));
            stmt.setString(2, l.getDataIni());
            stmt.setString(3, l.getDataFim());
            stmt.setString(4, Double.toString(l.getArremate()));
            stmt.setString(5, l.getVencedor());
            stmt.setString(6, l.getTipo());
            stmt.setString(7, l.getTipoLance());
            
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }
	
	public boolean darLance(Lance la) throws DAOException {
		try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
            		"UPDATE LEILAO SET leilao_arremate=? WHERE leilao_id=?"
                    );
            stmt.setString(1, Double.toString(la.getLance_valor()));
            stmt.setString(2, Integer.toString(la.getLeilao_id()));
            
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao atualizar.", ex);
        }
    }
	
	
    public List<Leilao> getTodos() throws DAOException, ParseException { //retorna lista de todos os leil�es
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
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, vencedor,tipo_leilao,tipo_lance);
                
                String data_hora_lance=dataFim;
                d_temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if(agora.before(d_temp)){
                	l.ativa();
                }else l.desativa();
                
                listaLeiloes.add(l);
            }
            return listaLeiloes;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getAtivos() throws DAOException { //retorna lista de leil�es ativos
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
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                Boolean status=true;
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, vencedor,tipo_leilao,tipo_lance);
                l.ativa();
                listaLeiloesAtivos.add(l);
                
            }
            return listaLeiloesAtivos;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getEncerrados() throws DAOException { //retorna lista de leil�es j� encerrados
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
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, vencedor,tipo_leilao,tipo_lance);
                l.desativa();
                listaLeiloesEncerrados.add(l);
                
            }
            return listaLeiloesEncerrados;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
    
    public List<Leilao> getTipoOferta() throws DAOException, ParseException { //retorna lista de leil�es de Oferta
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
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, vencedor,tipo_leilao,tipo_lance);
                
                String data_hora_lance=dataFim;
                d_temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if(agora.before(d_temp)){
                	l.ativa();
                }else l.desativa();
                
                listaLeiloesOferta.add(l);
                
            }
            return listaLeiloesOferta;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getTipoDemanda() throws DAOException, ParseException { //retorna lista de leil�es de Demanda
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
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, vencedor,tipo_leilao,tipo_lance);
                
                String data_hora_lance=dataFim;
                d_temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if(agora.before(d_temp)){
                	l.ativa();
                }else l.desativa();
                
                listaLeiloesDemanda.add(l);
                
            }
            return listaLeiloesDemanda;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
    
    public List<Leilao> getLanceAberto() throws DAOException, ParseException { //retorna lista de leil�es de lance aberto
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
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, vencedor,tipo_leilao,tipo_lance);
                
                String data_hora_lance=dataFim;
                d_temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if(agora.before(d_temp)){
                	l.ativa();
                }else l.desativa();
                
                listaLeiloesLanceAberto.add(l);
                
            }
            return listaLeiloesLanceAberto;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
    public List<Leilao> getLanceFechado() throws DAOException, ParseException { //retorna lista de leil�es de lance fechado
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
                String vencedor = resultado.getString("leilao_vencedor");
                String tipo_leilao = resultado.getString("leilao_tipo");
                String tipo_lance = resultado.getString("leilao_tipo_lance");
                
                Leilao l = new Leilao(leilaoId,loteId, dataIni, dataFim, arremate, vencedor,tipo_leilao,tipo_lance);
                
                String data_hora_lance=dataFim;
                d_temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if(agora.before(d_temp)){
                	l.ativa();
                }else l.desativa();
                
                listaLeiloesLanceFechado.add(l);
                
            }
            return listaLeiloesLanceFechado;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
}