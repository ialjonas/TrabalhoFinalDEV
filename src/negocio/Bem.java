package negocio;

public class Bem {
	private String breveDescricao;
	private String descricaoCompleta;
	private int categoria;

	public Bem(String breveDescricao, String descricaoCompleta, int categoria) {
		this.breveDescricao = breveDescricao;
		this.descricaoCompleta = descricaoCompleta;
		this.categoria = categoria;
	}

	public String getBreveDescricao() {
		return breveDescricao;
	}

	public void setBreveDescricao(String breveDescricao) {
		this.breveDescricao = breveDescricao;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Bem [breveDescricao=" + breveDescricao + ", descricaoCompleta=" + descricaoCompleta + ", categoria="
				+ categoria + "]";
	}

}
