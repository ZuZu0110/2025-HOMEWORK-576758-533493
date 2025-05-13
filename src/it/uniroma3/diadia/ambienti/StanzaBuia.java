package it.uniroma3.diadia.ambienti;

//import it.uniroma3.diadia.IO;
//import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{

	private String nomeAttrezzo;

	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzo = nomeAttrezzo;
	}

	@Override
	public String getDescrizione() {
		StringBuilder ris = new StringBuilder();
		for(Attrezzo a : this.getAttrezzi()) {
			if(a!=null) {
				if(a.getNome().equals(this.nomeAttrezzo)) {
					ris.append("Adesso puoi vedere...\n");
					ris.append(this.toString());
					return ris.toString();	
				}
			}
		}
		return ris.append("Qui c'è buio pesto").toString();
	}
}
