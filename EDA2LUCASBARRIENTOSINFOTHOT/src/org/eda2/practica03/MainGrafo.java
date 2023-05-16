package org.eda2.practica03;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedHashSet;

/**
 * 
 * Clase MainGrafo.
 *
 */
public class MainGrafo {

	/** El inicio. */
	private static long start;

	/** El final. */
	private static long end;

	/**
	 * Estructura que almacena todo el recorrido resultante.
	 */
	private static LinkedHashSet<String> caminoResultado = new LinkedHashSet<>();

	/**
	 * Devuelve el camino resultante.
	 * 
	 * @return caminoResultado
	 */
	public static LinkedHashSet<String> getCaminoResultado() {
		return caminoResultado;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String origen;
		String destino;
		String intermedio;
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el nodo origen:  ");
		origen = sc.nextLine();
		System.out.println("Introduce el nodo destino: ");
		destino = sc.nextLine();
		System.out.println("En caso de que lo hubiera, introduce el nodo intermedio: ");
		intermedio = sc.nextLine();
		String nombreArchivo = "";
		String nombrePrueba = "prueba1500.txt"; //Introducir aquí el nombre de el archivo de prueba para ejecutar los algoritmos.
		System.out.println(
				"Que archivo quieres seleccionar para hacer la prueba? \n1. graphEDAland.txt \n2. graphEDAlandLarge.txt \n3. " + nombrePrueba);
		int elegirDatos = sc.nextInt();
		switch (elegirDatos) {
		case 1:
			nombreArchivo = "graphEDAland.txt";
			break;
		case 2:
			nombreArchivo = "graphEDAlandLarge.txt";
			break;
		case 3:
			nombreArchivo = nombrePrueba;
			break;
		default:
			break;
		}

		String archivo = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica03" + File.separator + "datos" + File.separator
				+ nombreArchivo + File.separator;
		Grafo g = new Grafo(archivo);
		System.out.println("Aristas: ");
		for (Arista a : g.aristas) {
			System.out.println(a);
		}
		System.out.println("=================");
		try {
			System.out.println("Algoritmo de BellmanFord: ");
			start = System.nanoTime();
			BF(g, origen, destino);
			end = System.nanoTime();
			System.out.println("El tiempo que tardó en ejecutarse es de: " + (end - start) + " nanosegundos.");
		} catch (NullPointerException e) {
			System.out.println("El grafo no existe.");
		}
		System.out.println("=================");
		try {
			System.out.println("Algoritmo de Dijkstra: ");
			start = System.nanoTime();
			DJK(g, origen, destino);
			end = System.nanoTime();
			System.out.println("El tiempo que tardó en ejecutarse es de: " + (end - start) + " nanosegundos.");
		} catch (NullPointerException e) {
			System.out.println("El grafo no existe.");
		}
		System.out.println("=================");
		try {
			System.out.println("Algoritmo BellmanFord con nodo intermedio: ");
			start = System.nanoTime();
			BFIntermedio(g, origen, destino, intermedio);
			end = System.nanoTime();
			System.out.println("El tiempo que tardó en ejecutarse es de: " + (end - start) + " nanosegundos.");
		} catch (NullPointerException e) {
			System.out.println("El grafo no existe.");
		}
		System.out.println("=================");
		try {
			System.out.println("Algoritmo Dijkstra con nodo intermedio: ");
			start = System.nanoTime();
			DJKIntermedio(g, origen, destino, intermedio);
			end = System.nanoTime();
			System.out.println("El tiempo que tardó en ejecutarse es de: " + (end - start) + " nanosegundos.");
		} catch (NullPointerException e) {
			System.out.println("El grafo no existe.");
		}
		System.out.println("=================");
		try {
			System.out.println("Algoritmo FloydWarshall: ");
			start = System.nanoTime();
			FloydWarshall(g, origen, destino);
			end = System.nanoTime();
			System.out.println("El tiempo que tardó en ejecutarse es de: " + (end - start) + " nanosegundos.");
		} catch (NullPointerException e) {
			System.out.println("El grafo no existe.");
		}
		sc.close();
	}

	/**
	 * Algoritmo de Bellman-Ford.
	 * 
	 * Se le pasan por parametro un Grafo g, una cadena que indica el nodo de origen
	 * y otra cadena que indica el nodo destino. Del nodo de origen se obtiene su
	 * valor asignado dentro del grafo con el metodo keyFromString. Se hace lo mismo
	 * para el nodo de destino. Luego se crea un array con la longitud del numero de
	 * vertices del grafo y otro array que se inicializa también como el array
	 * anterior.
	 * 
	 * Dado que con este algoritmo se busca el camino más corto desde un origen a un
	 * destino, se trata de un problema de minimización, con el metodo Arrays.fill
	 * se establece una distancia infinita entre el nodo origen y los demás. Luego
	 * se establece que el predecesor de cada nodo sea -1. La distancia del nodo
	 * origen a si mismo es 0.
	 * 
	 * En el siguiente paso se pretenden minimizar las distancias. Para ello se crea
	 * un bucle externo que itera desde 1 hasta el numero de vertices. esto es así
	 * porque el camino mas corto desde el origen a otro nodo tiene a lo sumo el
	 * numero de vertices-1 aristas. Dentro del bucle exterior se itera sobre el
	 * grafo de aristas donde para cada arista se guarda su origen, destino y peso
	 * en una variable para despues comprobar si la distancia desde el origen es
	 * distinto a infinito y la distancia del origen sumado al peso es menor a la
	 * distancia al nodo destino. En caso de que se cumpliera la condicion, la
	 * distancia al nodo de destino es la del nodo origen mas el peso y el nodo
	 * predecesor pasa a ser el nodo origen.
	 * 
	 * Terminando con este metodo, existe otro bucle que sirve para los ciclos
	 * negativos. Si la distancia al nodo de origen es infinita o su distancia del
	 * nodo origen, sumando el peso, al nodo destino es mas grande, lanza una
	 * excepcion.
	 * 
	 * Por ultimo hay una salida por pantalla diciendo si hay camino o no lo hay.
	 * Obtiene cual es el camino usando la funcion obtenerCaminoIntermedio.
	 *
	 * @param g        the g
	 * @param norigen  the norigen
	 * @param ndestino the ndestino
	 */
	public static void BF(Grafo g, String norigen, String ndestino) {
		int origen = Grafo.keyFromString(norigen);
		int destino = Grafo.keyFromString(ndestino);
		int[] distancia = new int[g.nV];
		int[] predecesor = new int[g.nV];
		Arrays.fill(distancia, Integer.MAX_VALUE);
		Arrays.fill(predecesor, -1);
		distancia[origen] = 0;

		for (int i = 1; i < g.nV; i++) {
			for (Arista a : g.aristas) {
				int o = a.origen;
				int d = a.destino;
				int p = a.peso;
				if (distancia[o] != Integer.MAX_VALUE && distancia[o] + p < distancia[d]) {
					distancia[d] = distancia[o] + p;
					predecesor[d] = o;
				}
			}
		}

		for (Arista a : g.aristas) {
			int o = a.origen;
			int d = a.destino;
			int p = a.peso;
			if (distancia[o] != Integer.MAX_VALUE && distancia[o] + p < distancia[d]) {
				throw new RuntimeException("Existen ciclos negativos en el grafo");
			}
		}

		if (distancia[destino] == Integer.MAX_VALUE) {
			System.out.println("No hay camino desde " + norigen + " hasta " + ndestino);
		} else {
			obtenerCamino(predecesor, destino);
			System.out.println("El coste desde " + norigen + " hasta " + ndestino + " es " + distancia[destino]);
		}
	}

	/**
	 * Este es un ayudante para el algoritmo de Bellman-Ford. Es practicamente igual
	 * que el metodo anteriormente explicado pero cambia en que termina devolviendo
	 * la distancia desde el nodo origen al nodo destino. El anterior era de tipo
	 * void.
	 *
	 * @param g        the g
	 * @param norigen  the norigen
	 * @param ndestino the ndestino
	 * @return the int
	 */
	public static int BFHelper(Grafo g, String norigen, String ndestino) {
		int origen = Grafo.keyFromString(norigen);
		int destino = Grafo.keyFromString(ndestino);
		int[] distancia = new int[g.nV];
		int[] predecesor = new int[g.nV];
		Arrays.fill(distancia, Integer.MAX_VALUE);
		Arrays.fill(predecesor, -1);
		distancia[origen] = 0;

		for (int i = 1; i < g.nV; i++) {
			for (Arista a : g.aristas) {
				int o = a.origen;
				int d = a.destino;
				int p = a.peso;
				if (distancia[o] != Integer.MAX_VALUE && distancia[o] + p < distancia[d]) {
					distancia[d] = distancia[o] + p;
					predecesor[d] = o;
				}
			}
		}

		// Verificar ciclos negativos
		for (Arista a : g.aristas) {
			int o = a.origen;
			int d = a.destino;
			int p = a.peso;
			if (distancia[o] != Integer.MAX_VALUE && distancia[o] + p < distancia[d]) {
				throw new RuntimeException("Existen ciclos negativos en el grafo");
			}
		}

		if (distancia[destino] == Integer.MAX_VALUE) {
			System.out.println("No hay camino desde " + norigen + " hasta " + ndestino);
		} else {
			obtenerCamino(predecesor, destino);
		}
		return distancia[destino];
	}

	/**
	 * Este es el algoritmo de Bellman-Ford donde se le pasan por parametro del
	 * Grafo g, el nodo de origen, el nodo de destino y el nodo intermedio. Se
	 * encarga de sacar por pantalla la distancia del nodo origen al nodo de destino
	 * enseñando el nodo intermedio por el que pasa.
	 * 
	 * Para ello calcula la distancia del nodo de origen al nodo intermedio usando
	 * el metodo BFHelper anteriormente explicado. Tambien calcula la distancia
	 * entre el nodo intermedio al destino.
	 * 
	 * Este metodo termina mostrando por pantalla la distancia del camino entre el
	 * nodo origen y destino.
	 *
	 * @param g           the g
	 * @param norigen     the norigen
	 * @param ndestino    the ndestino
	 * @param nintermedio the nintermedio
	 */
	public static void BFIntermedio(Grafo g, String norigen, String ndestino, String nintermedio) {
		System.out.print("Camino: ");
		int dist1 = BFHelper(g, norigen, nintermedio);
		int dist2 = BFHelper(g, nintermedio, ndestino);
		System.out.println("\n" + "La distancia del camino entre, " + norigen + " y " + ndestino
				+ " con nodo intermedio en " + nintermedio + " es: " + (dist1 + dist2));
	}

	/**
	 * Este metodo se trata del algoritmo de Dijkstra.
	 * 
	 * Empieza como el algoritmo de Bellman-Ford. Se le pasan por parametro un Grafo
	 * g, una cadena que indica el nodo de origen y otra cadena que indica el nodo
	 * destino. Del nodo de origen se obtiene su valor asignado dentro del grafo con
	 * el metodo keyFromString. Se hace lo mismo para el nodo de destino. Luego se
	 * crea un array con la longitud del numero de vertices del grafo y otro array
	 * que se inicializa también como el array anterior.
	 * 
	 * Con el metodo Arrays.fill se establece una distancia infinita entre el nodo
	 * origen y los demás. Luego se establece que el predecesor de cada nodo sea -1.
	 * La distancia del nodo origen a si mismo es 0.
	 * 
	 * A continuacion, se usa un bucle desde i = 0 hasta el numero de vertices del
	 * grafo g. En él, se crea una variable "o" que indica el origen y este se
	 * inicializa con el nodo no visitado con distancia minima al nodo de origen
	 * usando el metodo verticeDistanciaMinima. Luego para el vertice o, se dice que
	 * es visitado y se comprueba si es el destino. En caso de que fuera, se hace un
	 * break para terminar. Si no lo fuera, se iteran sobre las aristas.
	 * 
	 * Si el origen de la arista que se esta iterando es igual que el vertice o
	 * entonces se inicializa una variable d con su destino y una variable p con su
	 * peso. Si el destino no ha sido visitado y la distancia al origen es diferente
	 * a infinito y la distancia del origen sumado al peso es menor que la distancia
	 * al destino, entonces la distancia al destino es la distancia al origen mas el
	 * peso y el nodo predecesor pasa a ser el nodo origen.
	 * 
	 * Para terminar, si la distancia al destino es Infinito, se indica que no hay
	 * camino. En caso de que no fuera Infinito, se obtiene el camino con la funcion
	 * obtenerCaminoIntermedio entre el array con los nodos anteriores al destino y
	 * el destino. Para finalizar, se muestra la distancia entre el origen y el
	 * destino.
	 *
	 * @param g        the g
	 * @param norigen  the norigen
	 * @param ndestino the ndestino
	 */
	public static void DJK(Grafo g, String norigen, String ndestino) {
		int origen = Grafo.keyFromString(norigen);
		int destino = Grafo.keyFromString(ndestino);
		int[] distancia = new int[g.nV];
		int[] predecesor = new int[g.nV];
		boolean[] visitados = new boolean[g.nV];
		Arrays.fill(distancia, Integer.MAX_VALUE);
		Arrays.fill(predecesor, -1);
		distancia[origen] = 0;

		for (int i = 0; i < g.nV; i++) {
			int o = verticeDistanciaMinima(distancia, visitados);
			visitados[o] = true;
			if (o == destino) {
				break; // detener la b�squeda si se ha alcanzado el nodo destino
			}
			for (Arista a : g.aristas) {
				if (a.origen == o) {
					int d = a.destino;
					int p = a.peso;
					if (!visitados[d] && distancia[o] != Integer.MAX_VALUE && distancia[o] + p < distancia[d]) {
						distancia[d] = distancia[o] + p;
						predecesor[d] = o;
					}
				}
			}
		}

		if (distancia[destino] == Integer.MAX_VALUE) {
			System.out.println("No hay camino desde " + norigen + " hasta " + ndestino);
		} else {
			obtenerCamino(predecesor, destino);
			System.out.println("El coste desde " + norigen + " hasta " + ndestino + " es " + distancia[destino]);
		}
	}

	/**
	 * Este metodo es una ayuda del anterior. Funciona de la misma manera que el
	 * anterior solo que en la condicion donde se comprueba si la distancia al
	 * destino es Infinito, en caso de que no sea, se llama al metodo
	 * obtenerCaminoIntermedio.
	 * 
	 * Este metodo termina devolviendo la distancia al nodo destino.
	 *
	 * @param g        the g
	 * @param norigen  the norigen
	 * @param ndestino the ndestino
	 * @return the int
	 */
	public static int DJKHelper(Grafo g, String norigen, String ndestino) {
		int origen = Grafo.keyFromString(norigen);
		int destino = Grafo.keyFromString(ndestino);
		int[] distancia = new int[g.nV];
		int[] predecesor = new int[g.nV];
		boolean[] visitados = new boolean[g.nV];
		Arrays.fill(distancia, Integer.MAX_VALUE);
		Arrays.fill(predecesor, -1);
		distancia[origen] = 0;

		for (int i = 0; i < g.nV; i++) {
			int o = verticeDistanciaMinima(distancia, visitados);
			visitados[o] = true;
			if (o == destino) {
				break; // detener la b�squeda si se ha alcanzado el nodo destino
			}
			for (Arista a : g.aristas) {
				if (a.origen == o) {
					int d = a.destino;
					int p = a.peso;
					if (!visitados[d] && distancia[o] != Integer.MAX_VALUE && distancia[o] + p < distancia[d]) {
						distancia[d] = distancia[o] + p;
						predecesor[d] = o;
					}
				}
			}
		}

		if (distancia[destino] == Integer.MAX_VALUE) {
			System.out.println("No hay camino desde " + norigen + " hasta " + ndestino);
		} else {
			obtenerCamino(predecesor, destino);
		}
		return distancia[destino];
	}

	/**
	 * Esta implementacion devuelve la distancia entre el nodo de origen y el de
	 * destino pasando por un nodo intermedio.
	 * 
	 * Se le pasan como parametro el grafo g, el nodo de origen, el nodo destino y
	 * el nodo entre ellos dos. Se calcula la distancia del origen al intermedio y
	 * del intermedio al destino para terminar mostrando por pantalla su valor.
	 *
	 * @param g           the g
	 * @param norigen     the norigen
	 * @param ndestino    the ndestino
	 * @param nintermedio the nintermedio
	 */
	public static void DJKIntermedio(Grafo g, String norigen, String ndestino, String nintermedio) {
		System.out.print("Camino:");
		int dist1 = DJKHelper(g, norigen, nintermedio);
		int dist2 = DJKHelper(g, nintermedio, ndestino);
		System.out.println("\n" + "La distancia del camino entre, " + norigen + " y " + ndestino
				+ " con nodo intermedio en " + nintermedio + " es: " + (dist1 + dist2));
	}

	/**
	 * Este metodo es la implementacion del algoritmo de Floyd.
	 * 
	 * Se le pasan por parametro el Grafo g, el nodo de origen y el nodo de destino.
	 * 
	 * Primero crea una matriz de distancias con el numero de vertices del grafo g
	 * como longitud de las columnas y filas y se repite para una matriz de nodos
	 * predecesores. Luego se crean dos variables, origen y fin, a las que se les da
	 * su valor asignado dentro del grafo.
	 * 
	 * Luego se inicializan las dos matrices antes mencionadas con Infinito para
	 * matrizDistancia y -1 para matrizPredecesor.
	 * 
	 * A continuacion, se recorren las aristas del grafo g donde para cada arista se
	 * coge su origen y fin. Luego, dentro de la matriz de distancias en la posicion
	 * origen-destino se pone su valor distancia. Y dentro de matrizPredecesor en la
	 * posicion origen-destino se pone el nodo origen.
	 *
	 * Con el ultimo bucle se recorre desde i=0 hasta i<el numero de vertices del
	 * grafo donde se rellenan con 0 la diagonal de la matrizDistancia. Con la
	 * matrizPredecesor se rellena su diagonal [i][i] con i para poder indicar que
	 * el predecesor del vértice en un camino más corto hacia si mismo es el propio
	 * vértice.
	 * 
	 * Continuando con el algoritmo, hay un bucle triple donde se comprueba si
	 * existe un camino mas corto entre los vertices i y j pasando por el vertice k.
	 * En caso de que la condicion se cumpliera, la distancia entre i y j se
	 * actualiza con la distancia i-k + k-j. Luego la en la matriz con vertices
	 * predecesores se establece que en i-j el nodo el k-j.
	 * 
	 * Para terminar si la distancia origen-destino es infinita no hay camino. En
	 * caso de que no fuera infinito, obtiene el camino y muestra la distancia entre
	 * origen y destino.
	 * 
	 * @param g        the g
	 * @param norigen  the norigen
	 * @param ndestino the ndestino
	 */
	public static void FloydWarshall(Grafo g, String norigen, String ndestino) {
		int[][] matrizDistancia = new int[g.nV][g.nV];
		int[][] matrizPredecesor = new int[g.nV][g.nV];
		int origen = Grafo.keyFromString(norigen);
		int destino = Grafo.keyFromString(ndestino);

		for (int i = 0; i < g.nV; i++) {
			for (int j = 0; j < g.nV; j++) {
				matrizDistancia[i][j] = Integer.MAX_VALUE;
				matrizPredecesor[i][j] = -1;
			}
		}

		for (Arista a : g.aristas) {
			int i = a.origen;
			int j = a.destino;
			matrizDistancia[i][j] = a.peso;
			matrizPredecesor[i][j] = i;
		}
		for (int i = 0; i < g.nV; i++) {
			matrizDistancia[i][i] = 0;
			matrizPredecesor[i][i] = i;
		}

		for (int k = 0; k < g.nV; k++) {
			for (int i = 0; i < g.nV; i++) {
				for (int j = 0; j < g.nV; j++) {
					if (matrizDistancia[i][k] != Integer.MAX_VALUE && matrizDistancia[k][j] != Integer.MAX_VALUE
							&& matrizDistancia[i][k] + matrizDistancia[k][j] < matrizDistancia[i][j]) {
						matrizDistancia[i][j] = matrizDistancia[i][k] + matrizDistancia[k][j];
						matrizPredecesor[i][j] = matrizPredecesor[k][j];
					}
				}
			}
		}

		if (matrizDistancia[origen][destino] == Integer.MAX_VALUE) {
			System.out.println("No hay camino desde " + norigen + " hasta " + ndestino);
		} else {
			obtenerCaminoFW(matrizPredecesor, origen, destino);
			System.out.println(
					"El coste desde " + norigen + " hasta " + ndestino + " es " + matrizDistancia[origen][destino]);
		}
	}

	/**
	 * Este metodo esta encargado de devolver el camino de Floyd-Warshall.
	 * 
	 * Se le pasan por paramatros la matrizPredecesor, el origen y el destino.
	 * 
	 * Se crea una lista camino que se inicializa como ArrayList. Se añade a esta
	 * lista el destino. se iguala un entero i al destino. Se hace un bucle while
	 * donde mientras i sea distinto al origen, i será el nodo origen-i y se
	 * guardará en la lista.
	 * 
	 * Una vez que estan los nodos que forman el camino dentro de la lista, se
	 * revierte el orden de la lista. Se crea una variable StringBuilder. En la
	 * linea siguiente se recorrera la lista camino y se concatenará el valor de la
	 * posicion de j. En caso de que j sea menor que el tamaño de la lista -1,
	 * añadirá =>.
	 *
	 * @param matrizPredecesor the matriz predecesor
	 * @param origen           the origen
	 * @param destino          the destino
	 */
	private static void obtenerCaminoFW(int[][] matrizPredecesor, int origen, int destino) {
		List<Integer> camino = new ArrayList<>();
		camino.add(destino);
		int i = destino;
		while (i != origen) {
			i = matrizPredecesor[origen][i];
			camino.add(i);
		}
		Collections.reverse(camino);
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < camino.size(); j++) {
			sb.append(Grafo.stringFromKey(camino.get(j)));
			if (j < camino.size() - 1) {
				sb.append(" => ");
			}
		}
		System.out.println("Camino: " + sb.toString());
	}

	/**
	 * Obtiene el camino entre un nodo predecesor y el nodo destino.
	 *
	 * @param predecesor the predecesor
	 * @param destino    the destino
	 */
	private static void obtenerCamino(int[] predecesor, int destino) {
		int i = destino;
		String s = "";
		while (predecesor[i] != -1) {
			s = Grafo.stringFromKey(i) + " => " + s;
			caminoResultado.add(Grafo.stringFromKey(i));
			i = predecesor[i];
			caminoResultado.add(Grafo.stringFromKey(i));
		}
		System.out.print(" " + Grafo.stringFromKey(i) + " => " + s);
	}

	/**
	 * Vertice distancia minima. Devuelve el vertice con distancia minima.
	 *
	 * @param distancia the distancia
	 * @param visitados the visitados
	 * @return the int
	 */
	private static int verticeDistanciaMinima(int[] distancia, boolean[] visitados) {
		int min = Integer.MAX_VALUE;
		int posMin = -1;
		for (int i = 0; i < visitados.length; i++) {
			if (!visitados[i] && distancia[i] < min) {
				min = distancia[i];
				posMin = i;
			}
		}
		return posMin;
	}
}