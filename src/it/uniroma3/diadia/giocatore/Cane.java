package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_CANE_ARRABIATO="GRRRRR GRRRRR";
	
	public Cane(String nome,String pres) {
		super(nome,pres);
	}
	@Override
	public String agisci(Partita partita) {
		// TODO Auto-generated method stub
		String msg=MESSAGGIO_CANE_ARRABIATO;
		
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		return msg;
	}

}
