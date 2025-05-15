package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	static final public String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine","guarda","saluta","interagisci"};
	private IO io = new IOConsole();
	@Override
	public void esegui(Partita partita,IO io) {
		this.setIo(io);
		for(int i=0;i<elencoComandi.length;i++) {
			System.out.print(elencoComandi[i]+" ");
		}
		io.mostraMessaggio(" ");
		
	}


	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "aiuto";
	}

	public void setIo(IO io) {
		// TODO Auto-generated method stub
		this.io=io;
		
	}
	
	

}
