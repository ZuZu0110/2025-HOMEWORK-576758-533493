package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String attrezzoSbloccante;

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


	@Override
	public String getDescrizione() {
		StringBuilder ris = new StringBuilder();		
		ris.append(this.toString());
		ris.append("\nAttrezzo sbloccante: "+this.attrezzoSbloccante);
		ris.append("\nDirezione bloccata: "+this.direzioneBloccata);
		return ris.toString();
	}
}


