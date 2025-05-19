package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.nio.file.FileSystems;

import it.uniroma3.diadia.FormatoFileNonValidoException;


public class CaricatoreLabirinto {
	private static final String ROOT_PATH = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/resources/";
	private BufferedReader reader;
	private LabirintoBuilder builder;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new BufferedReader(new FileReader(ROOT_PATH + nomeFile));
		this.builder = new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			String linea;
			while ((linea = this.reader.readLine()) != null) {
				linea = linea.trim();
				if (linea.isEmpty() || linea.startsWith("#")) {
					continue; // salta righe vuote o commenti
				}

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
						creaAdiacenze(dati);
						break;
					default:
						throw new FormatoFileNonValidoException("Tipo non riconosciuto: " + tipo);
				}
			}
		} catch (IOException e) {
			throw new FormatoFileNonValidoException("Errore di lettura: " + e.getMessage());
		}
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
			builder.corrente = builder.stanze.get(nomeStanza);
			builder.addAttrezzo(nomeAttrezzo, peso);
		}
	}

	private void creaAdiacenze(String dati) {
		String[] adiacenze = dati.split(",");
		for (String a : adiacenze) {
			String[] parti = a.trim().split(" ");
			if (parti.length != 3)
				continue;
			String s1 = parti[0];
			String s2 = parti[1];
			String direzione = parti[2];
			builder.adAdiacenza(s1, s2, direzione);
		}
	}

	public Labirinto getLabirinto() {
		return builder.getLabirinto();
	}

	public Stanza getStanzaIniziale() {
		return builder.iniziale;
	}

	public Stanza getStanzaVincente() {
		return builder.finale;
	}
}
