package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{

	public Stanza iniziale;// Stanza iniziale del labirinto
    public Stanza corrente; // La stanza attualmente in focus per modifiche (es. aggiungere attrezzi)
    public Stanza finale;   // Stanza vincente del labirinto
//	public Labirinto lab;  // Labirinto

	//mappa che tine traccia delle stanza inserite
	public Map<String, Stanza> stanze;

	public LabirintoBuilder(){	
		this.stanze = new HashMap<>();		
	}

	/**
	 * metodo che inserisce la stanza iniziale del labirinto
	 * @param iniziale stringa nome della stanza
	 * @return riferimento all'oggetto labirinto nella classe
	 */
	public LabirintoBuilder addStanzaIniziale(String iniziale){
		Stanza s= new Stanza(iniziale);
		
		this.iniziale = s;
		this.corrente = s;
		this.stanze.put(iniziale, s);
		this.setStanzaCorrente(this.corrente);
		return this;
	}
	/**
	 * metodo che inserisce una stanza nel labirinto
	 * @param stanza stringa nome stanza
	 * @return riferimento all'oggetto labirinto nella classe
	 */
	public LabirintoBuilder addStanza(String stanza){
		Stanza s= new Stanza(stanza);
		this.corrente=s;
		this.stanze.put(stanza, s);
		this.setIniziale(iniziale);
		this.setStanzaCorrente(this.corrente);
		return this;
	}

	/**
	 * metodo che inserisce la stanza vincente del labirinto
	 * @param vincente stringa nome della stanza
	 * @return riferimento all'oggetto labirinto nella classe
	 */
	public LabirintoBuilder addStanzaVincente(String vincente){
		Stanza s = new Stanza(vincente);
		this.finale= s;
		this.corrente=s;
		this.stanze.put(vincente, s);
		this.setStanzaVincente(this.finale);
		return this;
	}

	/**
	 * metodo che aggiunge all'ultima stanza aggiunta un attrezzo
	 * @param nome dell'attrezzo
	 * @param peso dell'attrezzo
	 * @return riferimento all'oggetto labirinto nella classe
	 */
	public LabirintoBuilder addAttrezzo(String nome,Integer peso){
		if(this.corrente!=null) {
			this.corrente.addAttrezzo(new Attrezzo(nome,peso));
		}
		return this;
	}

	/**
	 * metodo che collega la prima stanza alla seconda tramite la direzione corrispondente
	 * @param s1 nome stanza 1
	 * @param s2 nome stanza 2
	 * @param direzione
	 * @return riferimento all'oggetto labirinto nella classe
	 */
	public LabirintoBuilder adAdiacenza(String s1,String s2,String direzione){
		if(s1!=null && s2!=null) {
			Stanza st1=this.stanze.get(s1);
			Stanza st2=this.stanze.get(s2);
			st1.impostaStanzaAdiacente(direzione, st2);
		}
			
		return this;
	}
	/**
	 * 
	 * @return riferimento all'oggetto labirinto nella classe
	 */
	public Labirinto getLabirinto(){
		return this;
	}
}
