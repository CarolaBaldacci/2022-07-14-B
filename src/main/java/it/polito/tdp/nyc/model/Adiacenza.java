package it.polito.tdp.nyc.model;

public class Adiacenza {

	private Zona z1; //sorgente
	private Zona z2; //destinazione
	private int peso;
	private String z1Name;
	private String z2Name;
	
	public Adiacenza(Zona z1, Zona z2, int peso) {
		super();
		this.z1 = z1;
		this.z2 = z2;
		this.peso= peso;
		this.z1Name=z1.getNTAname();
		this.z2Name=z2.getNTAname();
	}

	public Zona getZ1() {
		return z1;
	}

	public Zona getZ2() {
		return z2;
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
