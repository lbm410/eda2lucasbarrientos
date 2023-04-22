package org.eda2.practica02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConectarArchivo {
	
	public static void main(String[] args) throws IOException {
		String ruta = System.getProperty("user.dir")
				+File.separator+"src"
				+File.separator+"org" 
				+File.separator+"eda2"
				+File.separator+"practica02";
		SolucionesMochila sm = cargarArchivo(ruta, "datos.txt");
		Resultado r = sm.algoritmo2();
		System.out.println(r);
	}
	
	public static SolucionesMochila cargarArchivo (String ruta, String archivo) {
		String route = ruta+File.separator+archivo;
		Double p = null;
		ArrayList<Objeto> lista = new ArrayList<Objeto>();
		File f = new File(route);
		if(!f.exists()) {
			System.out.println("Tas quivocao");
			throw new RuntimeException("No veas no?");
		}
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
			String linea = sc.nextLine();
			if(linea.trim().isEmpty()) continue;
			if(linea.startsWith("#")) continue;
			String[] tokens = linea.split(";");
			Objeto obj;
			if (tokens.length == 1) {
				p = Double.parseDouble(tokens[0]);
			} else if(tokens.length == 3) {
				obj = new Objeto(tokens[0],
						Double.parseDouble(tokens[1]),
						Double.parseDouble(tokens[2]));
				lista.add(obj);
			} else if(tokens.length == 4) {
				obj = new Objeto(tokens[0],
						Double.parseDouble(tokens[1]),
						Double.parseDouble(tokens[2]),
						Double.parseDouble(tokens[3]));
				lista.add(obj);
				
			}
		}
			return new SolucionesMochila(p, lista);
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			}
		return null;
		}
	}
	
