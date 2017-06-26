package negocio;

import java.util.ArrayList;

public class Lote {
	private int bemId;

	public Lote(int bemId) {
		this.bemId = bemId;
	}

	public int getBemId() {
		return bemId;
	}

	public void setBemId(int bemId) {
		this.bemId = bemId;
	}

	@Override
	public String toString() {
		return "Lote [bemId=" + bemId + "]";
	}
	
}
