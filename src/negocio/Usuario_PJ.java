package negocio;

public class Usuario_PJ extends Usuario{
	private String CNPJ;

	public Usuario_PJ(String nome, String email, String CNPJ) {
		super(nome, email);
		this.CNPJ=CNPJ;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	@Override
	public String toString() {
		return super.toString() + "CNPJ=" + CNPJ;
	}
	
	

}
