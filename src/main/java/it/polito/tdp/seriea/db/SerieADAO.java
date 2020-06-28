package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Adiacenza;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public void listSeasons(Map<Integer,Season> idMap) {
		String sql = "SELECT distinct season, description FROM seasons";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if (!idMap.containsKey(res.getInt("season"))) {
					Season s = new Season(res.getInt("season"), res.getString("description"));
					idMap.put(res.getInt("season"), s);
				}
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public List<Adiacenza> getEdge(Map<Integer,Season> idMap){
		String sql = "select m1.season, m2.season, count(distinct m1.HomeTeam)as peso \n" + 
				"from matches m1, matches m2\n" + 
				"where m1.season>m2.season\n" + 
				"and m1.HomeTeam=m2.HomeTeam \n" + 
				"group by m1.season, m2.season";
		List<Adiacenza> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if (idMap.containsKey(res.getInt("m1.season")) && idMap.containsKey(res.getInt("m2.season"))) {
					Adiacenza a = new Adiacenza (idMap.get(res.getInt("m1.season")), idMap.get(res.getInt("m2.season")), res.getInt("peso"));
					result.add(a);
				}
				
			}
			
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}

