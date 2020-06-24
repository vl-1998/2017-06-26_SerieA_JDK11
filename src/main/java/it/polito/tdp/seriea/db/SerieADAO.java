package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.seriea.model.Coppia;
import it.polito.tdp.seriea.model.Risultati;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getVertex(){
		String sql = "select distinct FTHG " + 
				"from matches";
		List<Integer> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getInt("FTHG"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Coppia> getArchi(){
		String sql = "select FTHG, FTAG, count(*) as peso " + 
				"from matches " + 
				"where FTHG<>FTAG " + 
				"group by FTHG, FTAG";
		List<Coppia> result = new ArrayList <>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Coppia c = new Coppia (res.getInt("FTHG"), res.getInt("FTAG"), res.getInt("peso"));
				result.add(c);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Coppia> vincenti(Integer goal){
		String sql = "select FTHG, FTAG, count(*) as numero " + 
				"from matches " + 
				"where FTHG = ? " + 
				"group by FTHG, FTAG "+
				"order by count(*) DESC";
		
		List<Coppia> result = new ArrayList <>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,goal);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Coppia c = new Coppia (res.getInt("FTHG"), res.getInt("FTAG"), res.getInt("numero"));
				result.add(c);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Risultati> risultati (Team squadra1, Team squadra2){
		String sql = "select HomeTeam, AwayTeam, FTHG, FTAG, Date " + 
				"from matches " + 
				"where HomeTeam =? and AwayTeam =? " + 
				"or HomeTeam =? and AwayTeam =? " + 
				"order by Date asc";
		List<Risultati> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, squadra1.getTeam());
			st.setString(2, squadra2.getTeam());
			st.setString(3, squadra2.getTeam());
			st.setString(4, squadra1.getTeam());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Risultati rTemp = new Risultati (res.getString("HomeTeam"), res.getString("AwayTeam"), 
						res.getInt("FTHG"), res.getInt("FTAG"), res.getDate("Date").toLocalDate());
				result.add(rTemp);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}

