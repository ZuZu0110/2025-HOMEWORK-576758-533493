package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	Stanza stanzaBloccata = new StanzaBloccata("stanzaBloccata","nord","piedediporco");
	Stanza stanza = new Stanza("stanza");
	Attrezzo piede = new Attrezzo("piedediporco",1);
	Labirinto lab = new Labirinto();
	
	@Test
	public void stanzaAdiacenteNull() {
		lab.setStanzaCorrente(stanzaBloccata);
		lab.getStanzaCorrente().impostaStanzaAdiacente("sud", null);
		assertEquals(null,lab.getStanzaCorrente().getStanzaAdiacente("sud"));
	}
	
	@Test
	public void stanzaAdiacenteBloccata() {
		lab.setStanzaCorrente(stanzaBloccata);
		lab.getStanzaCorrente().impostaStanzaAdiacente("nord", stanza);
		assertEquals(null,lab.getStanzaCorrente().getStanzaAdiacente("nord"));
	}
	@Test
	public void stanzaAdiacenteSbloccata() {
		lab.setStanzaCorrente(stanzaBloccata);
		lab.getStanzaCorrente().addAttrezzo(piede);
		lab.getStanzaCorrente().impostaStanzaAdiacente("nord", stanza);
		assertEquals(stanza,lab.getStanzaCorrente().getStanzaAdiacente("nord"));
	}
}
