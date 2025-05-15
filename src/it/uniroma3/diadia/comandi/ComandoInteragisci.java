package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{

	private static final String MESSAGGIO_CON_CHI = "Con chi dovrai interagire? ";
	private IO io; 
	private String mes;
	
	@Override
	public void esegui(Partita partita, IO io) {
		// TODO Auto-generated method stub
		AbstractPersonaggio pers;
		pers=partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(pers!=null) {
			this.mes=pers.agisci(partita);
			io.mostraMessaggio(this.mes);
		}
		else
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "interagisci";
	}

	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		this.io=io;
		
	}

}
