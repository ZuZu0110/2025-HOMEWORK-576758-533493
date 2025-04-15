package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
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

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "posa";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return this.attrezzo;
	}

	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		
	}

}
