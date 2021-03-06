package negocio;

public class Leilao {
	private int loteId, leilaoId;
	private double arremate;
	private String vencedor, tipo, tipoLance, dataIni, dataFim;
	private boolean status;
	
	public Leilao(int loteId, String dataIni, String dataFim, double arremate, String vencedor, String tipo,
			String tipoLance) {
		super();
		this.loteId = loteId;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.arremate = arremate;
		this.vencedor = vencedor;
		this.tipo = tipo;
		this.tipoLance = tipoLance;
		//this.status = status;
	}

	public Leilao(int leilaoId, int loteId, String dataIni, String dataFim, double arremate, String vencedor,
			String tipo, String tipoLance) {
		super();
		this.leilaoId = leilaoId;
		this.loteId = loteId;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.arremate = arremate;
		this.vencedor = vencedor;
		this.tipo = tipo;
		this.tipoLance = tipoLance;
	}
	
	public void ativa(){
		status=true;
	}
	
	public void desativa(){
		status=false;
	}
	
	public boolean getStatus(){
		return status;
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

	public String getDataIni() {
		return dataIni;
	}

	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public double getArremate() {
		return arremate;
	}

	public void setArremate(double arremate) {
		this.arremate = arremate;
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
		return "Informações do Leilao: Data inicio: " + dataIni + ", data fim: " + dataFim + ", tipo: " + tipo
				+ ", tipoLance: " + tipoLance + ", Valor arremate: " + arremate + ", Vencedor: " + vencedor
				+ " Id do Lote: " + loteId;
	}

}
