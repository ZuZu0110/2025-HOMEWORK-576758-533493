package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{

	private IOConsole io = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando non valido");
		
	}

	@Override
	public void setParametro(String parametro) {
		
	}

}
