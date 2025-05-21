package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;
import it.uniroma3.diadia.giocatore.Cane;
import it.uniroma3.diadia.giocatore.Mago;
import it.uniroma3.diadia.giocatore.Strega;


public class CaricatoreLabirinto {
	private static final String ROOT_PATH = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/resources/";
	private BufferedReader reader;
	private LabirintoBuilder builder;
	private String lineaSalvata =null;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new BufferedReader(new FileReader(ROOT_PATH + nomeFile));
		this.builder = new Labirinto.LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			String linea;
			
			while (true) {
			    if (this.lineaSalvata != null) {
			        linea = this.lineaSalvata;
			        this.lineaSalvata = null;
			    } else {
			        linea = this.reader.readLine();
			        if (linea == null)
			            break;
			    }
//			while ((linea = this.reader.readLine()) != null) {
//				linea = linea.trim();
//				if (linea.isEmpty() || linea.startsWith("#")) {
//					continue; // salta righe vuote o commenti
//				}

				String[] parti = linea.split(":");
				if (parti.length < 2) {
					throw new FormatoFileNonValidoException("Linea non valida: " + linea);
				}

				String tipo = parti[0].trim();
				String dati = parti[1].trim();

				switch (tipo) {
					case "Stanze":
						creaStanze(dati);
						break;
					case "StanzaBloccata":
						creaStanzaBloccata(dati);
						break;
					case "StanzaBuia":
						creaStanzaBuia(dati);
						break;
					case "StanzaMagica":
						creaStanzaMagica(dati);
						break;
					case "Inizio":
						builder.addStanzaIniziale(dati);
						break;
					case "Vincente":
						builder.addStanzaVincente(dati);
						break;
					case "Attrezzi":
						creaAttrezzi(dati);
						break;
					case "Adiacenze":
					    while ((linea = this.reader.readLine()) != null && !linea.contains(":")) {
					        creaAdiacenzaDaSingolaRiga(linea.trim());
					    }
					    if (linea != null && linea.contains(":")) {
					    	 this.lineaSalvata = linea;
					    }
					    break;
					case "Personaggi":
						creaPersonaggi(dati);
						break;
					default:
						throw new FormatoFileNonValidoException("Tipo non riconosciuto: " + tipo);
				}
			}
		} catch (IOException e) {
			throw new FormatoFileNonValidoException("Errore di lettura: " + e.getMessage());
		}
	}
	
	private void creaAdiacenzaDaSingolaRiga(String linea) throws FormatoFileNonValidoException {
	    // es. "Atrio AulaN11 est"
	    String[] tokens = linea.split(" ");
	    if (tokens.length != 3)
	        throw new FormatoFileNonValidoException("Adiacenza non valida: " + linea);

	    String stanzaDa = tokens[0];
	    String stanzaA = tokens[1];
	    Direzione direzione;
	    try {
	    	 direzione = Direzione.valueOf(tokens[2].toUpperCase());
	    }catch (IllegalArgumentException e) {
	    	throw new FormatoFileNonValidoException("Direzione non valida: " + tokens[2]);
	    }

		Stanza partenza = builder.stanze.get(stanzaDa);
	    Stanza destinazione = builder.stanze.get(stanzaA);

	    if (partenza == null || destinazione == null)
	        throw new FormatoFileNonValidoException("Stanza non trovata in adiacenze: " + stanzaDa + " o " + stanzaA);

	    partenza.impostaStanzaAdiacente(direzione, destinazione);
	}

	
	private void creaStanzaBuia(String dati) {
	    String[] parti = dati.split(" ");
	    String nome = parti[0];
	    String attrezzoVisibilita = parti[1];
	    builder.stanze.put(nome, new StanzaBuia(nome, attrezzoVisibilita));
	}

	private void creaStanzaBloccata(String dati) {
	    String[] parti = dati.split(" ");
	    String nome = parti[0];
	    Direzione direzioneBloccata = Direzione.valueOf(parti[1].toUpperCase());
	    String attrezzoSbloccante = parti[2];
	    builder.stanze.put(nome, new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante));
	}

	private void creaStanzaMagica(String dati) {
	    String[] parti = dati.split(" ");
	    String nome = parti[0];
	    int sogliaMagica = (parti.length > 1) ? Integer.parseInt(parti[1]) : 1;
	    builder.stanze.put(nome, new StanzaMagica(nome, sogliaMagica));
	}
	
	private void creaStanze(String dati) {
		String[] stanze = dati.split(",");
		for (String s : stanze) {
			builder.addStanza(s.trim());
		}
	}

	private void creaAttrezzi(String dati) {
		String[] attrezzi = dati.split(",");
		for (String a : attrezzi) {
			String[] parti = a.trim().split(" ");
			if (parti.length != 3)
				continue;
			String nomeAttrezzo = parti[0];
			int peso = Integer.parseInt(parti[1]);
			String nomeStanza = parti[2];
			// imposta come stanza corrente quella specificata, poi aggiunge l'attrezzo
			builder.stanzaCorrente = builder.stanze.get(nomeStanza);
			builder.addAttrezzo(nomeAttrezzo, peso);
		}
	}
	
//	private void creaAdiacenze(String dati) throws FormatoFileNonValidoException {
//	    String[] adiacenze = dati.split(",");
//	    for (String a : adiacenze) {
//	        a = a.trim();
//	        if (a.isEmpty()) continue; // ignora voci vuote
//
//	        String[] parti = a.split(" ");
//	        if (parti.length != 3) {
//	            throw new FormatoFileNonValidoException("Linea non valida (adiacenza): " + a);
//	        }
//
//	        String s1 = parti[0];
//	        String s2 = parti[1];
//	        String direzione = parti[2];
//
//	        if (!builder.stanze.containsKey(s1) || !builder.stanze.containsKey(s2)) {
//	            throw new FormatoFileNonValidoException("Stanza non trovata in adiacenze: " + s1 + " o " + s2);
//	        }
//
//	        builder.adAdiacenza(s1, s2, direzione);
//	    }
//	}



//	private void creaAdiacenze(String dati) {
//		String[] adiacenze = dati.split(",");
//		for (String a : adiacenze) {
//			String[] parti = a.trim().split(" ");
//			if (parti.length != 3)
//				continue;
//			String s1 = parti[0];
//			String s2 = parti[1];
//			String direzione = parti[2];
//			builder.adAdiacenza(s1, s2, direzione);
//		}
//	}
	
	private void creaPersonaggi(String dati) {
		String[] personaggi = dati.split(",");
		for (String p : personaggi) {
			String[] parti = p.trim().split(" ", 5); // Limitiamo a 5 split
			if (parti.length < 5) {
				System.err.println("Formato personaggio non valido: " + p);
				continue;
			}

			String tipo = parti[0];
			String nome = parti[1];
			String descrizione = parti[2].replace("\"", ""); // rimuove virgolette
			String oggetto = parti[3];
			String nomeStanza = parti[4];

			Stanza stanzaDestinazione = builder.stanze.get(nomeStanza);
			if (stanzaDestinazione == null) {
				System.err.println("Stanza " + nomeStanza + " non trovata per personaggio " + nome);
				continue;
			}

			AbstractPersonaggio personaggio = null;
			switch (tipo.toLowerCase()) {
				case "mago":
					personaggio = new Mago(nome, descrizione, new Attrezzo(oggetto, 1));
					break;
				case "cane":
					personaggio = new Cane(nome, descrizione, new Attrezzo(oggetto, 1));
					break;
				case "strega":
					personaggio = new Strega(nome, descrizione);
					break;
				default:
					System.err.println("Tipo personaggio non riconosciuto: " + tipo);
					continue;
			}

			stanzaDestinazione.setPersonaggio(personaggio);
		}
	}



	public Labirinto getLabirinto() {
		return builder.getLabirinto();
	}

	public Stanza getStanzaIniziale() {
		return builder.getLabirinto().getIniziale();
	}

	public Stanza getStanzaVincente() {
		return builder.getLabirinto().getStanzaVincente();
	}
}
