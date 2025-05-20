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
}
