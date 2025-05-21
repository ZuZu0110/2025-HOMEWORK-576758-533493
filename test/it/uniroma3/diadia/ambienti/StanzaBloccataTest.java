package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	Stanza stanzaBloccata = new StanzaBloccata("stanzaBloccata",Direzione.nord,"piedediporco");
	Stanza stanza = new Stanza("stanza");
	Attrezzo piede = new Attrezzo("piedediporco",1);
	Labirinto lab = new Labirinto();
	
	@Test
	public void stanzaAdiacenteNull() {
		lab.setStanzaCorrente(stanzaBloccata);
		lab.getStanzaCorrente().impostaStanzaAdiacente(Direzione.sud, null);
		assertEquals(null,lab.getStanzaCorrente().getStanzaAdiacente(Direzione.sud));
	}
	
	@Test
	public void stanzaAdiacenteBloccata() {
		lab.setStanzaCorrente(stanzaBloccata);
		lab.getStanzaCorrente().impostaStanzaAdiacente(Direzione.nord, stanza);
		assertEquals(null,lab.getStanzaCorrente().getStanzaAdiacente(Direzione.nord));
	}
	@Test
	public void stanzaAdiacenteSbloccata() {
		lab.setStanzaCorrente(stanzaBloccata);
		lab.getStanzaCorrente().addAttrezzo(piede);
		lab.getStanzaCorrente().impostaStanzaAdiacente(Direzione.nord, stanza);
		assertEquals(stanza,lab.getStanzaCorrente().getStanzaAdiacente(Direzione.nord));
	}
}
