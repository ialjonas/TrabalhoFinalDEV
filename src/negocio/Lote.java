package negocio;

import java.util.ArrayList;

public class Lote {

	private ArrayList<Bem> loteBem;
	private static Lote instanciaDeLote = null;

	private Lote() {
		loteBem = new ArrayList<Bem>();
	}

	public static Lote getInstance() {
		if (instanciaDeLote == null) {
			instanciaDeLote = new Lote();
		}
		return instanciaDeLote;
	}

	public boolean cadastrar(Bem b) {
		return loteBem.add(b);
	}
	
	

}
