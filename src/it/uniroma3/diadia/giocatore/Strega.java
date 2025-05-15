package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;

public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_STREGA="VIA DA QUA! ";
	
	public Strega(String nome,String pres) {
		super(nome,pres);
	}
	
	@Override
	public String agisci(Partita partita) {
		// TODO Auto-generated method stub
		String msg=MESSAGGIO_STREGA;
		if(haSalutato()) {
			int max=0;
			String dir=null;
			for(String d :partita.getLabirinto().getStanzaCorrente().getDirezioni()) {
				if(max<partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(d).getNumeroAttrezzi()) {
					max=partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(d).getNumeroAttrezzi();
					dir=d;
				}
			}
			if(dir!=null)
				partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(dir));
		}
		else {
			int min=partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi();
			String dir=null;
			for(String d :partita.getLabirinto().getStanzaCorrente().getDirezioni()) {
				if(min>partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(d).getNumeroAttrezzi()) {
					min=partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(d).getNumeroAttrezzi();
					dir=d;
				}
			}
			if(dir!=null)
				partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(dir));
		}
		return msg;
	}

}
