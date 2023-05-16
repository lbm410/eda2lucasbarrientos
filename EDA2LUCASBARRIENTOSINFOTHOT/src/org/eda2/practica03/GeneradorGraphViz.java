package org.eda2.practica03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class GeneradorGraphViz {
	
	/**
	 * Definimos las rutas que van a contener los directorios de creación del archivo y la lectura de los contenidos.
	 */

    private static String rutaArchivo = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
            + File.separator + "eda2" + File.separator + "practica03" + File.separator;

    private static String rutaDatos = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
            + File.separator + "eda2" + File.separator + "practica03" + File.separator + "datos" + File.separator;

    public static void main(String[] args) {
    	
    	/**
    	 * Al igual que hemos hecho en otras clases, meramente guardamos en las estructuras que mas nos convengan los 
    	 * datos necesarios. En este caso, los nodos se guardan por orden de insercion y las relaciones se almacenan
    	 * en un arbol para ser mas sencillo localizarlos.
    	 */
        LinkedHashSet<String> nodos = new LinkedHashSet<>();
        HashMap<String, ArrayList<Pair<String, Integer>>> relaciones = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(rutaDatos + "graphEDAland.txt"))) {
            scanner.nextLine();
            int contador = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < contador; i++) {
                String nombre = scanner.nextLine();
                nodos.add(nombre);
                relaciones.put(nombre, new ArrayList<>());
            }

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] partes = scanner.nextLine().split(" ");
                String nodoOrigen = partes[0];
                String nodoDestino = partes[1];
                int peso = Integer.parseInt(partes[2]);
                relaciones.get(nodoOrigen).add(new Pair<>(nodoDestino, peso));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        
        /**
         * Para que el getCaminoResultado() no nos devuelva un contenido vacio, hemos de llamar al metodo main
         * para asi definir cual seria el camino a tomar.
         */
        
        MainGrafo.main(null);
        LinkedHashSet<String> caminoResultado = MainGrafo.getCaminoResultado();

        generarArchivoDot(nodos, relaciones, rutaArchivo + "grafo.dot", caminoResultado);
    }

    public static void generarArchivoDot(HashSet<String> nodos,
            HashMap<String, ArrayList<Pair<String, Integer>>> relaciones, String nombreArchivo,
            LinkedHashSet<String> caminoResultado) {
        try (PrintWriter out = new PrintWriter(nombreArchivo)) {

            out.println("digraph Grafo {");
            out.println("\tfontname=\"Helvetica,Arial,sans-serif\"");
            out.println("\tnode [fontname=\"Helvetica,Arial,sans-serif\", fontsize=19]");
            out.println("\tedge [fontname=\"Helvetica,Arial,sans-serif\"]");
            out.println("\trankdir=LR;\n");

            out.println("\tnode [style=filled, shape=circle, height=0.25]");

            /**
             * Empezamos a escribir los nodos en el .dot.
             */
            for (String nodo : nodos) {
            	/**
            	 * Si no forma parte del camino, este va a ser su color por defecto.
            	 */
                String fillColor = "#9B9B9B"; 
                if (caminoResultado.contains(nodo)) {
                    if (nodo.equals(new ArrayList<>(caminoResultado).get(caminoResultado.size() - 1))) {
                    	/**
                    	 * Verde para el nodo origen.
                    	 */
                        fillColor = "#00FF00"; 
                    } else if (nodo.equals(new ArrayList<>(caminoResultado).get(0))) {
                    	/**
                    	 * Rojo para el nodo destino.
                    	 */
                        fillColor = "#FF0000";  
                    } else {
                    	/**
                    	 * Azul para los nodos intermedios.
                    	 */
                        fillColor = "#0777A2";
                    }
                }
                out.println("\t" + nodo + " [xlabel=\"" + nodo + "\", label=\"\", fillcolor=\"" + fillColor + "\"]");
            }

            out.println("\n\tnode [style=filled, fillcolor=\"#1f77b4\", height=0.5]");

            /**
             * Empezamos a escribir las aristas segun el txt.
             */
            for (String nodoOrigen : relaciones.keySet()) {
                for (Pair<String, Integer> arista : relaciones.get(nodoOrigen)) {
                    String nodoDestino = arista.first;
                    int peso = arista.second;
                    /**
                     * Si no esta incluido en el camino, se pone con el color por defecto.
                     */
                    String color = "#000000"; 
                    if (caminoResultado.contains(nodoOrigen) && caminoResultado.contains(nodoDestino)) {
                    	/**
                    	 * Azul para las aristas del camino optimo.
                    	 */
                        color = "#0777A2";
                    }
                    out.println("\t" + nodoOrigen + " -> " + nodoDestino + " [label=\"" + peso + "\", color=\"" + color
                            + "\"]");
                }
            }

            out.println("}");
            System.out.println("Archivo " + nombreArchivo + " generado exitosamente");

        } catch (FileNotFoundException e) {
            System.err.println("Error al generar el archivo: " + e.getMessage());
        }
    }

}