package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando{

	private String attrezzo;
	private IOConsole io = new IOConsole();
	@Override
	public void esegui(Partita partita) {
		Borsa b = partita.getGiocatore().getBorsa();
		if(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(attrezzo)) {
			Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(attrezzo);
			b.addAttrezzo(a);
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
			io.mostraMessaggio(attrezzo + " aggiunto alla borsa");
		}
		else
			io.mostraMessaggio("attrezzo inesistente");
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;

	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
	}

	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		
	}

}
