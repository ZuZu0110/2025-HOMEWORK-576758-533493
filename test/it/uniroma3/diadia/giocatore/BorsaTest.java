package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsa = new Borsa();;
	private Attrezzo attrezzo1 = new Attrezzo("osso", 1);;
	private Attrezzo attrezzo2  = new Attrezzo("lanterna", 2);;
	
	// TEST PER addAttrezzo
	
	@Test
	public void addAttrezzoValido() {
		assertTrue(borsa.addAttrezzo(attrezzo1), "l'attrezzo osso dovrebbe essere stato inserito correttamente");
		assertTrue(borsa.hasAttrezzo("osso"), "la borsa dovrebbe contenere l'oggetto osso");
	}
	
	@Test
	public void testAddAttrezzoSuperaPesoMax() {
		Attrezzo grande = new Attrezzo("Armadio", 11);
		borsa.addAttrezzo(attrezzo2);
		assertFalse(borsa.addAttrezzo(grande), "non dovrebbe essere possibile aggiungere l'oggetto perchè supera il peso max della borsa");
	}
	
	@Test
	public void testAddAttrezzoOltreLimite() {
		for(int i=0;i<10;i++) {
			assertTrue(borsa.addAttrezzo(attrezzo1), "dovrebbe essere possibile aggiungere il decimo elemento");
		}
		assertFalse(borsa.addAttrezzo(attrezzo2), "non dovrebbe essere possibile aggiungere l'undicesimo elemento");
	}
	
	// TEST PER removeAttrezzo
	@Test
    public void testRemoveAttrezzoEsistente() {
        borsa.addAttrezzo(attrezzo1);
        Attrezzo removed = borsa.removeAttrezzo("osso");
        assertNotNull(removed, "L'attrezzo osso dovrebbe essere rimosso correttamente");
        assertFalse(borsa.hasAttrezzo("osso"), "La borsa non dovrebbe contenere l'attrezzo osso dopo la rimozione");
    }

    @Test
    public void testRemoveAttrezzoAggiornaPeso() {
        borsa.addAttrezzo(attrezzo1);
        borsa.addAttrezzo(attrezzo2);
        
        int pesoIniziale = borsa.getPeso();
        borsa.removeAttrezzo(attrezzo1.getNome());
        int pesoFinale = borsa.getPeso();
        assertNotEquals(pesoIniziale, pesoFinale, "Il peso della borsa dovrebbe essere aggiornato dopo la rimozione dell'attrezzo osso");
    }
}
