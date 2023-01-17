package it.polito.tdp.nyc.model;

public class Adiacenza {

	private int peso;
	private String z1Name; //sorgente
	private String z2Name;  //destinazione
	
	public Adiacenza(String z1, String z2, int peso) {
		super();
		this.peso= peso;
		this.z1Name=z1;
		this.z2Name=z2;
	}

	

	public int getPeso() {
		return peso;
	}

	public String getZ1Name() {
		return z1Name;
	}

	public String getZ2Name() {
		return z2Name;
	}	
	
	
	
	
}
