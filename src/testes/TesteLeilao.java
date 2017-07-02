package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.Leilao;

public class TesteLeilao {

	private Leilao lei;

	@Before
	public void setUp() throws Exception {
		lei = new Leilao(5, 2, "01/02/20017", "05/02/2017", 366, "Dudu", "PF", "Fechado");
	}

	@Test
	public void testaGetLeilaoId() {
		int idLeilao = lei.getLeilaoId();
		assertEquals(idLeilao, lei.getLeilaoId());
	}

	@Test
	public void testaGetLoteId() {
		int idLote = lei.getLoteId();
		assertEquals(idLote, lei.getLoteId());
	}

	@Test
	public void testaSetLoteId() {
		lei.setLoteId(9);
		int idLote = lei.getLoteId();
		assertEquals(idLote, lei.getLoteId());
	}

	@Test
	public void testaGetDataIni() {
		String data = lei.getDataIni();
		assertEquals(data, lei.getDataIni());
	}

	@Test
	public void testaSetDataIni() {
		lei.setDataIni("10/10/2018");
		String data = lei.getDataIni();
		assertEquals(data, lei.getDataIni());
	}

	@Test
	public void testaGetDataFim() {
		String data = lei.getDataFim();
		assertEquals(data, lei.getDataFim());
	}

	@Test
	public void testaSetDataFim() {
		lei.setDataFim("10/10/2018");
		String data = lei.getDataFim();
		assertEquals(data, lei.getDataFim());
	}

	@Test
	public void testaGetArremate() {
		double arremate = lei.getArremate();
		assertEquals(arremate, 0, lei.getArremate());
	}

	@Test
	public void testaSetArremate() {
		lei.setArremate(988);
		double arremate = lei.getArremate();
		assertEquals(arremate, 0, lei.getArremate());
	}

	@Test
	public void testaGetVencedor() {
		String vencedor = lei.getVencedor();
		assertEquals(vencedor, lei.getVencedor());
	}

	@Test
	public void testaSetVencedor() {
		lei.setVencedor("Goku");
		String vencedor = lei.getVencedor();
		assertEquals(vencedor, lei.getVencedor());
	}

	@Test
	public void testaGetTipo() {
		String tipo = lei.getTipo();
		assertEquals(tipo, lei.getTipo());
	}

	@Test
	public void testaSetTipo() {
		lei.setTipo("Tipo");
		String tipo = lei.getTipo();
		assertEquals(tipo, lei.getTipo());
	}

	@Test
	public void testeGetTipoLance() {
		String tipo = lei.getTipoLance();
		assertEquals(tipo, lei.getTipoLance());
	}

	@Test
	public void testeSetTipoLance() {
		lei.setTipoLance("Vencedor");
		String tipo = lei.getTipoLance();
		assertEquals(tipo, lei.getTipoLance());
	}

	@Test
	public void testaToString() {
		String msg = lei.toString();
		assertEquals(msg, lei.toString());
	}

}
