package org.eda2.practica03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The Class GeneradorGrafo.
 */
public class GeneradorGrafo {

	/** El numero de nodos nodos. */
	private int numNodos;

	/** Lista con los nodos. */
	private List<String> nodos;

	/** Lista con las aristas. */
	private List<String> aristas;

	/** La ruta relativa. */
	private static String rutaRelativa = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org" + File.separator
			+ "eda2" + File.separator + "practica03" + File.separator + "datos";

	/**
	 * Instancia un nuevo generador de grafo.
	 *
	 * @param numNodos     the num nodos
	 * @param rutaRelativa the ruta relativa
	 */
	public GeneradorGrafo(int numNodos) {
		this.numNodos = numNodos;
		this.nodos = new ArrayList<>();
		this.aristas = new ArrayList<>();
	}

	/**
	 * Genera un grafo.
	 * 
	 * Primero genera unos nodos y luego unas aristas.
	 */
	public void generarGrafo() {
		
		// Generar nodos
		for (int i = 0; i < numNodos; i++) {
			nodos.add(String.valueOf(i));
		}

		// Generar aristas
		Random random = new Random();
		for (int i = 0; i < numNodos; i++) {
			List<String> nodosRestantes = new ArrayList<>(nodos);
			nodosRestantes.remove(nodos.get(i));
			Collections.shuffle(nodosRestantes);
			int numAristas = Math.min(random.nextInt(numNodos - i) + 1, nodosRestantes.size()); // mínimo 1 arista por
																								// nodo
			for (int j = 0; j < numAristas; j++) {
				String nodoDestino = nodosRestantes.get(j);
				int peso = random.nextInt(1000) + 1; // peso aleatorio entre 1 y 1000
				aristas.add(nodos.get(i) + " " + nodoDestino + " " + peso);
			}
		}
	}

	/**
	 * Guardar grafo.
	 *
	 * @param nombreArchivo the nombre archivo
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void guardarGrafo(String nombreArchivo) throws IOException {
		FileWriter writer = new FileWriter(nombreArchivo);
		writer.write("0\n");
		writer.write(numNodos + "\n");
		for (String nodo : nodos) {
			writer.write(nodo + "\n");
		}
		writer.write(aristas.size() + "\n");
		for (String arista : aristas) {
			writer.write(arista + "\n");
		}

		// Imprimir la ruta absoluta del archivo generado
		String rutaRelativa = new File(nombreArchivo).getAbsolutePath();
		System.out.println("Archivo generado en " + rutaRelativa);

		writer.close();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Introduce el nombre del archivo a generar: ");
			String fileName = sc.nextLine();
			System.out.println("Introduce el número de nodos a generar: ");
			int numNodes = sc.nextInt();
			GeneradorGrafo generador = new GeneradorGrafo(numNodes);
			generador.generarGrafo();
			String nombreArchivo = rutaRelativa + File.separator + fileName;
			generador.guardarGrafo(nombreArchivo + ".txt");
		}
	}
}