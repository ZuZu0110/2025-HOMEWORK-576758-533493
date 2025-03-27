package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoTest {

	private Labirinto labirinto = new Labirinto();
	
	
	@Test
	public void testStanzaCorrente() {
		labirinto.creaLabirinto();
		assertNotNull(labirinto.getStanzaCorrente(), "la stanza corrente non dovrebbe essere nulla");
		assertEquals("Atrio", labirinto.getStanzaCorrente().getNome(), "la stanza corrente dovrebbe essere l'atrio");
	}
	
	@Test
	public void testSetStanzaCorrente() {
		Stanza aulaN11 = new Stanza("Aula n11");
		labirinto.setStanzaCorrente(aulaN11);
		assertEquals(aulaN11, labirinto.getStanzaCorrente(), "La stanza corrente dovrebbe essere l'aula n11");
	}
	
	@Test
	public void testAttrezziStanza() {
		labirinto.creaLabirinto();
		Stanza atrio = labirinto.getStanzaCorrente();
		assertTrue(atrio.hasAttrezzo("osso"), "l'atrio dovrebbe contenere l'osso");
		
		Stanza aulaN10 = new Stanza("Aula n10");
		aulaN10.addAttrezzo(new Attrezzo("lanterna", 3));
		assertTrue(aulaN10.hasAttrezzo("lanterna"), "l'aula n10 dovrebbe contenere la lanterna");
	}
}
