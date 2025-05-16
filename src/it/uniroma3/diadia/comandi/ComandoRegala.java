package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{

	private static final String MESSAGGIO_CON_CHI = "A chi vuoi fare un regalo? ";
	private IO io; 
	private String mes;
	private String attrezzo;
	
	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio pers;
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
		pers=partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(pers!=null && a!=null) {
			this.mes=pers.riceviRegalo(a, partita);
			if(!this.mes.equals("GRRRRR GRRRRR"))
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
			io.mostraMessaggio(this.mes);
		}
		else
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
		
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;

	}
	
	@Override
	public String getParametro() {
		return this.attrezzo;
	}

	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}

}