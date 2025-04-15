package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.IOSimulatorTest;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

	Stanza stanza = new Stanza("stanza");
	Stanza stanza1 = new Stanza("stanza1");
	Comando vai = new ComandoVai();
	Partita partita = new Partita();




	@Before
	public void setUp() {
		vai.setIo(new IOConsole());
	}


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

	@Test
	public void comandoVaiTest() {
		String[] righeDaLeggere = {"vai sud","fine"};
		IOSimulator io = IOSimulatorTest.creaSimulazionePartitaEGioca(righeDaLeggere);

		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());

		assertTrue(io.hasNextMessaggio());
		assertEquals("Aula N10", io.nextMessaggio());


		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!", io.nextMessaggio());


	}
}
