package com.uncampus2.estructuras;

public class ListaEnlazada<T> implements Iterable<T> {
    private Nodo<T> cabeza;
    
    // Clase interna para los nodos
    private class Nodo<E> {
        E dato;
        Nodo<E> siguiente;
        public Nodo(E dato) {
            this.dato = dato;
        }
    }


    public void insertar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while(actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }
    
    // Implementación de Iterable<T>
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Nodo<T> actual = cabeza;
            
            @Override
            public boolean hasNext() {
                return actual != null;
            }
            
            @Override
            public T next() {
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }

    // Aquí el método para retornar todos los valores:
    public java.util.List<T> obtenerElementos() {
        java.util.List<T> resultado = new java.util.ArrayList<>();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            resultado.add(actual.dato);
            actual = actual.siguiente;
        }
        return resultado;
    }
}
