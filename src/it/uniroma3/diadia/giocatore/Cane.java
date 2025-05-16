package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_CANE_ARRABIATO="GRRRRR GRRRRR";
	Attrezzo att;
	
	public Cane(String nome,String pres,Attrezzo att) {
		super(nome,pres);
		this.att=att;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg=MESSAGGIO_CANE_ARRABIATO;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo att, Partita partita) {
		String msg="BAU BAUUU! Ora sono felice! ";
		if(att.getNome().equals("osso")) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.att);
			this.att=null;
		}
		else
			msg=this.agisci(partita);
		return msg;
	}

}