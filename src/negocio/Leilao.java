package negocio;

import java.time.LocalDate;

public class Leilao {
	private int loteId;
	private LocalDate dataIni, dataFim;
	private double arremate;
	private String vendedor, comprador;
	
	public Leilao(int loteId, LocalDate dataIni, LocalDate dataFim, double arremate, String vendedor,
			String comprador) {
		super();
		this.loteId = loteId;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.arremate = arremate;
		this.vendedor = vendedor;
		this.comprador = comprador;
	}

	public int getLoteId() {
		return loteId;
	}

	public void setLoteId(int loteId) {
		this.loteId = loteId;
	}

	public LocalDate getDataIni() {
		return dataIni;
	}

	public void setDataIni(LocalDate dataIni) {
		this.dataIni = dataIni;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public double getArremate() {
		return arremate;
	}

	public void setArremate(double arremate) {
		this.arremate = arremate;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	@Override
	public String toString() {
		return "Informações do Leilao: Data inicio: " + dataIni + ", data fim: " + dataFim + ", Valor arremate: " + arremate
				+ ", Vendedor: " + vendedor + ", Comprador: " + comprador +" Id do Lote: " + loteId;
	}
	
	
	
	

}
