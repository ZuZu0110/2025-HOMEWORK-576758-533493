package it.uniroma3.diadia.ambienti;

import java.util.Random;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{

	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;
	Random rand = new Random();
	int codice = rand.nextInt(9000)+1000;

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;

	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;
		Direzione[] direzioni = this.getDirezioni();
		if(this.direzioneBloccata == direzione) {
			for(Attrezzo a : this.getAttrezzi()) {
				if(a!=null) {
					if(a.getNome().equals(attrezzoSbloccante)) {
						for(int i=0; i<this.numeroStanzeAdiacenti; i++)
							if (direzioni[i].equals(direzione))
								stanza = this.stanzeAdiacenti[i];
						return stanza;
					}	
				}
			}
			return stanza;
		}
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			if (direzioni[i] == direzione)
				stanza = this.stanzeAdiacenti[i];
		return stanza;

	}
	
	public int getCodice() {
		return this.codice;
	}
	
	public String getAttrezzoSbloccante() {
		return this.attrezzoSbloccante;
	}
	
	public Direzione getDirezioneBloccata() {
		return this.direzioneBloccata;
	}


	@Override
	public String getDescrizione() {
		StringBuilder ris = new StringBuilder();	
		ris.append("Sei entrato in una STANZA BLOCCATA, sembra che una direzione sia bloccata.\nSembra tu abbia bisogno di un codice.\n");
		ris.append(this.toString());
		if(this.hasAttrezzo(attrezzoSbloccante)) {
			ris.append("\nCodice della stanza: "+this.codice+"\nCodice del biglietto: "+this.codice);
			ris.append("\nCodici uguali ,direzione nord sbloccata");
		}
		else {
			ris.append("\nAttrezzo sbloccante: ?");
			ris.append("\nDirezione bloccata: "+this.direzioneBloccata);
		}
		
		return ris.toString();
	}
}


