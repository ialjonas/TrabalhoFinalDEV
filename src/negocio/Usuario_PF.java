package negocio;

public class Usuario_PF extends Usuario{
	private String CPF;

	public Usuario_PF(String nome, String email,String CPF) {
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
		return super.toString() + "CPF=" + CPF;
	}
	
	

}
