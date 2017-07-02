package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.Usuario;
import negocio.Usuario_PF;

public class TesteUsuario_PF {

	private Usuario_PF pf1;

	@Before
	public void setUp() throws Exception {
		pf1 = new Usuario_PF("12345678912", "ial", "email@email.com");
	}

	@Test
	public void testaGetNome() {
		String nome = pf1.getNome();
		assertEquals(nome, pf1.getNome());
	}

	@Test
	public void testaSetNome() {
		pf1.setNome("Jonas");
		String nome = pf1.getNome();
		assertEquals(nome, pf1.getNome());
	}

	@Test
	public void testaGetCPF() {
		String cpf = pf1.getCPF();
		assertEquals(cpf, pf1.getCPF());
	}

	@Test
	public void testaSetCPF() {
		pf1.setCPF("98765432198");
		String cpf = pf1.getCPF();
		assertEquals(cpf, pf1.getCPF());
	}

	@Test
	public void testaGetEmail() {
		String email = pf1.getEmail();
		assertEquals(email, pf1.getEmail());
	}

	@Test
	public void testaSetEmail() {
		pf1.setEmail("novoEmail@email.com");
		String email = pf1.getEmail();
		assertEquals(email, pf1.getEmail());
	}

	@Test
	public void testaToString() {
		String msg = pf1.toString();
		assertEquals(msg, pf1.toString());
	}

}
