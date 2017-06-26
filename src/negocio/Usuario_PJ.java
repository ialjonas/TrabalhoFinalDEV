package negocio;

public class Usuario_PJ extends Usuario{
	private String CNPJ;

	public Usuario_PJ(String CNPJ,String nome, String email) {
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
		return "CNPJ: "+CNPJ+ super.toString();
	}
	
	

}
