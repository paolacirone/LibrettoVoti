package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {
	
	
	Libretto lib;

	private void run() {

		lib = new Libretto(); //crea libretto vuoto
		
		//1. Inserire alcuni voti
		Voto v1 = new Voto("Tecniche di Programmazione", 30, LocalDate.of(2020,06, 15));
        Voto v2 =  new Voto("Analisi II", 28, LocalDate.of(2020,06, 17));
        
        lib.add(v1);
        lib.add(v2);
        if(lib.add(new Voto("Economia", 24, LocalDate.of(2020, 02, 14)))==false)
        		System.out.println("Errore");
        
        	
        
        System.out.println(lib);
        
        //2. Stampa tutti i voti pari a 28
        //due metodi per strambare i corsi con un determinato voto
        System.out.println(this.lib.stampaVotiUguali(28));
        
        System.out.println(this.lib.estraiVoti(28));
        
        //3. Cerca un esame superato conoscendo il nome del corso
        String nomeCorso = "Analisi II"; 
        Voto votoCorso =  lib.cercaNomeCorso(nomeCorso);
        System.out.println("Il voto di" +nomeCorso+ " è : "+votoCorso.getVoto());
        
        //4. 5. Verifica voti duplicati o in conflitto 
        Voto economia2 =  new Voto("Economia", 24, LocalDate.now()); 
        Voto economia3 = new Voto ("Economia", 21, LocalDate.now());
        
        System.out.println("Economia con 24 è duplicato: "+lib.isDuplicato(economia2)+"/ conflitto:"+
        		lib.isConflitto(economia2));
        System.out.println("Economia con 21 è duplicato: "+lib.isDuplicato(economia3)+"/ conflitto:"+
        		lib.isConflitto(economia3));
        
        //6. Migliora il libretto 
        Libretto migliorato = lib.creaLibrettoMigliorato();
        System.out.println("Miglioramento del libretto: ");
        System.out.println(lib);
        System.out.println(migliorato);
        
        //7. Stampa in ordine alfabetico
        Libretto alfabetico = new Libretto(lib); //ugiamo il copy constructor
        alfabetico.ordinaPerCorso();
        System.out.println(alfabetico);
        //7. Stampa per voto
        Libretto decrescenti = new Libretto(lib);
        decrescenti.ordinaPerVoto();
        System.out.println(decrescenti);
        
        //8.Elimina i voti minori di 24 
        lib.add(new Voto("Chimica",19, LocalDate.now()));
        lib.ordinaPerCorso();
        System.out.println(lib);
        lib.cancellaVotiScarsi();
        System.out.println(lib);
	}

	// NON SI LAVORA NEL MAIN, SI CREA UN OGGETTO E SI LAVORA NEL RUN()
	public static void main(String[] args) {

		TestLibretto test = new TestLibretto();
		test.run();
	}

}
