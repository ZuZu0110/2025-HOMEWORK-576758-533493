package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	public Attrezzo attrezzo;

	public AbstractPersonaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}
	
	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}
	
	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder ris = new StringBuilder("Ciao, io sono ");
		ris.append(this.getNome()+" ");
		if(!haSalutato()) {
			ris.append(this.presentazione+" ");
		}else
			ris.append("Ci siamo già presentati ");
		this.haSalutato=true;
		return ris.toString();
	}

	public abstract String agisci(Partita partita);
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);

	@Override
	public String toString() {
		return this.getNome();
	}
}
