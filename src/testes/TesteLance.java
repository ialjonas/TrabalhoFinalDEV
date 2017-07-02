package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.Lance;

public class TesteLance {

	private Lance lanc;

	@Before
	public void setUp() throws Exception {
		lanc = new Lance(1, "3", 3.65, "2/07/2017");
	}

	@Test
	public void testaGetUsuarioId() {
		String userId = lanc.getUsuario_id();
		assertEquals(userId, lanc.getUsuario_id());
	}

	@Test
	public void testaSetUsuarioId() {
		lanc.setUsuario_id("123");
		String userId = lanc.getUsuario_id();
		assertEquals(userId, lanc.getUsuario_id());
	}

	@Test
	public void testaGetLanceData() {
		String data = lanc.getLance_data();
		assertEquals(data, lanc.getLance_data());
	}

	@Test
	public void testaSetLanceData() {
		lanc.setLance_data("20/08/2017");
		String data = lanc.getLance_data();
		assertEquals(data, lanc.getLance_data());
	}

	@Test
	public void testaGetLeilaoId() {
		int idLeilao = lanc.getLeilao_id();
		assertEquals(idLeilao, lanc.getLeilao_id());
	}

	@Test
	public void testaSetLeilaoId() {
		lanc.setLeilao_id(99);
		int idLeilao = lanc.getLeilao_id();
		assertEquals(idLeilao, lanc.getLeilao_id());
	}

	@Test
	public void testaGetLanceId() {
		int lanceId = lanc.getLance_id();
		assertEquals(lanceId, lanc.getLance_id());
	}

	@Test
	public void testaSetLanceId() {
		lanc.setLance_id(888);
		int lanceId = lanc.getLance_id();
		assertEquals(lanceId, lanc.getLance_id());
	}

	@Test
	public void testaGetLanceValor() {
		double valor = lanc.getLance_valor();
		assertEquals(valor, 0, lanc.getLance_valor());
	}

	@Test
	public void testaSetLanceValor() {
		lanc.setLance_valor(898.65);
		double valor = lanc.getLance_valor();
		assertEquals(valor, 0, lanc.getLance_valor());
	}

	@Test
	public void testaToString() {
		String msg = lanc.toString();
		assertEquals(msg, lanc.toString());
	}

}
