package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	public Stanza stanzaCorrente;
	public Stanza stanzaVincente;
	public Stanza iniziale;
	
	public Labirinto() {}
	
	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFile);
		caricatore.carica();

		this.stanzaCorrente = caricatore.getStanzaIniziale();
		this.stanzaVincente = caricatore.getStanzaVincente();
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	
	public Stanza getIniziale() {
		return this.iniziale;
	}
	
	public void setIniziale(Stanza iniziale) {
		this.iniziale=iniziale;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza vincente) {
		this.stanzaVincente=vincente;
	}
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public static class LabirintoBuilder{
		private Labirinto labirinto;
		public Map<String, Stanza> stanze;
		public Stanza stanzaCorrente;
		
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.stanze = new HashMap<>();
		}
		
		public LabirintoBuilder addStanzaIniziale(String iniziale) {
			Stanza s = new Stanza(iniziale);
			this.labirinto.iniziale = s;
			this.labirinto.stanzaCorrente = s;
			this.stanzaCorrente = s;
			this.stanze.put(iniziale, s);
			return this;
		}
		
		public LabirintoBuilder addStanza(String stanza) {
			Stanza s = new Stanza(stanza);
			this.stanzaCorrente = s;
			this.stanze.put(stanza, s);
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String vincente) {
			Stanza s = new Stanza(vincente);
			this.labirinto.stanzaVincente = s;
			this.stanzaCorrente = s;
			this.stanze.put(vincente, s);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			if(this.stanzaCorrente != null)
				this.stanzaCorrente.addAttrezzo(new Attrezzo(nome,peso));
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String nome1, String nome2, String direzione) {
			Stanza s1 = this.stanze.get(nome1);
			Stanza s2 = this.stanze.get(nome2);
			if(s1 != null && s2 != null)
				s1.impostaStanzaAdiacente(direzione, s2);
			return this;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}		
	}
}	
	
	
	
	
	
	
	
//	public void creaLabirinto() {
//	/* crea gli attrezzi */
//	Attrezzo lanterna = new Attrezzo("lanterna",3);
//	Attrezzo osso = new Attrezzo("osso",1);
//	Attrezzo torcia = new Attrezzo("torcia",2);
//	Attrezzo piedeDiPorco = new Attrezzo("piedediporco", 4);
//	Attrezzo sasso = new Attrezzo("sasso", 7);
//	Attrezzo libro = new Attrezzo("libro", 1);
//	Attrezzo pc = new Attrezzo("pc", 2);
//	Attrezzo biglietto = new Attrezzo("biglietto",1);
//	Attrezzo bacchetta = new Attrezzo("bacchetta",1);
//	Attrezzo chiave = new Attrezzo("chiave",1);
//	
//	/* crea stanze del labirinto */
//	Stanza atrio = new Stanza("Atrio");
//	Stanza aulaN11 = new Stanza("Aula N11");
//	Stanza aulaN10 = new Stanza("Aula N10");
//	Stanza laboratorio = new Stanza("Laboratorio Campus");
//	Stanza biblioteca = new Stanza("Biblioteca");
//	Stanza N3 = new StanzaBuia("Aula N3","torcia");
//	Stanza N14 = new StanzaBloccata("Aula N14","nord","biglietto");
//	Stanza N8 = new StanzaMagica("Aula N8");
//	
//	/* crea personaggi*/
//	Strega strega = new Strega("Sabrina","Sono una strega che abita questo labirinto ");
//	Mago mago = new Mago("Mago Merlino","Sono un antico e potente mago ",bacchetta);
//	Cane cane = new Cane("Scooby Doo","BAU BAU",chiave);
//	
//	/* collega le stanze */
//	atrio.impostaStanzaAdiacente("nord", N14);
//	atrio.impostaStanzaAdiacente("est", aulaN11);
//	atrio.impostaStanzaAdiacente("sud", aulaN10);
//	atrio.impostaStanzaAdiacente("ovest", laboratorio);
//	aulaN11.impostaStanzaAdiacente("est", laboratorio);
//	aulaN11.impostaStanzaAdiacente("ovest", atrio);
//	aulaN10.impostaStanzaAdiacente("nord", atrio);
//	aulaN10.impostaStanzaAdiacente("est", aulaN11);
//	aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
//	aulaN10.impostaStanzaAdiacente("sud", N8);
//	laboratorio.impostaStanzaAdiacente("est", atrio);
//	laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
//	laboratorio.impostaStanzaAdiacente("nord", N3);
//	N3.impostaStanzaAdiacente("sud", laboratorio);
//	N3.impostaStanzaAdiacente("est", N14);
//	biblioteca.impostaStanzaAdiacente("sud", N14);
//	N14.impostaStanzaAdiacente("sud", atrio);
//	N14.impostaStanzaAdiacente("nord", biblioteca);
//	N14.impostaStanzaAdiacente("ovest", N3);
//	N8.impostaStanzaAdiacente("nord", aulaN10);
//	
//
//    /* pone gli attrezzi nelle stanze */
//	aulaN10.addAttrezzo(lanterna);
//	atrio.addAttrezzo(osso);
//	aulaN11.addAttrezzo(torcia);
//	N3.addAttrezzo(piedeDiPorco);
//	N3.addAttrezzo(biglietto);
//	N8.addAttrezzo(sasso);	
//	N8.addAttrezzo(libro);		
//	N8.addAttrezzo(pc);
//	
//	
//	laboratorio.setPersonaggio(mago);
//	atrio.setPersonaggio(cane);
//	N3.setPersonaggio(strega);
//
//	// il gioco comincia nell'atrio
//    stanzaCorrente = atrio;
//    iniziale=atrio;
//	stanzaVincente = biblioteca;
//}

