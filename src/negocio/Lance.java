package negocio;

public class Lance {
	private String usuario_id, lance_data;
	private int leilao_id, lance_id;
	private double lance_valor;
	
	public Lance(String usuario_id, String lance_data, int leilao_id, double lance_valor) {
		super();
		this.usuario_id = usuario_id;
		this.lance_data = lance_data;
		this.leilao_id = leilao_id;
		this.lance_valor = lance_valor;
	}
		
	public Lance(String usuario_id, String lance_data, int leilao_id, int lance_id, double lance_valor) {
		super();
		this.usuario_id = usuario_id;
		this.lance_data = lance_data;
		this.leilao_id = leilao_id;
		this.lance_id = lance_id;
		this.lance_valor = lance_valor;
	}
	
	

}
