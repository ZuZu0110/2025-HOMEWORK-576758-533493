package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartitaTest {

	private Partita partita;

	@BeforeEach
	public void setUp(){
		this.partita = new Partita();
	}

	// TEST PER isFinita()
	@Test
	void testPartitaNonFinitaAllInizio() {
		assertFalse(partita.isFinita(), "La partita non dovrebbe essere finita all'inizio");
	}

	@Test
	void testPartitaFinitaDopoSetFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita(), "La partita dovrebbe essere segnata come finita");
	}

	@Test
	void testPartitaFinitaQuandoCFUSonoZero() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita(), "La partita dovrebbe essere finita quando i CFU sono zero");
	}

	// TEST PER vinta()
	@Test
	void testPartitaVintaQuandoInStanzaVincente() {
		partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.vinta(), "La partita dovrebbe essere vinta quando si è nella stanza vincente");
	}

	@Test
	void testPartitaNonVintaInStanzaNonVincente() {
		assertFalse(partita.vinta(), "La partita non dovrebbe essere vinta se non si è nella stanza vincente");
	}

	@Test
	void testPartitaVintaRendePartitaFinita() {
		partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.isFinita(), "Se la partita è vinta, dovrebbe essere anche finita");
	}
}
