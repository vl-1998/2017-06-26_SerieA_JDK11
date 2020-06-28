package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	private SerieADAO dao ;
	private Graph<Season, DefaultWeightedEdge> grafo;
	private Map <Integer, Season> idMap;
	
	public Model() {
		this.dao = new SerieADAO();
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap = new HashMap<>();
		this.dao.listSeasons(idMap);
		
		for (Season s: this.idMap.values()) {
			this.grafo.addVertex(s);
		}
		
		for (Adiacenza a : this.dao.getEdge(idMap)) {
			Graphs.addEdgeWithVertices(this.grafo, a.getS1(), a.getS2(), a.getPeso());
		}
	}
	
	public List<Season> getVertex(){
		List<Season> vertici = new ArrayList<>(this.grafo.vertexSet()); 
		Collections.sort(vertici);
		return vertici;
	}
	
	public List<DefaultWeightedEdge> getEdge(){
		List<DefaultWeightedEdge> archi = new ArrayList<>(this.grafo.edgeSet()); 
		return archi;
	}
	
	public List<StagioneAdiacente> squadreComuni(Season s){ 
		List<Season> vicini = Graphs.neighborListOf(this.grafo, s); 
		List<StagioneAdiacente> result = new ArrayList <>();
		
		for (Season se: vicini) {
			StagioneAdiacente pTemp = new StagioneAdiacente(se, (int)this.grafo.getEdgeWeight(this.grafo.getEdge(s, se)));
			result.add(pTemp); 
		}
		
		Collections.sort(result);
		return result; 
	}


}
