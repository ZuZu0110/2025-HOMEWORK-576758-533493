package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private static final String MESSAGGIO_DONO="ecco a te un nuovissimo oggetto ";
	
	private static final String MESSAGGIO_SCUSE="Mi dispiace non ho più nulla ";
	private Attrezzo att;
	
	public Mago(String nome,String pres, Attrezzo att) {
		super(nome,pres);
		this.att=att;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.att!=null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.att);
			this.att=null;
			msg=MESSAGGIO_DONO;
		}
		else 
			msg=MESSAGGIO_SCUSE;
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo att, Partita partita) {
		String msg="Ti ho alleggerito la vita ";
		att.setPeso(att.getPeso()/2);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(att);
		return msg;
	}
	

}