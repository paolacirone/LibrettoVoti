package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Memoriza e gestisce una serie di voti superati
 * 
 * @author Lenovo Personal Pc
 *
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<>(); // Posso anche non specificare l'oggetto tra i <>

	public Libretto() {
		// non fa nulla, la lista viene creata nell'inizializzazione della lista
	}

	/**
	 * Copy constructor *Shallow* (copia superficiale) Fa una copia dell'oggetto
	 * corrente ma non fa una copia degli oggetti contenuto in esso
	 * 
	 * @param lib
	 */
	public Libretto(Libretto lib) {
		// copiamo il libretto con gli oggetti voto, non duplichiamo i voti
		this.voti.addAll(lib.voti); // addAll appende tutti gli elementi della
		// lista specificata alla lista corrente
	}

	public boolean add(Voto v) {

		if (this.isConflitto(v) || this.isDuplicato(v)) {
			// non inserire il voto
			return false;
		} else {
			voti.add(v);
			return true;
		}

	}

	public String toString() {
		// return this.voti.toString(); ha un brutto formato allora lo stampo io

		String s = "";
		for (Voto v : voti) {
			s += v.toString() + "\n";
		}
		return s; // costruisco gli oggetti in modo che siano in grado di stamparsi da se
	}

	/**
	 * Dato un libretto restituisce una stringa in cui ci sono solo i voti passati
	 * dal valore parametro
	 * 
	 * @param voto
	 * @return stringa formattata per visualizzare il sottolibretto
	 */

	public String stampaVotiUguali(int voto) {
		String s = "";
		for (Voto v : voti) {
			if (v.getVoto() == voto)
				s += v.toString() + "\n";
		}
		return s;
	}

	/**
	 * Genera un nuovo libretto da quello esistente che conterrà esclusivamente i
	 * voti con votazioni pari a quella specificata
	 * 
	 * @param voto
	 * @return nuovo libretto "ridotto"
	 */
	public Libretto estraiVoti(int voto) {
		Libretto nuovo = new Libretto();
		for (Voto v : this.voti) {
			if (v.getVoto() == voto)
				nuovo.add(v);
		}
		return nuovo;
	}

	/**
	 * Data il nome del corso cerca se quell'esame esiste, in caso affermativo
	 * restituisce l'oggetto voto corrispondente Se l'esame non esite, restituisce
	 * null
	 * 
	 * {@link Voto} riporta alla clase
	 * 
	 * @param nomeCorso nome esame da cercare
	 * @return voto corrispondente o null se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {

		/*
		 * for (Voto v : this.voti) { if (nomeCorso.equals(v.getCorso())) return v; }
		 * return null;
		 */
		// ho creato un nuovo oggetto di tipo voto i cui attributi conosco solo il nome
		// passo l'oggetto voto al metodo index of della lista che andrà a cercare
		// dentro la lista
		// il voto che soddifa nel libretto la condizione di quals implementata in voto
		// che verifica solo
		// il nomecorso
		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null));
		if (pos != -1) {
			return this.voti.get(pos);
		} else {
			return null;
		}
	}

	/**
	 * ritorna true se il corso spleciticato dal voto esiste nel libretto con la
	 * stessa valutazione. Se esiste con una valutazione diversa ritorna false.
	 */
	public boolean isDuplicato(Voto v) {

		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null)
			return false;

		return (esiste.getVoto() == v.getVoto()); // ritorna il boolean soluzione dell'uguaglianza
	}

	/**
	 * Determina se esiste un voto con steso nomeCorso ma votazione diversa
	 * 
	 */
	public boolean isConflitto(Voto v) {

		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null)
			return false;

		return (esiste.getVoto() != v.getVoto());
	}

	/**
	 * Restituisce un nuovo libretto migliorando i voti del libretto attuale
	 * 
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto();

		for (Voto v : this.voti) {
			Voto v2 = new Voto(v); // copy constructor in voto
			// oppure Voto v2= v.clone();
			if (v2.getVoto() >= 24) {
				v2.setVoto(v2.getVoto() + 2);
				if (v2.getVoto() >= 30)
					v2.setVoto(30);
			} else if (v2.getVoto() >= 18) {
				v2.setVoto(v2.getVoto() + 1);
			}
			nuovo.add(v2);
		}

		return nuovo;

	}

	/**
	 * riordina i voti presenti nel libretto corrente alfabeticamente per corso
	 */
	public void ordinaPerCorso() {
		Collections.sort(voti);
	}

	public void ordinaPerVoto() {
		Collections.sort(this.voti, new ConfrontaVotiPervValutazioni());
	}

	/**
	 * Elimina dal libretto tutti i voti minori di 24
	 */
	public void cancellaVotiScarsi() {
		List<Voto> daRimuovere = new ArrayList<>();
		// non posso rimuovere sugli elementi che sto iterando this.voti.remove(v);
		for (Voto v : this.voti) {
			if (v.getVoto() < 24)
				daRimuovere.add(v);
		}

		// devo iterare su una lista diversa rispetto a quella in cui devo rimuovere
		/*for (Voto v : daRimuovere) {
			this.voti.remove(v);

		}*/ 
		//oppure 
		this.voti.removeAll(daRimuovere);
	}

}
