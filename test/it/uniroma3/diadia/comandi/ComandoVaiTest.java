package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

	Stanza stanza = new Stanza("stanza");
	Stanza stanza1 = new Stanza("stanza1");
	Comando vai = new ComandoVai();
	Partita partita = new Partita();
	
	@Test
	public void eseguiDirezioneNullTest() {
		assertEquals(null,vai.getParametro());
	}
	
	@Test
	public void eseguiDirezioneEsistenteTest() {
		partita.getLabirinto().setStanzaCorrente(stanza);
		partita.getLabirinto().getStanzaCorrente().impostaStanzaAdiacente("sud", stanza1);
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void eseguiStanzaNonPresenteTest() {
		partita.getLabirinto().setStanzaCorrente(stanza);
		partita.getLabirinto().getStanzaCorrente().impostaStanzaAdiacente("sud", stanza1);
		vai.setParametro("nord");
		vai.esegui(partita);
		assertEquals(stanza,partita.getLabirinto().getStanzaCorrente());
	}
}
