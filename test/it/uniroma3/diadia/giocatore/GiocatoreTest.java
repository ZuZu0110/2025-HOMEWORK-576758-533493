package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class GiocatoreTest {

	private Giocatore giocatore = new Giocatore();;
	
	@Test
	public void testGetCfu() {
		assertEquals(20, giocatore.getCfu(), "i cfu iniziali per il giocatore dovrebbero essere 20");
	}
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(15);
		assertEquals(15, giocatore.getCfu(), "i cfu dopo la modifica sono 15");
	}
	
	@Test
	public void testSetBorsa() {
		Borsa nuovaBorsa = new Borsa();
		giocatore.setBorsa(nuovaBorsa);
		assertEquals(nuovaBorsa, giocatore.getBorsa(), "la borsa dovrebbe essere stata modificata correttamente");
	}
}
