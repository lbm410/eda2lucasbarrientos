package org.eda2.practica03;

/**
 * Clase Arista.
 */
public class Arista {

	/** El origen. */
	int origen;

	/** El destino. */
	int destino;

	/** El peso (distancia). */
	int peso;

	/**
	 * Instancia una nueva arista. Constructor copia.
	 *
	 * @param origen  the origen
	 * @param destino the destino
	 * @param peso    the peso
	 */
	public Arista(int origen, int destino, int peso) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}

	/**
	 * Metodo toString de la clase Arista.
	 *
	 * @return the string
	 */
	public String toString() {
		return origen + " -> " + destino + " (" + this.peso + ") ";
	}

	/**
	 * Metodo equals de la clase Arista.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		if (destino != other.destino)
			return false;
		if (origen != other.origen)
			return false;
		return true;
	}

}
