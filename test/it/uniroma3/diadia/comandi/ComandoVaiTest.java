package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IO;
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
	private IO io= new IOConsole(null);


	@Test
	public void eseguiDirezioneNullTest() {
		assertEquals(null,vai.getParametro());
	}

	@Test
	public void eseguiDirezioneEsistenteTest() {
		partita.getLabirinto().setStanzaCorrente(stanza);
		partita.getLabirinto().getStanzaCorrente().impostaStanzaAdiacente(Direzione.SUD, stanza1);
		vai.setParametro("SUD");
		vai.esegui(partita,io);
		assertEquals(stanza1,partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void eseguiStanzaNonPresenteTest() {
		partita.getLabirinto().setStanzaCorrente(stanza);
		partita.getLabirinto().getStanzaCorrente().impostaStanzaAdiacente(Direzione.SUD, stanza1);
		vai.setParametro("nord");
		vai.esegui(partita,io);
		assertEquals(stanza,partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void comandoVaiTest() {
		List<String> righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("vai nord");
		righeDaLeggere.add("fine");

		IOSimulator io = IOSimulatorTest.creaSimulazionePartitaEGioca(righeDaLeggere);


		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());

		assertTrue(io.hasNextMessaggio());
		assertEquals("Aula N10", io.nextMessaggio());

		assertTrue(io.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!", io.nextMessaggio());


	}
}
