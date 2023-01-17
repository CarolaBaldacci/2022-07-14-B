package it.polito.tdp.nyc.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		String s=model.creaGrafo("BX");
		System.out.println(s);
		for(String z: model.getVertici()) {
			System.out.println(z.toString());
		}
		System.out.println(model.pesoMedio());
		

	}

}
