package it.uniroma3.diadia.ambienti;

import java.util.Random;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String attrezzoSbloccante;
	Random rand = new Random();
	int codice = rand.nextInt(9000)+1000;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;

	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		String[] direzioni = this.getDirezioni();
		if(this.direzioneBloccata.equals(direzione)) {
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
			if (direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;

	}
	
	public int getCodice() {
		return this.codice;
	}
	
	public String getAttrezzoSbloccante() {
		return this.attrezzoSbloccante;
	}
	
	public String getDirezioneBloccata() {
		return this.direzioneBloccata;
	}


	@Override
	public String getDescrizione() {
		StringBuilder ris = new StringBuilder();	
		ris.append("Sei entrato in una STANZA BLOCCATA, sembra che una direzione sia bloccata.\nTrova un modo per sbloccarla.\n");
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


