package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto
 * @author Lenovo Personal Pc
 *
 */
public class Voto implements Comparable<Voto> {

	private String corso; //es: Tdp
	private int voto; 
	private LocalDate data; 
	
	/**
	 * Costituisce un nuovo voto
	 * @param corso
	 * @param voto
	 * @param data
	 */
	

	public Voto(String corso, int voto, LocalDate data) {
		
		this.corso = corso;
		this.voto = voto;
		this.data = data;
	}

	/**
	 * Copy constructor di Voto:
	 * Crea un nuovo oggetto di tipo voto 
	 * copiando il contenuto del parametro {@code v}
	 * @return
	 */
	
	public Voto(Voto v) {
		//quando un oggetto è immutabile (tipo stringa) il problema della 
		//copia non si pone 
		this.corso=v.corso;  
		this.data=v.data; 
		this.voto= v.voto; 
	}
	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return corso + ": " + voto + " (" + data + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
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
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}
	
	/**
	 * crea una copia (clone) dell'oggetto presente (this) come nuovo oggetto
	 */
	public Voto clone() {
		Voto v = new Voto(this.corso, this.voto, this.data);
		//uguale a new Voto(this)
		return v;
	}

	@Override
	public int compareTo(Voto o) {
		/*
		 * <0 se this < other
		 * =0 se this=other 
		 * >0 se this>othe
		 */
		
		return this.corso.compareTo(o.getCorso());
	}
	
	
	
	
}
