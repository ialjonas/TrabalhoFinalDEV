package negocio;

public class Lance {
	private int leilao_id, lance_id;
	private String usuario_id, lance_data;
	private double lance_valor;
	
	public Lance(int leilao_id, String usuario_id, double lance_valor,String lance_data) {
		super();
		this.leilao_id = leilao_id;
		this.usuario_id = usuario_id;
		this.lance_valor = lance_valor;
		this.lance_data = lance_data;
	}
		
	public Lance(int lance_id, int leilao_id, String usuario_id, double lance_valor,String lance_data) {
		super();
		this.lance_id = lance_id;
		this.leilao_id = leilao_id;
		this.usuario_id = usuario_id;
		this.lance_valor = lance_valor;
		this.lance_data = lance_data;
	}

	public String getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getLance_data() {
		return lance_data;
	}

	public void setLance_data(String lance_data) {
		this.lance_data = lance_data;
	}

	public int getLeilao_id() {
		return leilao_id;
	}

	public void setLeilao_id(int leilao_id) {
		this.leilao_id = leilao_id;
	}

	public int getLance_id() {
		return lance_id;
	}

	public void setLance_id(int lance_id) {
		this.lance_id = lance_id;
	}

	public double getLance_valor() {
		return lance_valor;
	}

	public void setLance_valor(double lance_valor) {
		this.lance_valor = lance_valor;
	}

	@Override
	public String toString() {
		return "Lance [usuario_id=" + usuario_id + ", lance_data=" + lance_data + ", leilao_id=" + leilao_id
				+ ", lance_id=" + lance_id + ", lance_valor=" + lance_valor + "]";
	}
	
	
	

}
