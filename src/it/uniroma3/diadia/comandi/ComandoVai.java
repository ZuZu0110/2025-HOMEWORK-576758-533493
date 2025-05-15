
package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;

public class ComandoVai  extends AbstractComando{

	private String direzione;
	private IO io;

	@Override
	public void esegui(Partita partita,IO io) {
		this.setIo(io);
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione == null) {

			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(stanzaCorrente instanceof StanzaBloccata && !((StanzaBloccata) stanzaCorrente).hasAttrezzo(((StanzaBloccata) stanzaCorrente).getAttrezzoSbloccante()) && direzione.equals(((StanzaBloccata) stanzaCorrente).getDirezioneBloccata())){
			io.mostraMessaggio("Direzione Bloccata");
			return;
		}
		if(prossimaStanza==null) {

			io.mostraMessaggio("Direzione Inesistente");
			return;
		}
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);

		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);

	}

	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;

	}

}
