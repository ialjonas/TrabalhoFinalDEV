package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.Lote;

public class TesteLote {

	private Lote lo;

	@Before
	public void setUp() throws Exception {
		lo = new Lote(5, 10, 366.55);
	}

	@Test
	public void testaGetBemId() {
		int bemId = lo.getBemId();
		assertEquals(bemId, lo.getBemId());
	}

	@Test
	public void testaSetBemId() {
		lo.setBemId(9);
		int bemId = lo.getBemId();
		assertEquals(bemId, lo.getBemId());
	}

	@Test
	public void testaGetLoteId() {
		int loteId = lo.getLoteId();
		assertEquals(loteId, lo.getLoteId());
	}

	@Test
	public void testaGetValor() {
		double valor = lo.getValor();
		assertEquals(valor, 0, lo.getValor());
	}

	@Test
	public void testaSetValor() {
		lo.setValor(889.88);
		double valor = lo.getValor();
		assertEquals(valor, 0, lo.getValor());
	}

	@Test
	public void testaToString() {
		String msg = lo.toString();
		assertEquals(msg, lo.toString());
	}

}
