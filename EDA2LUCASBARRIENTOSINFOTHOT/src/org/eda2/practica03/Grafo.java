package org.eda2.practica03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The Class Grafo.
 */
public class Grafo {

	/** Es dirigido. */
	int directed;
	
	/** Numero de vertices. */
	int nV;
	
	/** Numero de aristas. */
	int nA;
	
	/** The aristas. */
	List<Arista> aristas;
	
	/** The name key map. */
	static Map<String, Integer> nameKeyMap;
	
	/** The key name map. */
	static Map<Integer, String> keyNameMap;

	/**
	 * Instantiates a new grafo.
	 */
	public Grafo() {
		this.directed = 0; // No dirigido
		this.nV = 0;
		this.nA = 0;
		this.aristas = new ArrayList<Arista>();
		nameKeyMap = new HashMap<String, Integer>();
		keyNameMap = new HashMap<Integer, String>();
	}

	/**
	 * Instantiates a new grafo.
	 *
	 * @param archivo the archivo
	 */
	public Grafo(String archivo) {
		this();
		File f = new File(archivo);
		try {
			Scanner sc = new Scanner(f);
			this.directed = Integer.parseInt(sc.nextLine());
			this.nV = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < nV; i++) {
				String nombre = sc.nextLine();
				nameKeyMap.put(nombre, i);
				keyNameMap.put(i, nombre);
			}
			this.nA = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < nA; i++) {
				String[] tokens = sc.nextLine().split(" ");
				addArista(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Añade la arista dado un origen, destino y peso.
	 *
	 * @param origen the origen
	 * @param destino the destino
	 * @param peso the peso
	 */
	public void addArista(String origen, String destino, int peso) {
		Integer o = nameKeyMap.get(origen);
		Integer d = nameKeyMap.get(destino);
		if (o == null || d == null)
			return;
		addArista(o, d, peso);

	}

	/**
	 * Metodo add que añade una arista cuando dado un origen, destino y peso.
	 *
	 * @param origen the origen
	 * @param destino the destino
	 * @param peso the peso
	 */
	public void addArista(int origen, int destino, int peso) {
		addArista(new Arista(origen, destino, peso));
	}

	/**
	 * Metodo addArista que añade la arista que se le pasa por parametro.
	 *
	 * @param a the a
	 */
	public void addArista(Arista a) {
		if (!this.aristas.contains(a)) {
			this.aristas.add(a);
			if (directed == 0) {
				addArista(new Arista(a.destino, a.origen, a.peso));
			}
		}
	}

	/**
	 * Metodo Key from string.
	 * 
	 * Devuelve el valor asignado a la variable en el mapa.
	 *
	 * @param nombre the nombre
	 * @return the int
	 */
	public static int keyFromString(String nombre) {
		return nameKeyMap == null ? null : nameKeyMap.get(nombre);
	}

	/**
	 * Metodo String from key.
	 * 
	 * Devuelve el valor asignado a la variable en el mapa.
	 *
	 * @param key the key
	 * @return the string
	 */
	public static String stringFromKey(int key) {
		return keyNameMap == null ? key + "" : keyNameMap.get(key);
	}

}
