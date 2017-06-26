
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

public class LeilaoJavaDb {
	private static LeilaoJavaDb ref;

	public static LeilaoJavaDb getInstance(){
		if (ref == null){
			ref = new LeilaoJavaDb();
		}
		return ref;
	}

	private LeilaoJavaDb(){
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
	
	public boolean adicionar(Leilao l) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LEILAO (LOTE_ID_foreign_key, leilao_dataInicio, leilao_dataFim, leilao_arremate, leilao_vendedor, leilao_comprador) VALUES (?,?,?,?,?,?)" //                             1        2         3            4          5             6
                    );
            String loteId = Integer.toString(l.getLoteId());
            
            DateFormat dataString = new SimpleDateFormat("yyyy-MM-dd"); //converter Data para String
            String dataIni = dataString.format(l.getDataIni());
            String dataFim = dataString.format(l.getDataFim());
            String arremate = Double.toString(l.getArremate());
            stmt.setString(1, loteId);
            stmt.setString(2, dataIni);
            stmt.setString(3, dataFim);
            stmt.setString(4, arremate);
            stmt.setString(5, l.getVendedor());
            stmt.setString(6, l.getComprador());
            
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
                
                DateTimeFormatter stringData = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //converter String para Data
                LocalDate dataIni = LocalDate.parse(resultado.getString("leilao_dataInicio"),stringData);
                LocalDate dataFim = LocalDate.parse(resultado.getString("leilao_dataFim"),stringData);
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String vendedor = resultado.getString("leilao_vendedor");
                String comprador = resultado.getString("leilao_comprador");
                
                Leilao l = new Leilao(loteId, dataIni, dataFim, arremate, vendedor, comprador);
        
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
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO");
            List<Leilao> listaLeiloesAtivos = new ArrayList<Leilao>();
            while(resultado.next()) {
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                
                DateTimeFormatter stringData = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //converter String para Data
                LocalDate dataIni = LocalDate.parse(resultado.getString("leilao_dataInicio"),stringData);
                LocalDate dataFim = LocalDate.parse(resultado.getString("leilao_dataFim"),stringData);
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String vendedor = resultado.getString("leilao_vendedor");
                String comprador = resultado.getString("leilao_comprador");
                
                Leilao l = new Leilao(loteId, dataIni, dataFim, arremate, vendedor, comprador);
        
                if(dataFim.isAfter(LocalDate.now())){ //data de fim do leilão for depois de hoje, leilão não terminou
                	listaLeiloesAtivos.add(l);
                }
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
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILAO");
            List<Leilao> listaLeiloesEncerrados = new ArrayList<Leilao>();
            while(resultado.next()) {
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                DateTimeFormatter stringData = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //converter String para Data
                LocalDate dataIni = LocalDate.parse(resultado.getString("leilao_dataInicio"),stringData);
                LocalDate dataFim = LocalDate.parse(resultado.getString("leilao_dataFim"),stringData);
                double arremate = Double.parseDouble(resultado.getString("leilao_arremate"));
                String vendedor = resultado.getString("leilao_vendedor");
                String comprador = resultado.getString("leilao_comprador");
                
                Leilao l = new Leilao(loteId, dataIni, dataFim, arremate, vendedor, comprador);
                
                if(dataFim.isBefore(LocalDate.now())){ //data de fim do leilão for antes de hoje, leilão já terminou
                	listaLeiloesEncerrados.add(l);
                }
            }
            return listaLeiloesEncerrados;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
	
	
	
	
	
	
}