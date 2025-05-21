package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.ConfigurazioneProperties;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private Stanza stanza = new Stanza("n10");
	private Stanza stanza1 = new Stanza("n11");
	private Attrezzo attrezzo = new Attrezzo("Lampada", 1);
	
	
	// TEST PER addAttrezzo
	
	@Test
	public void testAddAttrezzoAggiunto() {
		assertTrue(stanza.addAttrezzo(attrezzo), "Attrezzo aggiunto correttamente");
	}
	
	@Test
	public void testAddAttrezzoOltreLimite() {
		for(int i=0;i<10;i++) {
			stanza.addAttrezzo(new Attrezzo("Attrezzo"+i, 1));
		}
		assertFalse(stanza.addAttrezzo(new Attrezzo("Forchetta", 1)), "non dovrebbero essere aggiunti più di 10 elementi");
	}
	
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(stanza.addAttrezzo(null), "non si può aggiungere un oggetto null");
	}
	
	// TEST PER removeAttrezzo
	@Test
	public void testRemoveAttrezzoPresente() {
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.removeAttrezzo(attrezzo), "l'oggetto dovrebbe essere stato eliminato");
		assertFalse(stanza.hasAttrezzo("Lampada"), "l'oggetto non dovrebbe essere presente nella stanza");
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		assertFalse(stanza.removeAttrezzo(attrezzo), "non si può rimuovere un oggetto non presente nella stanza");
	}
	
	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(stanza.removeAttrezzo(null), "non si potrebbe rimuovere un oggetto null");
	}
	
	// TEST PER hasAttrezzo
	@Test
	public void testHasAttrezzoPresente() {
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo("Lampada"), "l'oggetto lampada è contenuto nella stanza");
	}
	
	@Test
	public void testHasAttrezzoNonPresente() {
		stanza.addAttrezzo(attrezzo);
		assertFalse(stanza.hasAttrezzo("osso"), "l'oggetto osso non è presente nella stanza");
	}
	
	@Test
	public void testHasAttrezzoDuplicato() {
		Attrezzo nuovo = new Attrezzo("Lampada", 1);
		stanza.addAttrezzo(attrezzo);
		stanza.addAttrezzo(nuovo);
		assertEquals(stanza.hasAttrezzo(nuovo.getNome()), stanza.hasAttrezzo(attrezzo.getNome()));
		
	}
	
	// TEST PER impostaStanzaAdiacente
	@Test
	public void testImpostaStanzaAdiacenteNuovaDirezione() {
		stanza.impostaStanzaAdiacente(Direzione.nord, stanza1);
		assertEquals(stanza1, stanza.getStanzaAdiacente(Direzione.nord), "la stanza adiacente di stanza in direzione nord deve essere stanza1");
	}
	
	@Test
	public void testImpostaStanzaAdiacenteAggiornamentoStanzaEsistente() {
		Stanza nuova = new Stanza("nuovaStanza");
		stanza.impostaStanzaAdiacente(Direzione.nord, stanza1);
		stanza.impostaStanzaAdiacente(Direzione.nord, nuova);
		assertEquals(nuova, stanza.getStanzaAdiacente(Direzione.nord), "la nuova Stanza adiacente in direzione nord divente 'nuova'");
	}
	
	@Test
	public void testImpostaStanzaAdiacenteSuperaLimiteDirezioni() {
		for(Direzione dir : Direzione.values()) {
			stanza.impostaStanzaAdiacente(dir, new Stanza("Stanza "+dir));
		}
		Stanza extra = new Stanza("extra");
		assertEquals(ConfigurazioneProperties.getNumeroMassimoDirezioni(), stanza.getDirezioni());
	}
}
