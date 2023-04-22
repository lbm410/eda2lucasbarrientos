package org.eda2.practica02;

public class Objeto implements Comparable<Objeto>{
	
	private String nombre;
	private double beneficio;
	private double peso;
	private double cantidad;
	
	public Objeto(String nombre, double beneficio, double peso, double cantidad) {
		this.nombre = nombre;
		this.beneficio = beneficio;
		this.peso = peso;
		this.cantidad = cantidad;
	}
	
	public Objeto(String nombre, double beneficio, double peso) {
		this(nombre, beneficio, peso, 1);
	}
	
	public Objeto(Objeto otro) {
		this(otro.nombre, otro.beneficio, otro.peso, otro.cantidad);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return nombre + ", B=" + beneficio + ", P=" + peso + ", C=" + cantidad + ", peso="+(peso*cantidad);
	}

	@Override
	public int compareTo(Objeto o) {
		return -Double.compare(this.beneficio/this.peso, o.beneficio/o.peso);
	}
	
	
	
	
	
	
	

}
