
package org.eda2.practica01;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.Test;

public class DyVTest {
	
	/**
	 * Si se quiere cambiar el archivo, no es más que cambiar la ruta de la siguiente variable.
	 * 
	 * Para hacer uso de un archivo que represente n elementos use los archivos proporcionados
	 * con el generador de manera que prueba1000 tiene 1000 elementos, prueba 10000 tiene 10000...
	 * 
	 * En caso de querer usar otro archivo con un número concreto de elementos haga uso
	 * del generador.
	 */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org" +  
			File.separator + "eda2" + File.separator + "practica01" + File.separator + "Prueba10.txt" + File.separator;
	
	/**
	 * Test para comprobar que se se produzca un error o no dependiendo de si se encuentra el archivo o no.
	 */
	@Test
	public void testReconocerArchivo() {
		assertThrows(RuntimeException.class, () -> DyV.loadFile("NoExiste.txt"));
        assertDoesNotThrow(() -> {DyV.loadFile(ruta);});
	}
	
	/**
	 * Test para comprobar que se lea adecuadamente el archivo (tanto el rango-cantidadDeNumeros, como el array donde se guardan
	 * todos los números contenidos).
	 */
	@Test
	public void testReconocerRangoTxt() {
		int[] returningArray = DyV.loadFile(ruta);
		
		assertEquals(10, DyV.getRango());
		assertArrayEquals(new int[]{-21,-24,-30,46,25,-41,20,-9,-15,3}, returningArray);
	}
	
	/**
	 * Test para comprobar los resultados con los proporcionados en el pdf de la práctica.
	 */
	@Test
	public void testComprobarEjercicios() {
		int[] array1 = {-2, 11, -4, 13, -5, 2};
		int[] array2 = {1, -3, 4, -2, -1, 6};
		int[] array3 = {4, -3, 5, -2, -1, 2, 6, -2};
		
		assertEquals(20, DyV.maxSubarraySumOn3(array1) & DyV.maxSubarraySumOn2(array1) & DyV.maxSubarraySumOn1(array1)
				& DyV.maxSubarraySumDYVOnlogn(array1) & DyV.maxSubarraySumDYVOnlogn(array1));
		assertEquals(7, DyV.maxSubarraySumOn3(array2) & DyV.maxSubarraySumOn2(array2) & DyV.maxSubarraySumOn1(array2)
				& DyV.maxSubarraySumDYVOnlogn(array2) & DyV.maxSubarraySumDYVOnlogn(array2));
		assertEquals(11, DyV.maxSubarraySumOn3(array3) & DyV.maxSubarraySumOn2(array3) & DyV.maxSubarraySumOn1(array3)
				& DyV.maxSubarraySumDYVOnlogn(array3) & DyV.maxSubarraySumDYVOnlogn(array3));

	}

	/**
	 * Test que prueba la funcionalidad de los métodos al recibir un array de positivos.
	 */
	@Test
	public void testMaxSubarraySumDYVOnWithPositiveNumbers() {
		int[] array = {2, 4, 7, 1, 8, 2, 4, 3};
		int expectedMaxSum = 31;
		
		int actualMaxSum3n = DyV.maxSubarraySumOn3(array);
		int actualMaxSum2n = DyV.maxSubarraySumOn2(array);
		int actualMaxSumn = DyV.maxSubarraySumOn1(array);
		int actualMaxSumDyVnlogn = DyV.maxSubarraySumDYVOnlogn(array);
		int actualMaxSumDyVn = DyV.maxSubarraySumDYVOn(array);
		
		assertEquals(expectedMaxSum, actualMaxSum3n);
		assertEquals(expectedMaxSum, actualMaxSum2n);
		assertEquals(expectedMaxSum, actualMaxSumn);
		assertEquals(expectedMaxSum, actualMaxSumDyVnlogn);
		assertEquals(expectedMaxSum, actualMaxSumDyVn);
	}
	
	/**
	 * Test que prueba la funcionalidad de los métodos al recibir un array de negativos.
	 */
	@Test
    public void testMaxSubarraySumDYVOnWithNegativeNumbers() {
        int[] array = {-2, -5, -1, -3, -7, -2, -4, -1};
        int expectedMaxSum = -1;
        
        int actualMaxSum3n = DyV.maxSubarraySumOn3(array);
		int actualMaxSum2n = DyV.maxSubarraySumOn2(array);
		int actualMaxSumn = DyV.maxSubarraySumOn1(array);
		int actualMaxSumDyVnlogn = DyV.maxSubarraySumDYVOnlogn(array);
		int actualMaxSumDyVn = DyV.maxSubarraySumDYVOn(array);
		
		assertEquals(expectedMaxSum, actualMaxSum3n);
		assertEquals(expectedMaxSum, actualMaxSum2n);
		assertEquals(expectedMaxSum, actualMaxSumn);
		assertEquals(expectedMaxSum, actualMaxSumDyVnlogn);
		assertEquals(expectedMaxSum, actualMaxSumDyVn);
    }

}