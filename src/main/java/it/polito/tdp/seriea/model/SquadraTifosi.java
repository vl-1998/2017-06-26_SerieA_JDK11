package it.polito.tdp.seriea.model;

public class SquadraTifosi {
	private String squadra;
	private Integer tifosi;
	/**
	 * @param squadra
	 * @param tifosi
	 */
	public SquadraTifosi(String squadra, Integer tifosi) {
		super();
		this.squadra = squadra;
		this.tifosi = tifosi;
	}
	public String getSquadra() {
		return squadra;
	}
	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}
	public Integer getTifosi() {
		return tifosi;
	}
	public void setTifosi(Integer tifosi) {
		this.tifosi = tifosi;
	}
	@Override
	public String toString() {
		return "SquadraTifosi [squadra=" + squadra + ", tifosi=" + tifosi + "]";
	}
	
	
	

}
