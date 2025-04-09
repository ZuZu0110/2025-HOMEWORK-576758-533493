package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	Stanza stanzaBuia = new StanzaBuia("stanzaBuia", "torcia");
	Attrezzo torcia = new Attrezzo("torcia",1);
	Labirinto lab = new Labirinto();
	
	@Test
	public void stanzaContieneAttrezzoTest() {
		lab.setStanzaCorrente(stanzaBuia);
		lab.getStanzaCorrente().addAttrezzo(torcia);
		assertEquals(lab.getStanzaCorrente().getDescrizione(), stanzaBuia.getDescrizione());
	}
	
	@Test
	public void stanzaNonContieneAttrezzoTest() {
		lab.setStanzaCorrente(stanzaBuia);
		assertEquals("Qui c'è buio pesto", stanzaBuia.getDescrizione());
	}
}
