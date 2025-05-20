package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurazioneProperties {
	private static final String FILE_PROPERTIES = "diadia.properties";
	private static Properties properties = new Properties();
	
	static {
		try(InputStream input = ConfigurazioneProperties.class.getClassLoader().getResourceAsStream(FILE_PROPERTIES)){
			if(input == null) {
				throw new RuntimeException("Impossibile trovare il file " + FILE_PROPERTIES);
			}
			properties.load(input);
		} catch (IOException e){
			throw new RuntimeException("Errore durante il caricamento del file " + FILE_PROPERTIES, e);
		}
	}
	
	public static int getCfuIniziali() {
		return Integer.parseInt(properties.getProperty("cfu.iniziali"));
	}
	
	public static int getPesoMaxBorsa() {
		return Integer.parseInt(properties.getProperty("peso.max.borsa"));
	}
	
	public static int getNumeroMassimoDirezioni() {
		return Integer.parseInt(properties.getProperty("numero.massimo.direzioni"));
	}
	
	public static int getNumeroMassimoAttrezzi() {
		return Integer.parseInt(properties.getProperty("numero.massimo.attrezzi"));
	}
	
	public static int getSogliaMagicaDefault() {
		return Integer.parseInt(properties.getProperty("soglia.magica.default"));
	}
	
}
