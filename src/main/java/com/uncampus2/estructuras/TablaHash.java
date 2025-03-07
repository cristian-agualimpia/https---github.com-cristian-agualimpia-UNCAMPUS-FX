package com.uncampus2.estructuras;

public class TablaHash<K, V> {
    // Clase interna estática que almacena la clave, el valor y referencia al siguiente nodo (para manejo de colisiones).
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity = 16;
    // Usamos un array de Object para evitar crear un array genérico.
    private Object[] buckets;
    
    public TablaHash() {
        buckets = new Object[capacity];
    }
    
    // Función hash sencilla.
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> nuevo = new Entry<>(key, value);
        
        // Hacemos cast del elemento en el array a Entry<K,V>.
        Entry<K, V> actual = (Entry<K, V>) buckets[index];
        if(actual == null) {
            buckets[index] = nuevo;
        } else {
            Entry<K, V> current = actual;
            while(true) {
                if(current.key.equals(key)) {
                    // Si la clave ya existe, actualizamos el valor.
                    current.value = value;
                    return;
                }
                if(current.next == null) {
                    current.next = nuevo;
                    return;
                }
                current = current.next;
            }
        }
    }
    
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = (Entry<K, V>) buckets[index];
        while(current != null) {
            if(current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
}
