package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Risultati {
	private String homeTeam;
	private String awayTeam;
	private Integer goalCasa;
	private Integer goalFuori;
	private LocalDate data;
	
	/**
	 * @param homeTeam
	 * @param awayTeam
	 * @param goalCasa
	 * @param goalFuori
	 * @param data
	
	 */
	public Risultati(String homeTeam, String awayTeam, Integer goalCasa, Integer goalFuori, LocalDate data) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.goalCasa = goalCasa;
		this.goalFuori = goalFuori;
		this.data = data;
		
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Integer getGoalCasa() {
		return goalCasa;
	}
	public void setGoalCasa(Integer goalCasa) {
		this.goalCasa = goalCasa;
	}
	public Integer getGoalFuori() {
		return goalFuori;
	}
	public void setGoalFuori(Integer goalFuori) {
		this.goalFuori = goalFuori;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	

}
