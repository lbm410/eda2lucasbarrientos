package org.eda2.practica03;

/**
 * Creacion de esta clase auxiliar para poder contener los pares de una forma mas accesible.
 * @author ab005
 *
 * @param <T>
 * @param <U>
 */

public class Pair<T, U> {

    T first;
    U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}