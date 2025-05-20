package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.ConfigurazioneProperties;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa{
	//public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Integer> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	public IOConsole io;


	public Borsa() {
		this(ConfigurazioneProperties.getPesoMaxBorsa());
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Integer>(); // speriamo bastino...
		this.numeroAttrezzi = 0;
		this.io = new IOConsole();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		//		if (this.numeroAttrezzi==10)
		//			return false;
		//		if(this.attrezzi.containsKey(attrezzo.getNome())) {
		this.attrezzi.put(attrezzo.getNome(), attrezzo.getPeso());
		this.numeroAttrezzi++;
		//		}
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {

		if(nomeAttrezzo !=null && this.attrezzi.containsKey(nomeAttrezzo)) {
			Attrezzo a = new Attrezzo(nomeAttrezzo,this.attrezzi.get(nomeAttrezzo));
			return a;
		}
		return null;
	}

	public int getPeso() {
		int peso = 0;
		for(Integer i : this.attrezzi.values()){
			peso += i;
		}
		return peso;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			return null;


		if(this.attrezzi.containsKey(nomeAttrezzo)) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,this.attrezzi.get(nomeAttrezzo));
			this.numeroAttrezzi--;
			this.attrezzi.remove(nomeAttrezzo);
			return attrezzo;
		}
		return null;
	}

	//restituisce la lista degli attrezzi nella borsa ordinati per peso e quindi,a parità di peso, per nome
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista = new ArrayList<>();
		for(String s :this.attrezzi.keySet()) {	
			lista.add(new Attrezzo(s, this.attrezzi.get(s)));
		}
		Collections.sort(lista, new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				if(o1.getPeso()-o2.getPeso()==0)
					return o1.getNome().hashCode()-o2.getNome().hashCode();
				return o1.getPeso()-o2.getPeso();
			}
		});
		return lista;
	}

	//restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(new Comparator<Attrezzo>(){
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				if(o1.getNome().hashCode()-o2.getNome().hashCode()==0)
					return o1.getPeso()-o2.getPeso();
				return o1.getNome().hashCode()-o2.getNome().hashCode();
			}
		});
		for(String s :this.attrezzi.keySet()) {	
			set.add(new Attrezzo(s, this.attrezzi.get(s)));
		}
		return set;
	}

	//		restituisce una mappa che associa un intero (rappresentante un
	//		peso) con l’insieme (comunque non vuoto) degli attrezzi di tale peso:
	//		tutti gli attrezzi dell'insieme che figura come valore hanno lo stesso
	//		peso pari all'intero che figura come chiave
	public	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> mappa = new TreeMap<>();
		
		for(String s : this.attrezzi.keySet()) {
			if(mappa.containsKey(this.attrezzi.get(s))) {
				Attrezzo a=new Attrezzo(s,this.attrezzi.get(s));
				mappa.get(this.attrezzi.get(s)).add(a);
			}
			else {
				Set<Attrezzo> set = new HashSet<>();
				Attrezzo a=new Attrezzo(s,this.attrezzi.get(s));
				set.add(a);
				mappa.put(this.attrezzi.get(s), set);
			}
		}
		return mappa;
	}
	
//	restituisce l'insieme gli attrezzi nella borsa ordinati
//	per peso e quindi, a parità di peso, per nome
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(new Comparator<Attrezzo>(){
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				if(o1.getPeso()-o2.getPeso()==0)
					return o1.getNome().hashCode()-o2.getNome().hashCode();
				return o1.getPeso()-o2.getPeso();
			}
		});
		for(String s :this.attrezzi.keySet()) {	
			set.add(new Attrezzo(s, this.attrezzi.get(s)));
		}
		return set;
	}


	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (String i: this.attrezzi.keySet())
				s.append(i+" "+this.attrezzi.get(i)+"kg, ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}


}
