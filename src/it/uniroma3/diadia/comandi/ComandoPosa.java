package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{

	private IOConsole io= new IOConsole(); 
	private String attrezzo;
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(a);
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
		io.mostraMessaggio(attrezzo+ " inserito nella stanza");
		
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
		
	}

}
