package it.uniroma3.diadia.comandi;

import java.util.Random;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;

public class ComandoGuarda implements Comando{

	private IO io = new IOConsole();

	@Override
	public void esegui(Partita partita,IO io) {
		this.setIo(io);

		Stanza stanza=partita.getLabirinto().getStanzaCorrente();
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio(partita.getGiocatore().toString());


	}

	@Override
	public void setParametro(String parametro) {


	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "guarda";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		this.io=io;

	}

}
