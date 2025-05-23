package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import it.uniroma3.diadia.ConfigurazioneProperties;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	//static final public int NUMERO_MASSIMO_DIREZIONI = 4;
	//static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private  List<Attrezzo> attrezzi;
	public int numeroAttrezzi;   
	public Stanza[] stanzeAdiacenti;
	public int numeroStanzeAdiacenti;
	private Direzione[] direzioni;
	public AbstractPersonaggio pers;
	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni = new Direzione[ConfigurazioneProperties.getNumeroMassimoDirezioni()];
		this.stanzeAdiacenti = new Stanza[ConfigurazioneProperties.getNumeroMassimoDirezioni()];
		this.attrezzi = new ArrayList<Attrezzo>();
		this.pers=null;
		
	}

	public void setPersonaggio(AbstractPersonaggio pers) {
		this.pers=pers;
		
	}
	public AbstractPersonaggio getPersonaggio() {
		return this.pers;
	}
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		boolean aggiornato = false;
		for(int i=0; i<this.direzioni.length; i++)
			if (direzione == this.direzioni[i]) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < ConfigurazioneProperties.getNumeroMassimoDirezioni()) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i] == direzione)
				stanza = this.stanzeAdiacenti[i];
		return stanza;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null)
			return false;
		if(this.attrezzi.contains(attrezzo))
			return false;
		if (this.numeroAttrezzi < ConfigurazioneProperties.getNumeroMassimoAttrezzi()) {
			this.attrezzi.add(attrezzo);
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.direzioni)
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		if(this.getPersonaggio()!=null)
			risultato.append("\n"+this.getPersonaggio().toString());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		if(nomeAttrezzo==null)
			return trovato;
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					attrezzoCercato = attrezzo;
			}
		}	
		return attrezzoCercato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null) 
			return false;

		int i=0;
		for(Attrezzo a : this.attrezzi) {
			if(a!=null) {
				if(a.equals(attrezzo)) {
					this.attrezzi.remove(a);

					for(int j=i;j<this.attrezzi.size()-1;j++) {
						if(this.attrezzi.get(j)!=null)
							this.attrezzi.add(j, this.attrezzi.get(j+1));
					}
					this.numeroAttrezzi--;
					return true;
				}

			}
			i++;
		}
		return false;
	}

	// METODO CHE CERCA L'INDICE DELL'ATTREZZO CHE DOBBIAMO RINOMINARE CORRETTAMENTE
	public int cercaAttrezzo(String nomeAttrezzo) {
		int i=0;
		int n=0;
		if(nomeAttrezzo!=null)
			for(Attrezzo a : this.attrezzi) {
				if(a!=null)
					if(a.getNome().equals(nomeAttrezzo)) {
						n=i;
					}
				i++;
			}
		return n;
	}


	public Direzione[] getDirezioni() {
		Direzione[] direzioni = new Direzione[this.numeroStanzeAdiacenti];
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(direzioni);
		result = prime * result + Arrays.hashCode(stanzeAdiacenti);
		result = prime * result + Objects.hash(nome, numeroStanzeAdiacenti);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stanza other = (Stanza) obj;
		return Arrays.equals(direzioni, other.direzioni) && Objects.equals(nome, other.nome)
				&& numeroStanzeAdiacenti == other.numeroStanzeAdiacenti
				&& Arrays.equals(stanzeAdiacenti, other.stanzeAdiacenti);
	}



}