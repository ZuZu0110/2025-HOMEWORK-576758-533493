package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando{

	private String attrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita,IO io) {
		this.setIo(io);
		Borsa b = partita.getGiocatore().getBorsa();
		Stanza stanzaCorr = partita.getLabirinto().getStanzaCorrente();
		if(stanzaCorr.hasAttrezzo(attrezzo)) {
			Attrezzo a = stanzaCorr.getAttrezzo(attrezzo);
			
			if(stanzaCorr instanceof StanzaMagica && stanzaCorr.cercaAttrezzo(attrezzo)>1) {
				Attrezzo c=stanzaCorr.getAttrezzo(attrezzo);
				StringBuilder nomeO = new StringBuilder(c.getNome());
				nomeO = nomeO.reverse();
				int pesoO = c.getPeso()/2;
				a = new Attrezzo(nomeO.toString(),pesoO);
			}
			if(b.addAttrezzo(a)) {
				stanzaCorr.removeAttrezzo(stanzaCorr.getAttrezzo(attrezzo));
				io.mostraMessaggio(attrezzo + " aggiunto alla borsa");
			}
			else
				io.mostraMessaggio("borsa piena");
		}
		else
			io.mostraMessaggio("atrezzo inesistente");
		
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;

	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
	}

	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		this.io=io;
		
	}

}
