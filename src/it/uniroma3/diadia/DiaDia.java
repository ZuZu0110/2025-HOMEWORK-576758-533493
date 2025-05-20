package it.uniroma3.diadia;



import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final public String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	//	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine"};

	public Partita partita;
	public Labirinto labirinto;
	public Giocatore giocatore;
	private IO io;

	public DiaDia(IO io) {
		this.partita = new Partita();
		this.giocatore = new Giocatore();
		this.io = io;
	}
	
	public DiaDia(Labirinto labirinto,IO io) {
		this.partita = new Partita(labirinto);
		this.giocatore = new Giocatore();
		this.io = io;
	}
	

	public void gioca() {
		String istruzione; 
		//Scanner scannerDiLinee;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita,io);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}   

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		try(Scanner scanner = new Scanner(System.in)){
			IO io = new IOConsole(scanner);
			Labirinto labirinto = new Labirinto("labirinto.txt");
			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		}
	}
}