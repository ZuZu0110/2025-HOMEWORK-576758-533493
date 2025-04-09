package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FabbricaDiComandiFisarmonicaTest {

	@Test
	public void comandoConParametroTest() {
		Comando vai = new ComandoVai();
		FabbricaDiComandi fab = new FabbricaDiComandiFisarmonica(); 
		Comando test= fab.costruisciComando("vai");
		assertEquals(vai.getNome(),test.getNome());
		assertEquals(vai.getParametro(),test.getParametro());
	}
	
	@Test
	public void comandoSenzaParametroTest() {
		Comando vai = new ComandoAiuto();
		FabbricaDiComandi fab = new FabbricaDiComandiFisarmonica(); 
		Comando test= fab.costruisciComando("aiuto");
		assertEquals(vai.getNome(),test.getNome());
		
	}
	
	
}
