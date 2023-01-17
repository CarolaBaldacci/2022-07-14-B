package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;


public class Model {

	private NYCDao dao;
	private Graph<Zona, DefaultWeightedEdge> grafo;
	private List<Zona> zoneList;
	private List<Adiacenza> adiacenzeList;
	
	public Model() {
		super();
		this.dao = new NYCDao();
	}

	public List<String> getBorghi() {
		return this.dao.getAllBorghi();
	}
	
	public String creaGrafo(String borgo) {
		this.grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		//aggiungo vertici
		zoneList=  this.dao.getZonaBorgo(borgo);
		Graphs.addAllVertices(this.grafo, zoneList);
		//aggiungo archi
		adiacenzeList=this.dao.getArchi(borgo);
		for (Adiacenza a: adiacenzeList) {
			Graphs.addEdgeWithVertices(this.grafo, a.getZ1(), a.getZ2(), a.getPeso());
		}
		
		return "Grafo creato #Vertici: "+this.grafo.vertexSet().size()+"#Archi: "+this.grafo.edgeSet().size();
	}
	
	public Set <Zona> getVertici(){
		return this.grafo.vertexSet();
	}
	
	public int pesoMedio() {
		int media =0;
		int pesoTot=0;
		for(Adiacenza a: adiacenzeList) {
			pesoTot+= a.getPeso();
		}
		media =pesoTot/adiacenzeList.size();
		return media;
	}
	
	public List<Adiacenza> getArchiPesoMinore(){
		List<Adiacenza> result= new ArrayList<>() ;
		int media = pesoMedio();
		for(Adiacenza a: adiacenzeList) {
			if(a.getPeso()<media) {
				result.add(a);
			}
		}
    	Collections.sort(result, new Comparator<Adiacenza>() {
			@Override
			public int compare(Adiacenza a1, Adiacenza a2) {
				// TODO Auto-generated method stub
				return a1.getPeso()-a2.getPeso();
			}});
    	
		return result;
	}
	
}
