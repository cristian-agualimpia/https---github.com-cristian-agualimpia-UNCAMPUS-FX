package com.uncampus2.estructuras;

import java.util.LinkedList;

import com.uncampus2.mapeo.Camino;
import com.uncampus2.mapeo.Lugar;

public class Grafo {
    // Lista de vértices: cada vértice es un Lugar y sus caminos adyacentes.
    private ListaEnlazada<Vertice> vertices = new ListaEnlazada<>();
    
    // Clase interna que representa cada vértice del grafo.
    public class Vertice {
        private Lugar lugar;
        private ListaEnlazada<Camino> caminos;
        
        public Vertice(Lugar lugar) {
            this.lugar = lugar;
            this.caminos = new ListaEnlazada<>();
        }
        
        public Lugar getLugar() {
            return lugar;
        }
        
        public void agregarCamino(Camino camino) {
            caminos.insertar(camino);
        }
        
        public ListaEnlazada<Camino> getCaminos() {
            return caminos;
        }
    }
    
    // Agrega un nuevo vértice al grafo
    public void agregarLugar(Lugar lugar) {
        vertices.insertar(new Vertice(lugar));
    }
    
    // Agrega un camino (arista) entre dos lugares
    public void agregarCamino(Lugar origen, Lugar destino, double distancia) {
        Vertice vOrigen = buscarVertice(origen);
        Vertice vDestino = buscarVertice(destino);
        if(vOrigen != null && vDestino != null) {
            Camino camino = new Camino(origen, destino, distancia);
            vOrigen.agregarCamino(camino);
            // Si se requiere que el grafo sea bidireccional, se podría agregar el camino inverso aquí:
            // Camino caminoInverso = new Camino(destino, origen, distancia);
            // vDestino.agregarCamino(caminoInverso);
        }
    }
    
    // Método auxiliar para buscar el vértice correspondiente a un lugar
    private Vertice buscarVertice(Lugar lugar) {
        for (Vertice v : vertices) {
            if (v.getLugar().getId().equals(lugar.getId())) {
                return v;
            }
        }
        return null;
    }
    
    // Clase auxiliar para Dijkstra
    private class DijkstraNode {
        Vertice vertice;
        double distance;
        DijkstraNode previous;
        boolean visited;
        
        DijkstraNode(Vertice v) {
            this.vertice = v;
            this.distance = Double.MAX_VALUE;
            this.previous = null;
            this.visited = false;
        }
    }
    
    // Implementación del algoritmo de Dijkstra para obtener la ruta más corta.
    // Retorna una ListaEnlazada con los lugares en el camino, en orden de recorrido.
    public ListaEnlazada<Lugar> rutaMasCorta(Lugar origen, Lugar destino) {
        // Crear una tabla hash simple (usamos la ya implementada TablaHash) para mapear el ID del lugar a su DijkstraNode.
        TablaHash<String, DijkstraNode> dNodes = new TablaHash<>();
        
        // Inicializar DijkstraNode para cada vértice del grafo.
        for (Vertice v : vertices) {
            DijkstraNode dn = new DijkstraNode(v);
            dNodes.put(v.getLugar().getId(), dn);
        }
        
        // Establecer la distancia del vértice de origen en 0.
        DijkstraNode startNode = dNodes.get(origen.getId());
        if (startNode == null) return null;
        startNode.distance = 0;
        
        // Bucle principal de Dijkstra.
        while (true) {
            DijkstraNode current = null;
            // Seleccionar el nodo no visitado con la menor distancia.
            for (Vertice v : vertices) {
                DijkstraNode dn = dNodes.get(v.getLugar().getId());
                if (!dn.visited) {
                    if (current == null || dn.distance < current.distance) {
                        current = dn;
                    }
                }
            }
            
            // Si no se encuentra un nodo no visitado, salir del bucle.
            if (current == null) break;
            
            // Marcar el nodo actual como visitado.
            current.visited = true;
            
            // Si se ha alcanzado el destino, salir.
            if (current.vertice.getLugar().getId().equals(destino.getId())) {
                break;
            }
            
            // Relajar las aristas de los vecinos.
            for (Camino camino : current.vertice.getCaminos()) {
                DijkstraNode neighbor = dNodes.get(camino.getDestino().getId());
                if (neighbor != null && !neighbor.visited) {
                    double alt = current.distance + camino.getDistancia();
                    if (alt < neighbor.distance) {
                        neighbor.distance = alt;
                        neighbor.previous = current;
                    }
                }
            }
        }
        
        // Obtener el nodo correspondiente al destino.
        DijkstraNode endNode = dNodes.get(destino.getId());
        if (endNode == null || endNode.distance == Double.MAX_VALUE) {
            // No se encontró un camino válido.
            return null;
        }
        
        // Reconstruir el camino desde el destino hasta el origen.
        LinkedList<Lugar> reversePath = new LinkedList<>();
        DijkstraNode cur = endNode;
        while (cur != null) {
            reversePath.addFirst(cur.vertice.getLugar());
            cur = cur.previous;
        }
        
        // Convertir el LinkedList al formato ListaEnlazada de la aplicación.
        ListaEnlazada<Lugar> path = new ListaEnlazada<>();
        for (Lugar lugar : reversePath) {
            path.insertar(lugar);
        }
        
        return path;
    }
}
