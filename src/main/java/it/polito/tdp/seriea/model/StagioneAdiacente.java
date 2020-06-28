package it.polito.tdp.seriea.model;

public class StagioneAdiacente implements Comparable<StagioneAdiacente> {
	private Season s1;
	private Integer peso;
	/**
	 * @param s1
	 * @param peso
	 */
	public StagioneAdiacente(Season s1, Integer peso) {
		super();
		this.s1 = s1;
		this.peso = peso;
	}
	public Season getS1() {
		return s1;
	}
	public void setS1(Season s1) {
		this.s1 = s1;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(StagioneAdiacente o) {
		return this.s1.compareTo(o.getS1());
	}
	@Override
	public String toString() {
		return "StagioneAdiacente [s1=" + s1 + ", peso=" + peso + "]";
	}
	
	
	
	

}
