package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	Stanza stanza = new Stanza("stanza");
	Attrezzo a = new Attrezzo("a",1);
	Comando prendi = new ComandoPrendi();
	Partita p = new Partita();
	
	@Test
	public void eseguiAttrezzoNonPresenteTest() {
		p.getLabirinto().setStanzaCorrente(stanza);
		prendi.setParametro(null);
		prendi.esegui(p,new IOConsole(null));
		assertEquals(null, prendi.getParametro());
	}
	
	@Test
	public void eseguiAttrezzoPresoTest() {
		p.getLabirinto().setStanzaCorrente(stanza);
		p.getLabirinto().getStanzaCorrente().addAttrezzo(a);
		prendi.setParametro(a.getNome());
		prendi.esegui(p,new IOConsole(null));
		assertFalse(p.getLabirinto().getStanzaCorrente().hasAttrezzo(a.getNome()));
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo(a.getNome()));
	}
}
