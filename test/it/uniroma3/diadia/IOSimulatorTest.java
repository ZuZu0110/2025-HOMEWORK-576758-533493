package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class IOSimulatorTest {
	
	public static IOSimulator creaSimulazionePartitaEGioca(String... comandi) {
		IOSimulator io = new IOSimulator(comandi);
		new DiaDia(io).gioca();
		return io;
	}

	
	public static  Attrezzo creaAttrezzoEAggiugniAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}

}
