package negocio;

public class Usuario_PF extends Usuario{
	private String CPF;

	public Usuario_PF(String CPF, String nome,String email) {
		super(nome, email);
		this.CPF=CPF;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	@Override
	public String toString() {
		return "CPF: " + CPF + super.toString();
	}
	
	

}
