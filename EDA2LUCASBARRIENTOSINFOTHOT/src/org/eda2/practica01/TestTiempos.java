package org.eda2.practica01;

import java.io.File;

public class TestTiempos {

	
	/**
   *
	 * Ruta del archivo que representará el array de números que utilizarán los algoritmos a testear.
	 * Si se quiere cambiar el archivo, no es más que cambiar la ruta de la siguiente variable.
	 * 
	 * Para hacer uso de un archivo que represente n elementos use los archivos proporcionados
	 * con el generador de manera que prueba1000 tiene 1000 elementos, prueba 10000 tiene 10000...
	 * 
	 * En caso de querer usar otro archivo con un número concreto de elementos haga uso
	 * del generador. 
	 * 
	 */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org" +  
			File.separator + "eda2" + File.separator + "practica01" + File.separator + "Prueba10.txt" + File.separator;

	/**
	 * The main method.
	 *
	 * En este método obtenemos el tiempo que tardan los distintos algoritmos implementados en obtener 
	 * el mayor subarray para el array obtenido a partir del archivo de la ruta especificada en la variable
	 * ruta.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] prueba = DyV.loadFile(ruta);
		long start = 0;
		long end = 0;

		start = System.nanoTime();
		System.out.print(DyV.maxSubarraySumOn3(prueba)+"\t\t || Tiempo: ");
		end = System.nanoTime();
		System.out.println((end-start)+" nanosegundos");
		
		start = System.nanoTime();
		System.out.print(DyV.maxSubarraySumOn2(prueba)+"\t\t || Tiempo: ");
		end = System.nanoTime();
		System.out.println((end-start)+" nanosegundos");
		
		start = System.nanoTime();
		System.out.print(DyV.maxSubarraySumOn1(prueba)+"\t\t || Tiempo: ");
		end = System.nanoTime();
		System.out.println((end-start)+" nanosegundos");
		
		start = System.nanoTime();
		System.out.print(DyV.maxSubarraySumDYVOnlogn(prueba)+"\t || Tiempo: ");
		end = System.nanoTime();
		System.out.println((end-start)+" nanosegundos");
		
		start = System.nanoTime();
		System.out.print(DyV.maxSubarraySumDYVOn(prueba)+"\t || Tiempo: ");
		end = System.nanoTime();
		System.out.println((end-start)+" nanosegundos");
				
	}
}