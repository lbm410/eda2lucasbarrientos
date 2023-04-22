package org.eda2.practica01;

import java.io.File;
import java.util.Scanner;

public class DyV {
		
public static int rango;
	
	/**
	 * Métodos para obtener el valor de rango.
	 *
	 * @return rango
	 */
	public static int getRango() {
		return rango;
	}

	/**
	 * Métodos para establecer el valor de rango.
	 *
	 * @param rango the new rango
	 */
	public static void setRango(int rango) {
		DyV.rango = rango;
	}
	
	/** 
	 * Ruta del archivo que representará el array de números que utilizarán los algoritmos.
	 * Si se quiere cambiar el archivo, no es más que cambiar la ruta de la siguiente variable. 
	 * 
	 */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org" +  
			File.separator + "eda2" + File.separator + "practica01" + File.separator + "Prueba10.txt" + File.separator;

	/**
	 * The main method.
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		
		int[] january = {29,-7,14,31,1,-47,30,7,-39,23,-20,-36,-41,27,15,-34,48,35,-46,-16,32,18,5,-33,27,-28,-22,12,11,-42,13};
		int[] prueba = loadFile(ruta);
		
		System.out.println("---------EJERCICIO_ENERO---------\n");
		System.out.println(maxSubarraySumOn3(january));
		System.out.println(maxSubarraySumOn2(january));
		System.out.println(maxSubarraySumOn1(january));
		System.out.println(maxSubarraySumDYVOnlogn(january));
		System.out.println(maxSubarraySumDYVOn(january));
		System.out.println("\n---------ARCHIVO_DE_PRUEBA---------\n");
		System.out.println(maxSubarraySumOn3(prueba));
		System.out.println(maxSubarraySumOn2(prueba));
		System.out.println(maxSubarraySumOn1(prueba));
		System.out.println(maxSubarraySumDYVOnlogn(prueba));
		System.out.println(maxSubarraySumDYVOn(prueba));
	}
	
	/**
	 * Con este método definimos un método que carga un archivo de texto y devuelve un array de enteros.
	 * 
	 * En resumidas cuenta, inicializamos una variable de tipo Scanner "scan" y otra de tipo String "linea", en la primera 
	 * almacenamos el archivo con lo números que después guardaremos en un array. Por otra parte la segunda variable irá almacenando 
	 * cada una de las líneas en las que habrá un número. El bucle for tendrá tantas iteraciones como líneas (números) tenga el 
	 * archivo y las irá almacenando en el array "returningArray".
	 *
	 * @param archivo
	 * @return int[]
	 */
	public static int[] loadFile(String archivo) {
		Scanner scan = null;
		String linea = "";

		try {
			scan = new Scanner(new File(archivo));
		} catch (Exception ex) {
			throw new RuntimeException("Error al cargar el archivo. El sistema no puede encontrar el archivo especificado.");
		}
		
		linea = scan.nextLine();
		setRango(Integer.valueOf(linea));
		int returningArray[] = new int[rango];
		for (int i = 0; i < rango; i++) {
			returningArray[i] = Integer.valueOf(scan.nextLine());
		}
		return returningArray;
	}
	
	/**
	 * Algoritmo iterativo de coste O(n^3).
	 *
	 * En este método implementamos un algoritmo de fuerza bruta para obtener un subarray donde la suma de sus valores resulta ser
	 * la máxima de todo el array. Para ello hacemos uso de tres bucles for anidados donde el primero recorre todas las posibles
	 * posiciones iniciales, el segundo realiza el mismo procedimiento para la posición final mientras que el bucle interior
	 * realiza la suma.
	 * 
	 * Este algoritmo actualiza la variable maxSum en cada iteración completa almacenando así el mayor resultado.
	 * Lo mismo sucede en cada iteración para las variables "start" y "end" que almacenan las posiciones inicial
	 * y final del subarray final.
	 * 
	 * @param array 
	 * @return int
	 */
	public static int maxSubarraySumOn3(int[] array) {
	    int n = array.length;
	    int maxSum = Integer.MIN_VALUE;
	    int start = 0, end = 0, auxStart = 0, auxEnd = 0;

	    for (int i = 0; i < n; i++) {
	        for (int j = i; j < n; j++) {
	            int sum = 0;
	            for (int k = i; k <= j; k++) {
	                    sum += array[k];
	                    auxStart = i;
	                    auxEnd = j;
	                    
	            }
	            if (sum > maxSum) {
	                maxSum = sum;
                    start = auxStart;
                    end = auxEnd;
	            }
	        }
	    }
	    System.out.print("O(n^3) -> El rango es el siguiente: " + start + "-" + end + " y la suma máxima es ");
	    return maxSum;

	}
	
	/**
	 * Algoritmo iterativo de coste O(n^2).
	 *
	 * En este método obtenemos un subarray donde la suma de sus valores resulta ser la máxima de todo el array. 
	 * El primer bucle exterior recorre todas las posibles posiciones de inicio de la submatriz, mientras que 
	 * el segundo recorre todas las posibles posiciones finales de la submatriz, comenzando desde la posición de inicio actual. 
	 * 
	 * La diferencia con el método anterior reside en el if que suple la función del tercer for, este, para cada iteración completa,
	 * en caso de ser la suma mayor actualiza la variable que almacena la suma sin necesidad de realizar una tercera iteración.
	 * 
	 * @param array 
	 * @return int
	 */
	public static int maxSubarraySumOn2(int[] array) {

		int max = Integer.MIN_VALUE;
	    int start = 0, end = 0, auxStart = 0, auxEnd = 0;
		
		for(int i = 0; i < array.length; i++) {
		    int sum = 0;
		    for (int j = i; j < array.length; j++) {
		        sum += array[j];
		        auxStart=i;
		        auxEnd=j;
		        if (sum > max) {
		            max = sum; 
		            start=auxStart;
		            end=auxEnd;
		        } 
		    }
		}
		System.out.print("O(n^2) -> El rango es el siguiente: " + start + "-" + end + " y la suma máxima es ");
		return max;
	
	}
	
	/**
	 * Algoritmo iterativo de coste O(n).
	 * 
	 * En este método implementamos un algoritmo de Kadane para obtener un subarray donde la suma de sus valores resulta ser
	 * la máxima de todo el array. El proceso consiste en recorrer el arreglo y en cada iteración agregar el valor actual a la suma 
	 * actual. Si la suma actual es mayor que la suma máxima, actualiza la suma máxima y el índice final del subarreglo, mientras 
	 * que si la suma actual es menor que cero, se reinicia la suma actual y se actualiza el índice de inicio del subarreglo
	 *
	 * @param array 
	 * @return int
	 */
	
	public static int maxSubarraySumOn1(int[] array) { 
	    int n = array.length;
	    int maxSum = Integer.MIN_VALUE;
	    int currentSum = 0;
	    int start = 0, end = 0;
	    for (int i = 0; i < n; i++) {
	        currentSum += array[i];
	        if (currentSum > maxSum) {
	            maxSum = currentSum;
	            end = i;
	        }
	        if (currentSum < 0) {
	            currentSum = 0;
	            start = i+1;
	        }
	    } 
		System.out.print("O(n) -> El rango es el siguiente: " + start + "-" + end + " y la suma máxima es ");
	    return maxSum;
	}
	
	/**
	 * Algoritmo de tipo divide-and-conquer de coste O(n log n).
	 * 
	 * Este algoritmo viene resuelto con la implementación de los tres siguientes métodos que 
	 * explicamos en su correspondiente cabecera:
	 * 
	 * En este primer método simplemente se llama al segundo método "maxSubarraySumAux" para luego 
	 * devolvernos el subarray solución. Una vez obtenemos los índices que representan el rango del 
	 * subarray solución guardados en las posiciones 0 y 1 del array result, y la suma de los valores 
	 * de este rango, se imprimen por pantalla.
	 *
	 * @param array 
	 * @return int
	 */
	public static int maxSubarraySumDYVOnlogn(int[] array) {
	    int[] result = maxSubarraySumAux(array, 0, array.length - 1);
	    System.out.print("DyV_O(nlog(n)) -> El rango es el siguiente: " + result[0] + "-" + result[1] + " y la suma máxima es ");
	    return result[2];
	}
	
/**
 * 
 * En este método se comienza comprobando si el array de entrada es un solo número para 
 * devolverlo directamente como solución. De lo contrario, se divide el array de entrada
 * en dos, la parte izquierda y la derecha, realizando una llamada recursiva para cada subarray.
 * 
 * Además, se hace una llamada al método maxCrossingSubArraySum, que nos dará el resultado del 
 * subarray solución que cruce por la división entre los subarrays izquierdo y derecho.
 * 
 * Finalmente comprueba los array solucion izquierdo, derecho y el resultante del metodo anterior
 * y devuelve la mejor solución.
 * 
 * @param array
 * @param left
 * @param right
 * @return
 */
	private static int[] maxSubarraySumAux(int[] array, int left, int right) {
	    if (left == right) {
	        return new int[] {left, right, array[left]};
	    }
	    int middle = (left + right) / 2;
	    int[] leftMaxSum = maxSubarraySumAux(array, left, middle);
	    int[] rightMaxSum = maxSubarraySumAux(array, middle + 1, right);
	    int[] crossingMaxSum = maxCrossingSubarraySum(array, left, middle, right);
	    if (leftMaxSum[2] >= rightMaxSum[2] && leftMaxSum[2] >= crossingMaxSum[2]) {
	        return leftMaxSum;
	    } else if (rightMaxSum[2] >= leftMaxSum[2] && rightMaxSum[2] >= crossingMaxSum[2]) {
	        return rightMaxSum;
	    } else {
	        return crossingMaxSum;
	    }
	}
	
/**
 * Este método llamado en el anterior nos devuelve un subarray máximo que contiene al elemento 
 * que se encuentra entre el array de la izquierda y el de la derecha.
 * 
 * @param array
 * @param left
 * @param mid
 * @param right
 * @return
 */
	private static int[] maxCrossingSubarraySum(int[] array, int left, int mid, int right) {
	    int leftSum = Integer.MIN_VALUE;
	    int sum = 0;
	    int maxLeft = mid;
	    for (int i = mid; i >= left; i--) {
	        sum += array[i];
	        if (sum > leftSum) {
	            leftSum = sum;
	            maxLeft = i;
	        }
	    }
	    int rightSum = Integer.MIN_VALUE;
	    sum = 0;
	    int maxRight = mid + 1;
	    for (int i = mid + 1; i <= right; i++) {
	        sum += array[i];
	        if (sum > rightSum) {
	            rightSum = sum;
	            maxRight = i;
	        }
	    }
	    return new int[] {maxLeft, maxRight, leftSum + rightSum};
	}

	
    /**
     * Algoritmo de tipo divide-and-conquer de coste O(n).
     *
     * En este método, el algoritmo va sumando los elementos del array uno por uno, y en cada paso 
     * decide si incluir el elemento actual en la subsecuencia máxima o comenzar una nueva 
     * subsecuencia desde el elemento actual. De esta manera, se va actualizando la suma 
     * máxima en cada paso. Este además va guardando los índices correspondientes a la posición
     * inicial y final del subarray solución para devolverlos finalmente junto con la suma máxima.
     * 
     * @param array 
     * @return int
     */
    
    public static int maxSubarraySumDYVOn(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int maxSum = array[0];
        int currentSum = array[0];
        int start = 0;
        int end = 0;
        int currentStartIndex = 0;


        for (int i = 1; i < array.length; i++) {
            if (currentSum + array[i] < array[i]) {
                currentStartIndex = i;
                currentSum = array[i];
            } else {
                currentSum += array[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = currentStartIndex;
                end = i;
            }
        }

        System.out.print("DyV_O(n) -> El rango es el siguiente: " + start + "-" + end + " y la suma máxima es ");

        return maxSum;

    }
    	    	  		
}