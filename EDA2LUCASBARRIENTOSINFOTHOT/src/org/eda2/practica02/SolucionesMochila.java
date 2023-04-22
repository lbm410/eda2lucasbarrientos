package org.eda2.practica02;

import java.util.ArrayList;

public class SolucionesMochila {
	
	private double P;
	private ArrayList<Objeto> objetos;
	
	public SolucionesMochila(double p, ArrayList<Objeto> objetos) {
		this.P = p;
		this.objetos = objetos;
	}

	public double getP() {
		return P;
	}

	public ArrayList<Objeto> getObjetos() {
		return objetos;
	}

	public Resultado algoritmo1() {
		objetos.sort(null);
		Resultado result = new Resultado();
		double pAcum = 0;
		for (Objeto obj : objetos) {
			Objeto x = new Objeto(obj);
			if(pAcum + obj.getPeso() <= P) {
				x.setCantidad(1);//Enterito
				pAcum += obj.getPeso();
				result.add(x);
				if(pAcum == P) break;
			}else {
				x.setCantidad((P-pAcum) / x.getPeso()); //Lo que entre
				pAcum = P;
				result.add(x);
				break;
			}
		}
 		return result;
	}

	public Resultado algoritmo2() {
		objetos.sort(null);
		Resultado result = new Resultado();
		double pAcum = 0;
		for (Objeto obj : objetos) {
			Objeto x = new Objeto(obj);
			if(pAcum + obj.getPeso() <= P) {
				x.setCantidad(1);//Enterito
				pAcum += obj.getPeso();
				result.add(x);
				if(pAcum == P) break;
			}
		}
 		return result;
	}

	public Resultado algoritmo3() {
		objetos.sort(null);
		Resultado result = new Resultado();
		double pAcum = 0;
		for (Objeto obj : objetos) {
			Objeto x = new Objeto(obj);
			if(pAcum + obj.getPeso() <= P) {
				x.setCantidad(1);//Enterito
				pAcum += obj.getPeso();
				result.add(x);
				if(pAcum == P) break;
			}else if(pAcum + obj.getPeso()/2 <= P) {
				x.setCantidad(0.5);//Mitadita
				pAcum += obj.getPeso()/2;
				result.add(x);
				if(pAcum == P) break;
			}
		}
 		return result;
	}

	public Resultado algoritmo4() {
		objetos.sort(null);
		Resultado result = new Resultado();
		double pAcum = 0;
		for (Objeto obj : objetos) {
			Objeto x = new Objeto(obj);
			if(pAcum + obj.getPeso() * obj.getCantidad() <= P) {
				x.setCantidad(obj.getCantidad());//Todos enteritos
				pAcum += obj.getPeso()* obj.getCantidad();
				result.add(x);
				if(pAcum == P) break;
			}else {
				x.setCantidad((P-pAcum) / x.getPeso()); //Lo que entre
				pAcum = P;
				result.add(x);
				break;
			}
		}
 		return result;
	}

	public Resultado algoritmo5() {
		objetos.sort(null);
		Resultado result = new Resultado();
		Objeto x = new Objeto(objetos.get(0));
		x.setCantidad(P / x.getPeso());
		result.add(x);
		return result;
	}
}
