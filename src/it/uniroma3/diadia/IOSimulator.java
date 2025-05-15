package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{

	private List<String> righeLette;
	private int indiceRigheLette;
	private List<String> messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;
	
	
	public IOSimulator(List<String> righeDaLeggere) {
		
		this.indiceRigheLette =0;
		this.righeLette = righeDaLeggere;
		this.indiceMessaggiMostrati = 0;
		this.indiceMessaggiProdotti = 0;
		this.messaggiProdotti = new ArrayList<String>();
	}


	public List<String> getMessaggiProdotti() {
		return messaggiProdotti;
	}

	public void setMessaggiProdotti(List<String> messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}

	@Override
	public String leggiRiga() {
	    String riga = null;
	    if (this.righeLette != null) {
	    	riga= this.righeLette.get(indiceRigheLette);
	        this.indiceRigheLette++;
	    }
	    return riga;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		
			this.messaggiProdotti.add(indiceMessaggiProdotti, messaggio);
			this.indiceMessaggiProdotti++;
		
	}

	public String nextMessaggio() {
		
			return this.messaggiProdotti.get(this.indiceMessaggiMostrati++);
		
	}

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}

}
