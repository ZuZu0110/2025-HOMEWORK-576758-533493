package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{

	private IO io = new IOConsole();
	
	@Override
	public void esegui(Partita partita,IO io) {
		this.setIo(io);
		io.mostraMessaggio("Comando non valido");
		
	}

	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		this.io=io;
		
	}

}
