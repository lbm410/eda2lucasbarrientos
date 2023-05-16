package org.eda2.practica03;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class TestP3 {

	private static String grafoPrueba = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
			+ File.separator + "eda2" + File.separator + "practica03" + File.separator + "datos" + File.separator
			+ "grafoTest.txt" + File.separator;

	private static String grafoEDA = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
			+ File.separator + "eda2" + File.separator + "practica03" + File.separator + "datos" + File.separator
			+ "graphEDAland.txt" + File.separator;

	/**
	 * Comprueba que se crea el Grafo y que no sea dirigido.
	 */
	@Test
	public void testReconocerArchivo() {
		Grafo g = new Grafo(grafoPrueba);
		assertNotNull(g);
		assertTrue(g.directed == 0);
	}

	/**
	 * Metodo que comprueba la cantidad de vertices y aristas.
	 */
	@Test
	public void atributosGrafo() {
		Grafo g2 = new Grafo(grafoPrueba);
		assert (g2.nA == 7);
		assert (g2.nV == 6);
	}

	/**
	 * Comprueba que la lista tiene las ciudades que deben tener.
	 */
	@Test
	public void pruebaContenidoCaminoResultado() {
		Grafo g3 = new Grafo(grafoPrueba);
		String origen = "El_Ejido";
		String destino = "Retamar";
		MainGrafo.BF(g3, origen, destino);

		assert (MainGrafo.getCaminoResultado().contains("Retamar"));
		assert (MainGrafo.getCaminoResultado().contains("El_Ejido"));
		assert (MainGrafo.getCaminoResultado().contains("Almeria"));
		assert (false == MainGrafo.getCaminoResultado().contains("Huercal_Overa"));

	}

	/**
	 * Comprueba que la distancia entre si mismo es 0
	 */
	@Test
	public void pruebaDistanciaBellmanFord() {
		Grafo g3 = new Grafo(grafoPrueba);
		String origen = "El_Ejido";
		String destino = "El_Ejido";
		MainGrafo.BF(g3, origen, destino);
		assert (MainGrafo.BFHelper(g3, origen, destino) == 0);
	}

	@Test
	public void pruebaDistanciaDijkstra() {
		Grafo g7 = new Grafo(grafoPrueba);
		String origen = "El_Ejido";
		String destino = "Aguadulce";
		MainGrafo.DJKHelper(g7, origen, destino);
		assert(MainGrafo.BFHelper(g7, origen, destino) == 26);
	}
	
	/**
	 * Prueba que la salida por pantalla es la esperada usando BellmanFord.
	 */
	@Test
	public void pruebaBellmanFord() {
		Grafo g4 = new Grafo(grafoPrueba);
		String origen = "Aguadulce";
		String destino = "Retamar";
		MainGrafo.BF(g4, origen, destino);

		assert (MainGrafo.getCaminoResultado().contains("Aguadulce"));
		assert (MainGrafo.getCaminoResultado().contains("Retamar"));
		assert (MainGrafo.getCaminoResultado().contains("Almeria"));

	}

	/**
	 * Comprueba que la salida por pantalla es la esperada para Dijkstra.
	 */
	@Test
	public void pruebaDijsktra() {
		Grafo g5 = new Grafo(grafoPrueba);
		String origen = "Aguadulce";
		String destino = "Retamar";
		MainGrafo.DJK(g5, origen, destino);

		assert (MainGrafo.getCaminoResultado().contains("Aguadulce"));
		assert (MainGrafo.getCaminoResultado().contains("Retamar"));
		assert (MainGrafo.getCaminoResultado().contains("Almeria"));

	}

	/**
	 * Comprueba que la salida por pantalla es la esperada para Floyd.
	 */
	@Test
	public void pruebaFloyd() {
		Grafo g6 = new Grafo(grafoPrueba);
		String origen = "Aguadulce";
		String destino = "Nijar";
		MainGrafo.FloydWarshall(g6, origen, destino);
		
	}
	
	/**
	 * Los tres deben dar 814
	 */
	@Test
	public void pruebaTodosMetodos() {
		Grafo g0 = new Grafo(grafoEDA);
		String origen = "Almeria";
		String destino = "Barcelona";
		MainGrafo.BF(g0, origen, destino);
		MainGrafo.DJK(g0, origen, destino);
		MainGrafo.FloydWarshall(g0, origen, destino);
		
		assert(MainGrafo.BFHelper(g0, origen, destino) == MainGrafo.DJKHelper(g0, origen, destino) && MainGrafo.BFHelper(g0, origen, destino) == 814);
	}

}
