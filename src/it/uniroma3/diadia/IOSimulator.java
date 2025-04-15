package it.uniroma3.diadia;

public class IOSimulator implements IO{

	private String[] righeLette;
	private int indiceRigheLette;
	private String[] messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;
	
	
	public IOSimulator(String[] righeDaLeggere) {
		
		this.indiceRigheLette =0;
		this.righeLette = righeDaLeggere;
		this.indiceMessaggiMostrati = 0;
		this.indiceMessaggiProdotti = 0;
		this.messaggiProdotti = new String[42*23];
	}


	public String[] getMessaggiProdotti() {
		return messaggiProdotti;
	}

	public void setMessaggiProdotti(String[] messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}

	@Override
	public String leggiRiga() {
	    String riga = null;
	    if (this.righeLette != null) {
	        riga = this.righeLette[indiceRigheLette];
	        this.indiceRigheLette++;
	    }
	    return riga;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		
			this.messaggiProdotti[indiceMessaggiProdotti] = messaggio;
			this.indiceMessaggiProdotti++;
		
	}

	public String nextMessaggio() {
		
			return this.messaggiProdotti[this.indiceMessaggiMostrati++];
		
	}

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}

}
