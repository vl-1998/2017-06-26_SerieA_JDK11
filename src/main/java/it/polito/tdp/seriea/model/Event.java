package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Event implements Comparable<Event> {
	public enum EventType{
		PARTITA,
		RIBALTAMENTO,
		SPOSTAMENTO_TIFOSI
	}
	
	private LocalDate data;
	private Risultati risultato;
	private EventType type;
	private String squadra1;
	private String squadra2;
	
	

	
	/**
	 * @param data
	 * @param risultato
	 * @param type
	 * @param squadra1
	 * @param squadra2
	
	 */
	public Event(LocalDate data, Risultati risultato, EventType type, String squadra1, String squadra2) {
		super();
		this.data = data;
		this.risultato = risultato;
		this.type = type;
		this.squadra1 = squadra1;
		this.squadra2 = squadra2;
		
	}
	
	

	public LocalDate getData() {
		return data;
	}



	public void setData(LocalDate data) {
		this.data = data;
	}



	public Risultati getRisultato() {
		return risultato;
	}



	public void setRisultato(Risultati risultato) {
		this.risultato = risultato;
	}



	public EventType getType() {
		return type;
	}



	public void setType(EventType type) {
		this.type = type;
	}



	public String getSquadra1() {
		return squadra1;
	}



	public void setSquadra1(String squadra1) {
		this.squadra1 = squadra1;
	}



	public String getSquadra2() {
		return squadra2;
	}



	public void setSquadra2(String squadra2) {
		this.squadra2 = squadra2;
	}

	@Override
	public int compareTo(Event o) {
		return this.data.compareTo(o.getData());
	}

	@Override
	public String toString() {
		return "Event [data=" + data + ", risultato=" + risultato + ", type=" + type ;
	}

	
	
	
}
