package org.eda2.practica02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ConectarArchivo {
	
	public static void main(String[] args) throws IOException {
		SolucionesMochila sm = cargarProblema("p02");
		Resultado r = sm.algoritmo2();
		System.out.println(r);
	}
	
	public static SolucionesMochila cargarProblema(String problema) throws IOException {
		double P = 0;
		ArrayList<Objeto> lista = new ArrayList<Objeto>();
		
		String line;
		String line2;
		BufferedReader br;
		BufferedReader br2;
		String ruta = "https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/"+problema+"_";
		
		br = getBuffer(ruta, "c.txt");
		while((line = br.readLine()) != null) {
			P = Double.parseDouble(line);
		}
		br.close();
		
		br = getBuffer(ruta, "w.txt");
		br2 = getBuffer(ruta, "p.txt");
		
		int n = 1;
		while((line = br.readLine()) != null && (line2 = br2.readLine()) != null) {
			double peso = Double.parseDouble(line);
			double beneficio = Double.parseDouble(line2);
			Objeto obj = new Objeto("Objeto "+n, beneficio, peso);
			n++;
			lista.add(obj);
		}
		return new SolucionesMochila(P, lista);
	}
	
	public static BufferedReader getBuffer(String ruta, String archivo) throws IOException {
		URL url = new URL(ruta+archivo);
		URLConnection con = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		return br;
	}

}
