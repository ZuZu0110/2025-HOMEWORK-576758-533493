
package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{

	//private IO io = new IOConsole();
	@Override
	public void esegui(Partita partita,IO io) {
		this.setIo(io);
		io.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
		
	}

	

	@Override
	public String getNome() {
		return "fine";
	}


	@Override
	public void setIo(IO io) {
		// TODO Auto-generated method stub
		//this.io=io;
	}

}
