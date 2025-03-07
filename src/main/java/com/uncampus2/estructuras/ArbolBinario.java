package com.uncampus2.estructuras;

public class ArbolBinario<T extends Comparable<T>> {
    private NodoArbol<T> raiz;
    
    private class NodoArbol<E> {
        E dato;
        NodoArbol<E> izquierda, derecha;
        public NodoArbol(E dato) {
            this.dato = dato;
        }
    }
    
    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }
    
    private NodoArbol<T> insertarRecursivo(NodoArbol<T> nodo, T dato) {
        if(nodo == null) {
            return new NodoArbol<>(dato);
        }
        if(dato.compareTo(nodo.dato) < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, dato);
        } else {
            nodo.derecha = insertarRecursivo(nodo.derecha, dato);
        }
        return nodo;
    }
    
    // Otros métodos: búsqueda, recorrido, etc.
}