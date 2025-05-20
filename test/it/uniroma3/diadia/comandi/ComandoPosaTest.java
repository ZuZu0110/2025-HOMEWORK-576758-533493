package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	
	Stanza stanza = new Stanza("stanza");
	Attrezzo a = new Attrezzo("a",1);
	Comando posa = new ComandoPosa();
	Partita p = new Partita();

	
	@Test
	public void eseguiAttrezzoNonPresenteTest() {
		p.getLabirinto().setStanzaCorrente(stanza);
		posa.setParametro(null);
		posa.esegui(p,new IOConsole(null));
		assertEquals(null, posa.getParametro());
	}
	
	@Test
	public void eseguiAttrezzoPresoTest() {
		p.getLabirinto().setStanzaCorrente(stanza);
		p.getGiocatore().getBorsa().addAttrezzo(a);
		posa.setParametro(a.getNome());
		posa.esegui(p,new IOConsole(null));
		assertTrue(p.getLabirinto().getStanzaCorrente().hasAttrezzo(a.getNome()));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo(a.getNome()));
	}
}
