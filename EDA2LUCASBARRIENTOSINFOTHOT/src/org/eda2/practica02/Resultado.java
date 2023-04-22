package org.eda2.practica02;

import java.util.ArrayList;

public class Resultado {
	
	private ArrayList<Objeto> resultado;
	private double beneficio;
	private double peso;
	
	public Resultado() {
		this.resultado = new ArrayList<Objeto>();
		this.beneficio = 0;
		this.peso = 0;
	}
	
	public void add(Objeto o) {
		this.resultado.add(o);
		this.beneficio += o.getBeneficio() * o.getCantidad();
		this.peso += o.getPeso() * o.getCantidad();
	}
	
	public String toString() {
		String s = "PESO: "+this.peso+"\nBENEFICIO: "+this.beneficio+"\nOBJETOS:\n";
		for (Objeto o : resultado) {
			s += " - "+o+"\n";
		}
		return s;
	}

}
