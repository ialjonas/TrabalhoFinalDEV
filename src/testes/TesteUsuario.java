package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.Usuario;

public class TesteUsuario {

	private Usuario usua;

	@Before
	public void setUp() throws Exception {
		usua = new Usuario("Ial", "12345678912", "email@email.com");
	}

	@Test
	public void testaGetNome() {
		String nome = usua.getNome();
		assertEquals(nome, usua.getNome());
	}

	@Test
	public void testaSetNome() {
		usua.setNome("Jonas");
		String nome = usua.getNome();
		assertEquals(nome, usua.getNome());
	}

	@Test
	public void testaGetCPF() {
		String cpf = usua.getCpf();
		assertEquals(cpf, usua.getCpf());
	}

	@Test
	public void testaSetCPF() {
		usua.setCpf("98765432198");
		String cpf = usua.getCpf();
		assertEquals(cpf, usua.getCpf());
	}

	@Test
	public void testaGetEmail() {
		String email = usua.getEmail();
		assertEquals(email, usua.getCpf());
	}

	@Test
	public void testaSetEmail() {
		usua.setEmail("novoEmail@email.com");
		String email = usua.getEmail();
		assertEquals(email, usua.getCpf());
	}

}
