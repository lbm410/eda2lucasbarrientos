package org.eda2.practica01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Generador {

	/** Ruta donde se generará el archivo con los números. */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org" +  
			File.separator + "eda2" + File.separator + "practica01" + File.separator;
	
	/**
	 * The main method.
	 *
	 * NOTA: En caso de querer generar un archivo con más o menos de 10 números, modificar el valor del primer parámetro 
	 * del método de la línea 25.
	 *
	 * @param args 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		long startNano = System.nanoTime();
		long startMili = System.currentTimeMillis();
		generacionNumeros(25000,"Prueba25mil.txt");	
		long endNano = System.nanoTime();
		long endMili = System.currentTimeMillis();
		System.out.println("Tiempo de ejecucion: " + (endNano-startNano) + " nanosegundos. || " + (endMili-startMili) + " milisegundos.");
	}
	
	/**
	 * Generacion de numeros.
	 *
	 * NOTA: Dado que el la función math random genera números aleatorios entre 0 y 1, los números negativos para nosotros serán aquellos 
	 * menores de 0.5, de ahí multiplicar estos por "-1".
	 *
	 * @param cantidadNumeros 
	 * @param nombreArchivo 
	 * @throws FileNotFoundException 
	 */
	private static void generacionNumeros(int cantidadNumeros, String nombreArchivo) throws FileNotFoundException {
	    PrintWriter pw = new PrintWriter (new File (ruta + nombreArchivo));
	    pw.println(cantidadNumeros);
	    for (int i = 0; i < cantidadNumeros; i++) {
	        int numero = (int)(Math.random()*51); 	// Genera un número entre 0 y 50
	        if(Math.random() < 0.5){ 				// los números negativos serán aquellos menores de 0.5 (EXPLICAR CONCEPTO EN JAVADOC)
	            numero = numero * -1;
	        }
	        pw.println(numero);
	    }
	    pw.close();
	}
}