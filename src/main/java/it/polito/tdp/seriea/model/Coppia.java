package it.polito.tdp.seriea.model;

public class Coppia implements Comparable <Coppia> {
	private Integer gol1;
	private Integer gol2;
	private Integer peso;
	/**
	 * @param gol1
	 * @param gol2
	 * @param peso
	 */
	public Coppia(Integer gol1, Integer gol2, Integer peso) {
		super();
		this.gol1 = gol1;
		this.gol2 = gol2;
		this.peso = peso;
	}
	public Integer getGol1() {
		return gol1;
	}
	public void setGol1(Integer gol1) {
		this.gol1 = gol1;
	}
	public Integer getGol2() {
		return gol2;
	}
	public void setGol2(Integer gol2) {
		this.gol2 = gol2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Coppia o) {
		return -this.peso-o.getPeso();
	}
	@Override
	public String toString() {
		return "Coppia [gol1=" + gol1 + ", gol2=" + gol2 + ", peso=" + peso + "]";
	}
	
	
	
	

}
