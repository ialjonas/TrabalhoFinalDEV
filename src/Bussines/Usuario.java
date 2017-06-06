package Bussines;

public class Usuario {
	private String nome;
	private String cpf;
	private String cnpj;
	private String email;

	public Usuario(String nome, String cpf, String cnpj, String email) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", cpf=" + cpf + ", cnpj=" + cnpj + ", email=" + email + "]";
	}

}
