package com.uncampus2.mapeo;

public class Camino {
    private Lugar origen;
    private Lugar destino;
    private double distancia;

    public Camino(Lugar origen, Lugar destino, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }
    
    // Getters y setters
    public Lugar getOrigen() { return origen; }
    public Lugar getDestino() { return destino; }
    public double getDistancia() { return distancia; }
    @Override
    public String toString() {
        return origen + " -> " + destino + " (" + distancia + " m)";
    }
}
