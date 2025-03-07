package com.uncampus2.mapeo;

public class Lugar implements Comparable<Lugar> {
    private String id;
    private String nombre;
    // Otros atributos

    public Lugar(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    @Override
    public int compareTo(Lugar otro) {
        return this.id.compareTo(otro.id);
    }
    
    // Getters, setters y otros métodos
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    
    @Override
    public String toString() {
        return nombre;
    }
}
