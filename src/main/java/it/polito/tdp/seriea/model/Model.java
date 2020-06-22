package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;



import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	private Graph <Integer, DefaultWeightedEdge> grafo;
	private SerieADAO dao ;
	
	public Model () {
		this.dao= new SerieADAO();
	}
	
	public void creaGrafo () {
		this.grafo = new SimpleDirectedWeightedGraph <>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertex());
		
		for (Coppia c : dao.getArchi()) {
			if (this.grafo.vertexSet().contains(c.getGol1()) && this.grafo.vertexSet().contains(c.getGol2())) {
				Graphs.addEdgeWithVertices(this.grafo, c.getGol1(), c.getGol2(), c.getPeso());
			}
		}
	}
	
	public int getVertexNumber() {
		return this.grafo.vertexSet().size();
	}
	public int getEdgeNumber() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Integer> vertexSet(){
		List<Integer> res = new ArrayList <> ();
		for (Integer i : this.grafo.vertexSet()) {
			res.add(i);
		}
		Collections.sort(res);
		return res;
	}
	
	public List<Coppia> risultati (Integer goal){
		return dao.vincenti(goal);
	}

}
