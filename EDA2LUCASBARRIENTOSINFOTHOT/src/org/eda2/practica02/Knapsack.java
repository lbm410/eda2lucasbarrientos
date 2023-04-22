package org.eda2.practica02;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {

	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
			+ File.separator + "eda2" + File.separator + "practica02" + File.separator + "datos" + File.separator;

	private static int capacidad;
	private static double[] pesos;
	private static double[] valores;
	private static int[] cantidad;

	public static void main(String[] args) {
//      int[] valores = {6,9,12,3};
//      int[] pesos = {3,1,3,3};
//      int[] cantidad = {4,1,2,5};
//      int capacidad = 8;

      
		if (args.length == 0 || args.length < 2) {
			System.out.println(
					"Se debe cumplir la siguiente sintaxis:" + "\n\t o Primer argumento: Nombre del metodo a ejecutar."
							+ "\n\t\t + mochilaFraccionaria" + "\n\t\t + knapsack01" + "\n\t\t + mochilaMixta"
							+ "\n\t\t + knapsackRepeticion" + "\n\t\t + knapsackRepeticionIlimitada" + "\n\t\t + TODOS"
							+ "\n\t o Segundo argumento: Nombre del archivo. Por ejemplo: 'P01'.");
		} else {
			try {
				if (validateArgs(args[0], args[1])) {
					loadFile(args[1]);
					switch (args[0].toLowerCase()) {
					case "mochilafraccionaria":
						double valorMaximo = mochilaFraccionaria(pesos, valores, capacidad);
						System.out.println(
								"El valor máximo que se puede llevar en la mochila fraccionaria es: " + valorMaximo);
						break;
					case "knapsack01":
						double valorMaximo1 = knapsack01(pesos, valores, capacidad);
						System.out.println("El valor máximo que se puede llevar en la mochila 0/1 es: " + valorMaximo1);
						break;
					case "mochilamixta":
						double valorMaximo2 = mochilaMixta(pesos, valores, capacidad);
						System.out
								.println("El valor máximo que se puede llevar en la mochila mixta es: " + valorMaximo2);
						break;
					case "knapsackrepeticion":
						double valorMaximo3 = knapsackRepeticion(pesos, valores, capacidad, cantidad);
						System.out
								.println("El valor máximo que se puede llevar en la mochila con tipos de objetos y cantidades limitadas es: " + valorMaximo3);
						break;
					case "knapsackrepeticionilimitado":
						double valorMaximo4 = knapsackRepeticionIlimitado(pesos, valores, capacidad, cantidad);
						System.out
								.println("El valor máximo que se puede llevar en la mochila con tipos de objetos y cantidades ilimitadas es: " + valorMaximo4);
						break;
					case "todos":
						valorMaximo = mochilaFraccionaria(pesos, valores, capacidad);
						System.out.println("El valor máximo que se puede llevar en la mochila fraccionaria es: "
								+ valorMaximo + "\n");
						valorMaximo1 = knapsack01(pesos, valores, capacidad);
						System.out.println(
								"El valor máximo que se puede llevar en la mochila 0/1 es: " + valorMaximo1 + "\n");
						valorMaximo2 = mochilaMixta(pesos, valores, capacidad);
						System.out.println(
								"El valor máximo que se puede llevar en la mochila mixta es: " + valorMaximo2 + "\n");
						valorMaximo3 = knapsackRepeticion(pesos, valores, capacidad, cantidad);
						System.out
								.println("El valor máximo que se puede llevar en la mochila con tipos de objetos y cantidades limitadas es: " + valorMaximo3 + "\n");
						valorMaximo4 = knapsackRepeticionIlimitado(pesos, valores, capacidad, cantidad);
						System.out
								.println("El valor máximo que se puede llevar en la mochila con tipos de objetos y cantidades ilimitadas es: " + valorMaximo4 + "\n");
						break;
					}
				} else {
					System.out.println("Los argumentos no son validos.");
				}
			} catch (Exception e) {
				System.out.println("Se ha producido un error: " + e.getMessage());
			}
		}
	}

	public static boolean validateArgs(String metodo, String archivo) throws Exception {
		if (!archivo.matches("[a-zA-Z0-9]+")) {
			throw new Exception("El archivo contiene caracteres no permitidos.");
		} else {
			String[] metodosArray = { "mochilafraccionaria", "knapsack01", "mochilamixta", "knapsackrepeticion", "knapsackrepeticionilimitado", "todos" };
			ArrayList<String> metodosList = new ArrayList<>(Arrays.asList(metodosArray));
			if (metodosList.contains(metodo.toLowerCase())) {
				return true;
			} else {
				throw new Exception("El metodo esta mal escrito o no existe.");
			}
		}
	}

	public static boolean loadFile(String archivo) {
		Scanner scan = null;
		String linea = "";

		try {
			scan = new Scanner(new File(ruta + archivo + "_c.txt"));
		} catch (Exception ex) {
			throw new RuntimeException(
					"Error al cargar el archivo. El sistema no puede encontrar el archivo especificado.");
		}

		linea = scan.nextLine();
		capacidad = Integer.valueOf(linea);

		try {
			scan = new Scanner(new File(ruta + archivo + "_w.txt"));
		} catch (Exception ex) {
			throw new RuntimeException(
					"Error al cargar el archivo. El sistema no puede encontrar el archivo especificado.");
		}

		ArrayList<Double> aux = new ArrayList<>();

		linea = scan.nextLine();
		while (linea != null) {
			aux.add(Double.valueOf(linea));
			if (!scan.hasNextLine()) {
				break;
			} else {
				linea = scan.nextLine();
			}
		}

		pesos = aux.stream().mapToDouble(Double::doubleValue).toArray();

		try {
			scan = new Scanner(new File(ruta + archivo + "_p.txt"));
		} catch (Exception ex) {
			throw new RuntimeException(
					"Error al cargar el archivo. El sistema no puede encontrar el archivo especificado.");
		}

		aux = new ArrayList<>();

		linea = scan.nextLine();
		while (linea != null) {
			aux.add(Double.valueOf(linea));
			if (!scan.hasNextLine()) {
				break;
			} else {
				linea = scan.nextLine();
			}
		}

		valores = aux.stream().mapToDouble(Double::doubleValue).toArray();
		
		aux.clear();

		try {
			scan = new Scanner(new File(ruta + archivo + "_q.txt"));
		} catch (Exception ex) {
			throw new RuntimeException(
					"Error al cargar el archivo. El sistema no puede encontrar el archivo especificado.");
		}

		ArrayList<Integer> auxQuantity = new ArrayList<>();

		linea = scan.nextLine();
		while (linea != null) {
			auxQuantity.add(Integer.valueOf(linea));
			if (!scan.hasNextLine()) {
				break;
			} else {
				linea = scan.nextLine();
			}
		}

		cantidad = auxQuantity.stream().mapToInt(Integer::intValue).toArray();

		return true;
	}

	public static double mochilaFraccionaria(double[] pesos, double[] valores, int capacidadFinal) {
		Objeto[] objetos = new Objeto[pesos.length];

		for (int i = 0; i < pesos.length; i++) {
			objetos[i] = new Objeto(pesos[i], valores[i]);
		}

		Arrays.sort(objetos);

		double valorTotal = 0;
		double capacidadActual = 0;

		for (Objeto objeto : objetos) {
			if (capacidadActual + objeto.peso <= capacidadFinal) {
				capacidadActual += objeto.peso;
				valorTotal += objeto.valor;
				System.out.println("Objeto con | Peso= " + objeto.peso + "| Valor = " + objeto.valor);
			} else {
				if ((capacidadFinal - capacidadActual) != 0) {
					double espacioRestante = capacidadFinal - capacidadActual;
					double valorFraccionado = objeto.valor * (espacioRestante / objeto.peso);
					valorTotal += valorFraccionado;
					System.out.println(
							"Objeto normal-> " + objeto.toString() + " <> Objeto Fraccionado Resultante con | Peso= "
									+ espacioRestante + "| Valor = " + valorFraccionado);
				}
				break;
			}
		}
		return valorTotal;
	}

	public static double knapsack01(double[] pesos, double[] valores, int capacidadFinal) {
		Objeto[] objetos = new Objeto[pesos.length];

		for (int i = 0; i < pesos.length; i++) {
			objetos[i] = new Objeto(pesos[i], valores[i]);
		}

		Arrays.sort(objetos);

		double valorTotal = 0;
		double capacidadActual = 0;

		for (Objeto objeto : objetos) {
			if (capacidadActual + objeto.peso <= capacidadFinal) {
				capacidadActual += objeto.peso;
				valorTotal += objeto.valor;
				System.out.println("Objeto con | Peso= " + objeto.peso + "| Valor = " + objeto.valor);
			}
		}
		return valorTotal;
	}

	public static double mochilaMixta(double[] pesos, double[] valores, int capacidad) {
		Objeto[] objetos = new Objeto[pesos.length];

		for (int i = 0; i < pesos.length; i++) {
			objetos[i] = new Objeto(pesos[i], valores[i]);
		}

		Arrays.sort(objetos);

		double valorTotal = 0;
		double capacidadActual = 0;

		for (Objeto objeto : objetos) {
			if (capacidadActual + objeto.peso <= capacidad) {
				capacidadActual += objeto.peso;
				valorTotal += objeto.valor;
				System.out.println("Objeto con | Peso= " + objeto.peso + "| Valor = " + objeto.valor);
			} else {
				double espacioRestante = capacidad - capacidadActual;
				if (espacioRestante >= objeto.peso / 2) {
					capacidadActual += objeto.peso / 2;
					valorTotal += objeto.valor / 2;
					System.out.println("Objeto normal-> " + objeto.toString() + " <> Objeto Fraccionado Resultante "
							+ "con | Peso= " + objeto.peso / 2 + "| Valor = " + objeto.valor / 2);
				}
			}
		}
		return valorTotal;
	}

	public static double knapsackRepeticion(double[] pesos, double[] valores, int capacidadFinal, int[] cantidad) {
		Objeto[] objetos = new Objeto[pesos.length];

		
		for (int i = 0; i < pesos.length; i++) {
			objetos[i] = new Objeto(pesos[i], valores[i], cantidad[i]);
		}
		Arrays.sort(objetos);
		
		double valorTotal = 0;
		double capacidadActual = 0;

		for (Objeto objeto : objetos) {
			Objeto obj = new Objeto(objeto);
			if (capacidadActual + objeto.peso * objeto.cantidad <= capacidadFinal) {
				obj.setCantidad(objeto.cantidad);
				capacidadActual += objeto.peso*objeto.cantidad;
				valorTotal += objeto.valor*objeto.cantidad;
				System.out.println("Objeto con | Peso= " + objeto.peso + "| Valor = " + objeto.valor + "| Cantidad = " + objeto.cantidad);
			} else {
				if ((capacidadFinal - capacidadActual) != 0) {
					obj.setCantidad((capacidadFinal - capacidadActual) / objeto.peso);
					double espacioRestante = capacidadFinal - capacidadActual;
					double valorFraccionado = objeto.valor * (espacioRestante / objeto.peso) * objeto.cantidad;
					valorTotal += valorFraccionado;
					System.out.println(
							"Objeto normal-> " + objeto.toString() + " <> Objeto Fraccionado Resultante con | Peso= "
									+ espacioRestante + "| Valor = " + valorFraccionado + "| Cantidad = " + obj.cantidad);
				}
				break;
			}
		}

		return valorTotal;
	}

	public static double knapsackRepeticionIlimitado(double[] pesos, double[] valores, int capacidadFinal, int[] cantidad) {
		Objeto[] objetos = new Objeto[pesos.length];

		for (int i = 0; i < pesos.length; i++) {
			objetos[i] = new Objeto(pesos[i], valores[i], cantidad[i]);
		}
		Arrays.sort(objetos);

		double valorTotal = 0;
		double capacidadActual = 0;

		for (Objeto objeto : objetos) {
			Objeto obj = new Objeto(objeto);
			while(capacidadActual + objeto.peso <= capacidadFinal) {
				obj.setCantidad(obj.cantidad+1);
				capacidadActual += objeto.peso;
				valorTotal += objeto.valor;
				System.out.println("Objeto con | Peso= " + objeto.peso + "| Valor = " + objeto.valor);
			} 
			if(capacidadActual == capacidadFinal) break;
			obj.setCantidad(1);
				double espacioRestante = capacidadFinal - capacidadActual;
				double valorFraccionado = objeto.valor * (espacioRestante / objeto.peso);
				valorTotal += valorFraccionado;
				System.out.println(
						"Objeto normal-> " + objeto.toString() + " <> Objeto Fraccionado Resultante con | Peso= "
								+ espacioRestante + "| Valor = " + valorFraccionado);
				break;
		}
		return valorTotal;
	}
}
