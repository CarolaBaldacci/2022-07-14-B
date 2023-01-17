package it.polito.tdp.nyc.model;

public class Zona {

	private String NTAname;
	
	
	public Zona(String NTAname) {
		super();
		this.NTAname = NTAname;
	}
	
	public String getNTAname() {
		return NTAname;
	}

	@Override
	public String toString() {
		return "Zona [NTAname=" + NTAname + "]";
	}
	
	
	
}
