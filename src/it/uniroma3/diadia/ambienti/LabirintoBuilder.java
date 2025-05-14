package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{

	public Stanza iniziale;
	public Stanza corrente;
	public Stanza finale;
	public Labirinto lab;

	public Map<String, Stanza> stanze;

	public LabirintoBuilder(){
		this.lab=new Labirinto();
		this.stanze = new HashMap<>();
		
	}

	public LabirintoBuilder addStanzaIniziale(String iniziale){
		Stanza s= new Stanza(iniziale);
		
		this.iniziale = s;
		this.corrente = s;
		this.stanze.put(iniziale, s);
		this.lab.setStanzaCorrente(this.corrente);
		return this;
	}

	public LabirintoBuilder addStanza(String stanza){
		Stanza s= new Stanza(stanza);
		this.corrente=s;
		this.stanze.put(stanza, s);
		this.lab.setIniziale(iniziale);
		this.lab.setStanzaCorrente(this.corrente);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String vincente){
		Stanza s = new Stanza(vincente);
		this.finale= s;
		this.corrente=s;
		this.stanze.put(vincente, s);
		this.lab.setStanzaVincente(this.finale);
		return this;
	}


	public LabirintoBuilder addAttrezzo(String nome,Integer peso){
		if(this.corrente!=null) {
			this.corrente.addAttrezzo(new Attrezzo(nome,peso));
		}
		return this;
	}

	public LabirintoBuilder adAdiacenza(String s1,String s2,String direzione){
		if(s1!=null && s2!=null) {
			Stanza st1=this.stanze.get(s1);
			Stanza st2=this.stanze.get(s2);
			st1.impostaStanzaAdiacente(direzione, st2);
		}
			
		return this;
	}
	public Labirinto getLabirinto(){
		return this.lab;
	}
}
