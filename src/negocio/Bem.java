package negocio;

public class Bem {
	private String descricao;
	private String detalhes;
	private String categoria;
	private int bemId;

	public Bem(String descricao, String detalhes, String categoria) {
		this.descricao = descricao;
		this.detalhes = detalhes;
		this.categoria = categoria;
	}
	
	public Bem(int bemID,String descricao, String detalhes, String categoria) {
		this.bemId = bemID;
		this.descricao = descricao;
		this.detalhes = detalhes;
		this.categoria = categoria;
	}
	
	public int getBemId() {
		return bemId;
	}

	public void setBemId(int bemId) {
		this.bemId = bemId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return descricao + ", Descricao Completa=" + detalhes + ", Categoria="+ categoria;
	}

}
