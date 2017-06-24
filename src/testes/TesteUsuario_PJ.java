package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.Usuario;
import negocio.Usuario_PJ;

public class TesteUsuario_PJ {
 
	private Usuario_PJ pj1;

	@Before
	public void setUp() throws Exception {
		pj1=new Usuario_PJ("Ial", "12345674", "email@email.com");
	}

	@Test
	public void testaGetNome() {
		String nome = pj1.getNome();
		assertEquals(nome, pj1.getNome());
	}

	@Test
	public void testaSetNome() {
		pj1.setNome("Jonas");
		String nome = pj1.getNome();
		assertEquals(nome, pj1.getNome());
	}

	@Test
	public void testaGetCPF() {
		String cpf = pj1.getCNPJ();
		assertEquals(cpf, pj1.getCNPJ());
	}

	@Test
	public void testaSetCPF() {
		pj1.setCNPJ("98765432198");
		String cpf = pj1.getCNPJ();
		assertEquals(cpf, pj1.getCNPJ());
	}

	@Test
	public void testaGetEmail() {
		String email = pj1.getEmail();
		assertEquals(email, pj1.getCNPJ());
	}

	@Test
	public void testaSetEmail() {
		pj1.setEmail("novoEmail@email.com");
		String email = pj1.getEmail();
		assertEquals(email, pj1.getCNPJ());
	}

}
