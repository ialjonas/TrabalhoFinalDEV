package negocio;

import java.util.ArrayList;

public class Lote {
	private int bemId, loteId;

	public Lote(int bemId) {
		this.bemId = bemId;
	}
	
	public Lote(int loteId, int bemId) {
		this.loteId = loteId;
		this.bemId = bemId;
	}

	public int getBemId() {
		return bemId;
	}

	public void setBemId(int bemId) {
		this.bemId = bemId;
	}
	
	public int getLoteId() {
		return loteId;
	}

	public void setLoteId(int loteId) {
		this.loteId = loteId;
	}

	@Override
	public String toString() {
		return "Lote [bemId=" + bemId + ", loteId=" + loteId + "]";
	}

	
	
}
