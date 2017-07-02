package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.Bem;

public class TesteBem {

	private Bem b;

	@Before
	public void setUp() throws Exception {
		b = new Bem(1, "Motorola Moto X", "Octacore", "Telefonia");
	}

	@Test
	public void testaGetBemId() {
		int idBem = b.getBemId();
		assertEquals(idBem, b.getBemId());
	}

	@Test
	public void testaGetDescricao() {
		String desc = b.getDescricao();
		assertEquals(desc, b.getDescricao());
	}

	@Test
	public void testaGetDetalhes() {
		String detalhes = b.getDetalhes();
		assertEquals(detalhes, b.getDetalhes());
	}

	@Test
	public void testeGetCategoria() {
		String cat = b.getCategoria();
		assertEquals(cat, b.getCategoria());
	}

	@Test
	public void testaSetIdBem() {
		b.setBemId(5);
		int idBem = b.getBemId();
		assertEquals(idBem, b.getBemId());
	}

	@Test
	public void testaSetDescricao() {
		b.setDescricao("Nova Descricao");
		String desc = b.getDescricao();
		assertEquals(desc, b.getDescricao());
	}

	@Test
	public void testaSetDetalhes() {
		b.setDetalhes("Novo Detalhe");
		String det = b.getDetalhes();
		assertEquals(det, b.getDetalhes());
	}

	@Test
	public void testaSetCategoria() {
		b.setCategoria("Nova Categoria");
		String cat = b.getCategoria();
		assertEquals(cat, b.getCategoria());
	}

	@Test
	public void testaToString() {
		String msg = b.toString();
		assertEquals(msg, b.toString());
	}

}
