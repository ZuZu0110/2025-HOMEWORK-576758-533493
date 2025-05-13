package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	public Stanza stanzaCorrente;
	public Stanza stanzaVincente;

	
	public void creaLabirinto() {
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo torcia = new Attrezzo("torcia",2);
		Attrezzo piedeDiPorco = new Attrezzo("piedediporco", 4);
		Attrezzo sasso = new Attrezzo("sasso", 7);
		Attrezzo libro = new Attrezzo("libro", 1);
		Attrezzo pc = new Attrezzo("pc", 2);
		Attrezzo biglietto = new Attrezzo("biglietto",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		Stanza N3 = new StanzaBuia("Aula N3","torcia");
		Stanza N14 = new StanzaBloccata("Aula N14","nord","biglietto");
		Stanza N8 = new StanzaMagica("Aula N8");
		
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", N14);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN10.impostaStanzaAdiacente("sud", N8);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		laboratorio.impostaStanzaAdiacente("nord", N3);
		N3.impostaStanzaAdiacente("sud", laboratorio);
		N3.impostaStanzaAdiacente("est", N14);
		biblioteca.impostaStanzaAdiacente("sud", N14);
		N14.impostaStanzaAdiacente("sud", atrio);
		N14.impostaStanzaAdiacente("nord", biblioteca);
		N14.impostaStanzaAdiacente("ovest", N3);
		N8.impostaStanzaAdiacente("nord", aulaN10);
		

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		aulaN11.addAttrezzo(torcia);
		N3.addAttrezzo(piedeDiPorco);
		N3.addAttrezzo(biglietto);
		N8.addAttrezzo(sasso);
		
		N8.addAttrezzo(libro);
		
		N8.addAttrezzo(pc);
		
		
		

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
}
