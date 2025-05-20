package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ConfigurazioneProperties;

public class Giocatore {

	//static final private int CFU_INIZIALI = 20;
	public Borsa borsa;
	public int cfu;
	
	public Giocatore() {
		this.cfu = ConfigurazioneProperties.getCfuIniziali();
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("cfu: "+ this.getCfu());
		return s.toString();
		
	}
}
