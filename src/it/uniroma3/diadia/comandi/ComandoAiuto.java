package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine","guarda"};
	private IOConsole io = new IOConsole();
	@Override
	public void esegui(Partita partita) {
		for(int i=0;i<elencoComandi.length;i++) {
			System.out.print(elencoComandi[i]+" ");
		}
		io.mostraMessaggio(" ");
		
	}

	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "aiuto";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		
	}
	
	

}
