package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	protected String par;

	public abstract void esegui(Partita partita,IO io);
	public void setParametro(String parametro) {
		this.par=parametro;
	}

	public abstract String getNome();
	public String getParametro() {
		return this.par;
	}
	public abstract void setIo(IO io);
}
