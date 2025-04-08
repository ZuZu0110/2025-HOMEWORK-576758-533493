package it.uniroma3.diadia.comandi;

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
		Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(attrezzo);
		b.addAttrezzo(a);
		partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
		io.mostraMessaggio(attrezzo + " aggiunto alla borsa");
		
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
		
	}

}
