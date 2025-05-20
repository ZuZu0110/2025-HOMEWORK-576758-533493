package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.ConfigurazioneProperties;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{

	//final static private int SOGLIA_MAGICA_DEFAULT = 2;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, ConfigurazioneProperties.getSogliaMagicaDefault());
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	public int getSoglia() {
		return this.sogliaMagica;
	}
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;

		if (attrezzo!=null && this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);

		return super.addAttrezzo(attrezzo);
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;

		int pesoX2 = attrezzo.getPeso() * 2;
		if(attrezzo!=null) {
			nomeInvertito = new StringBuilder(attrezzo.getNome());
			nomeInvertito = nomeInvertito.reverse();
			attrezzo = new Attrezzo(nomeInvertito.toString(),
					pesoX2);
		}
		return attrezzo;
	}


	public String getDescrizione() {
		StringBuilder ris = new StringBuilder();		
		ris.append("Sei entrato in una STANZA MAGICA, questa ha poteri speciali!\n"
				+ "Se lasci più di 2 attrezzi, modifica il nome dell'attrezzo invertendo il nome e \n"
				+ "raddoppia il peso\n");
		ris.append(this.toString());

		return ris.toString();
	}
}
