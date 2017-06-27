package negocio;

import java.time.LocalDate;

public class Leilao {
	private int loteId,leilaoId;
	private LocalDate dataIni, dataFim;
	private double arremate;
	private String criador, vencedor,tipo,tipoLance;
	
	public Leilao(int loteId, LocalDate dataIni, LocalDate dataFim, double arremate, String criador, String vencedor,
			String tipo, String tipoLance) {
		super();
		this.loteId = loteId;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.arremate = arremate;
		this.criador = criador;
		this.vencedor = vencedor;
		this.tipo = tipo;
		this.tipoLance = tipoLance;
	}
	
	public Leilao(int leilaoId, int loteId, LocalDate dataIni, LocalDate dataFim, double arremate, String criador, String vencedor,
			String tipo, String tipoLance) {
		super();
		this.leilaoId = leilaoId;
		this.loteId = loteId;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.arremate = arremate;
		this.criador = criador;
		this.vencedor = vencedor;
		this.tipo = tipo;
		this.tipoLance = tipoLance;
	}
	
	public int getLeilaoId() {
		return leilaoId;
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
	public String getCriador() {
		return criador;
	}
	public void setCriador(String criador) {
		this.criador = criador;
	}
	public String getVencedor() {
		return vencedor;
	}
	public void setVencedor(String vencedor) {
		this.vencedor = vencedor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipoLance() {
		return tipoLance;
	}
	public void setTipoLance(String tipoLance) {
		this.tipoLance = tipoLance;
	}


	@Override
	public String toString() {
		return "Informações do Leilao: Data inicio: " + dataIni + ", data fim: " + dataFim + ", tipo: " + tipo + ", tipoLance: " + tipoLance
				+ ", Valor arremate: " + arremate + ", Criador: " + criador + ", Vencedor: " + vencedor +" Id do Lote: " + loteId;
	}
	
	
}
