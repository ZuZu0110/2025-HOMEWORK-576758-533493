package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_STREGA="VIA DA QUA! ";
	Attrezzo att;
	
	public Strega(String nome,String pres) {
		super(nome,pres);
	}
	
	@Override
	public String agisci(Partita partita) {	
		String msg=MESSAGGIO_STREGA;
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		if(haSalutato()) {
			int max=0;
			String dir=null;
			for(Direzione d :stanzaCorrente.getDirezioni()) {
				if(max<stanzaCorrente.getStanzaAdiacente(d).getNumeroAttrezzi()) {
					max=stanzaCorrente.getStanzaAdiacente(d).getNumeroAttrezzi();
					dir=d.name();
				}
			}
			if(dir!=null)
				partita.getLabirinto().setStanzaCorrente(stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(dir)));
		}
		else {
			int min=Integer.MAX_VALUE;
			String dir=null;
			for(Direzione d :stanzaCorrente.getDirezioni()) {
				if(min>stanzaCorrente.getStanzaAdiacente(d).getNumeroAttrezzi()) {
					min=stanzaCorrente.getStanzaAdiacente(d).getNumeroAttrezzi();
					dir=d.name();
				}
			}
			if(dir!=null)
				partita.getLabirinto().setStanzaCorrente(stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(dir)));
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo att, Partita partita) {
		String msg="HIHIHI! ";
		this.setAttrezzo(att);
		return msg;
	}

}