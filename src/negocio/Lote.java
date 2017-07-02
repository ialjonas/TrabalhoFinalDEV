package negocio;

import java.util.ArrayList;

public class Lote {
	private int bemId, loteId;
	private double valor;

	public Lote(int bemId, double valor) {
		super();
		this.bemId = bemId;
		this.valor = valor;
	}

	public Lote(int loteId, int bemId, double valor) {
		super();
		this.loteId = loteId;
		this.bemId = bemId;
		this.valor = valor;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
