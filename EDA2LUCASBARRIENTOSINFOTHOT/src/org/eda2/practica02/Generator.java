package org.eda2.practica02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Generator {
	
	/** Ruta donde se generará el archivo con los números. */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org" +  
			File.separator + "eda2" + File.separator + "practica02" + File.separator + "datos" + File.separator;

	public static void main(String[] args) throws FileNotFoundException {
    	long startNano = System.nanoTime();
		long startMili = System.currentTimeMillis();
        if(args.length == 0 || args.length < 2) {
            System.out.println("Se debe cumplir la siguiente sintaxis:"
            		+ "\n\t o Primer argumento: Nombre del archivo. Por ejemplo: 'P01'."
            		+ "\n\t o Segundo argumento: Cantidad de numeros a generar.");
        } else {
            try {
                if(validateArgs(args[0], args[1])) {
            		generateNumbers(args[0], args[1]);	
            		long endNano = System.nanoTime();
            		long endMili = System.currentTimeMillis();
            		System.out.println("Tiempo de ejecucion: " + (endNano-startNano) + " nanosegundos. || " + (endMili-startMili) + " milisegundos.");
                } else {
                    System.out.println("La palabra no es valida.");
                }
            } catch (Exception e) {
                System.out.println("Se ha producido un error: " + e.getMessage());
            }
        }
	}
	
    public static boolean validateArgs(String palabra, String numero) throws Exception {
        if(!palabra.matches("[a-zA-Z0-9]+")) {
            throw new Exception("La palabra contiene caracteres no permitidos.");
        } else {
        	try {
        		Integer.parseInt(numero);
        	} catch (NumberFormatException e) {
        		throw new Exception("El numero contiene caracteres no permitidos.");
        	}
            return true;
        }
    }
	
	private static void generateNumbers(String nombreArchivo, String cantidadNumeros) throws FileNotFoundException {
		int numerosInt = Integer.valueOf(cantidadNumeros);
	    PrintWriter pwC = new PrintWriter (new File (ruta + nombreArchivo + "_c.txt"));
	    pwC.print((int)Math.ceil(numerosInt*(Math.random()*3+1)));
	    pwC.close();
	    
	    PrintWriter pwW = new PrintWriter (new File (ruta + nombreArchivo + "_w.txt"));
	    for (int i = 0; i < numerosInt; i++) {
	    	//Lo divido entre 1.2 para asegurarme que nunca se llegue al valor de la capacidad de la mochila.
	    	//El +1 final es por si se da el caso de que, al hacer la division, se llega a 0.
	        double numero = ((Math.random()*numerosInt+1)/1.2)+1;
	        pwW.println(numero);
	    }
	    pwW.close();
	    
	    PrintWriter pwP = new PrintWriter (new File (ruta + nombreArchivo + "_p.txt"));
	    for (int i = 0; i < numerosInt; i++) {
	        double numero = ((Math.random()*numerosInt+1)/1.2)+1;
	        pwP.println(numero);
	    }
	    pwP.close();
	    
	    PrintWriter pwQ = new PrintWriter (new File (ruta + nombreArchivo + "_q.txt"));
	    for (int i = 0; i < numerosInt; i++) {
	        int numero = (int)Math.ceil(((Math.random()*numerosInt+1)/2)+1);
	        pwQ.println(numero);
	    }
	    pwQ.close();
	}

}