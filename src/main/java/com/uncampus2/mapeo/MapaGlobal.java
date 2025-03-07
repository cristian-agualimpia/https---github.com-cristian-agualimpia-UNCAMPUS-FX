package com.uncampus2.mapeo;

import java.util.ArrayList;
import java.util.List;

import com.uncampus2.estructuras.ArbolBinario;
import com.uncampus2.estructuras.Grafo;
import com.uncampus2.estructuras.ListaEnlazada;
import com.uncampus2.estructuras.TablaHash;

public class MapaGlobal {
    private Grafo grafo;
    private ArbolBinario<Lugar> arbolLugares;
    private TablaHash<String, Lugar> tablaLugares;
    private ListaEnlazada<Lugar> listaLugares;
    
    public MapaGlobal() {
        grafo = new Grafo();
        arbolLugares = new ArbolBinario<>();
        tablaLugares = new TablaHash<>();
        listaLugares = new ListaEnlazada<>();
        cargarMapa();
    }

    // Retornar lugares existentes
    public List<Lugar> getAllLugares() {
        // Return all available locations
        return new ArrayList<>(listaLugares.obtenerElementos());
    }

    public Lugar getLugarById(String id) {
        return tablaLugares.get(id);
    }
    
    
    private void cargarMapa() {
        // Crear instancias de Lugar según el mapa del campus
        Lugar uriel             = new Lugar("101", "Edificio Uriel Gutiérrez");
        Lugar museoArte         = new Lugar("102", "Museo de Arte");
        Lugar auditorio         = new Lugar("103", "Auditorio León de Greiff");
        Lugar museoArquitectura = new Lugar("105", "Museo de Arquitectura");
        Lugar aulasArtes        = new Lugar("106", "Edificio de Aulas de Artes");
        Lugar observatorio      = new Lugar("111", "Observatorio Astronómico");
        Lugar planetario        = new Lugar("112", "Planetario");
        Lugar enfermeria        = new Lugar("201", "Facultad de Enfermería");
        Lugar hospital          = new Lugar("202", "Hospital Universitario");
        Lugar odontologia       = new Lugar("210", "Facultad de Odontología");
        Lugar capilla           = new Lugar("215", "Capilla");
        Lugar cienciasHumanas   = new Lugar("301", "Facultad de Ciencias Humanas");
        Lugar derecho           = new Lugar("303", "Facultad de Derecho, Ciencias Políticas y Sociales");
        Lugar cienciasEconomicas= new Lugar("311", "Facultad de Ciencias Económicas");
        Lugar ingenieria        = new Lugar("401", "Facultad de Ingeniería");
        Lugar labIngenieria     = new Lugar("402", "Laboratorios de Ingeniería");
        Lugar aulasIngenieria   = new Lugar("403", "Edificio de Aulas de Ingeniería");
        Lugar centroComputo     = new Lugar("404", "Centro de Cómputo");
        Lugar ciencias          = new Lugar("501", "Facultad de Ciencias");
        Lugar institutoNaturales= new Lugar("502", "Instituto de Ciencias Naturales");
        Lugar labQuimica        = new Lugar("503", "Laboratorios de Química");
        Lugar labFisica         = new Lugar("504", "Laboratorios de Física");
        Lugar medVet            = new Lugar("601", "Facultad de Medicina Veterinaria y Zootecnia");
        Lugar agronomia         = new Lugar("602", "Facultad de Agronomía");
        Lugar institutoGenetica = new Lugar("603", "Instituto de Genética");
        Lugar estadio           = new Lugar("701", "Estadio");
        Lugar coliseo           = new Lugar("702", "Coliseo");
        Lugar gimnasio          = new Lugar("703", "Gimnasio");
        Lugar piscina           = new Lugar("704", "Piscina");
        Lugar biblioteca        = new Lugar("561", "Biblioteca Central");
        Lugar comedor           = new Lugar("CC", "Comedor Central");
        Lugar porteriaCalle26   = new Lugar("P26", "Portería Calle 26 (Entrada Norte)");
        Lugar porteriaCarrera30 = new Lugar("P30", "Portería Carrera 30 (Entrada Oriental)");
        Lugar facArqArtes       = new Lugar("107", "Facultad de Arquitectura / Artes");
        
        // Agregar los lugares a las estructuras (lista, árbol, tabla hash y grafo)
        Lugar[] lugares = {uriel, museoArte, auditorio, museoArquitectura, aulasArtes, observatorio, planetario,
                           enfermeria, hospital, odontologia, capilla, cienciasHumanas, derecho, cienciasEconomicas,
                           ingenieria, labIngenieria, aulasIngenieria, centroComputo, ciencias, institutoNaturales,
                           labQuimica, labFisica, medVet, agronomia, institutoGenetica, estadio, coliseo, gimnasio,
                           piscina, biblioteca, comedor, porteriaCalle26, porteriaCarrera30, facArqArtes};
                           
        for (Lugar lugar : lugares) {
            listaLugares.insertar(lugar);
            arbolLugares.insertar(lugar);
            tablaLugares.put(lugar.getId(), lugar);
            grafo.agregarLugar(lugar);
        }
        
        // Definir los caminos (distancias en metros)
        // 1. Edificio Uriel Gutiérrez (101)
        grafo.agregarCamino(uriel, auditorio, 80);
        grafo.agregarCamino(uriel, museoArte, 100);
        
        // 2. Auditorio León de Greiff (103)
        grafo.agregarCamino(auditorio, uriel, 80);
        grafo.agregarCamino(auditorio, museoArte, 50);
        grafo.agregarCamino(auditorio, museoArquitectura, 120);
        
        // 3. Museo de Arte (102)
        grafo.agregarCamino(museoArte, auditorio, 50);
        grafo.agregarCamino(museoArte, uriel, 100);
        grafo.agregarCamino(museoArte, observatorio, 150);
        
        // 4. Museo de Arquitectura (105)
        grafo.agregarCamino(museoArquitectura, auditorio, 120);
        grafo.agregarCamino(museoArquitectura, aulasArtes, 50);
        grafo.agregarCamino(museoArquitectura, observatorio, 120);
        
        // 5. Observatorio Astronómico (111)
        grafo.agregarCamino(observatorio, museoArte, 150);
        grafo.agregarCamino(observatorio, planetario, 40);
        grafo.agregarCamino(observatorio, museoArquitectura, 120);
        
        // 6. Planetario (112)
        grafo.agregarCamino(planetario, observatorio, 40);
        grafo.agregarCamino(planetario, facArqArtes, 60);
        
        // 7. Facultad de Enfermería (201)
        grafo.agregarCamino(enfermeria, hospital, 50);
        grafo.agregarCamino(enfermeria, odontologia, 200);
        
        // 8. Hospital Universitario (202)
        grafo.agregarCamino(hospital, enfermeria, 50);
        grafo.agregarCamino(hospital, odontologia, 250);
        
        // 9. Facultad de Odontología (210)
        grafo.agregarCamino(odontologia, hospital, 250);
        grafo.agregarCamino(odontologia, capilla, 100);
        
        // 10. Capilla (215)
        grafo.agregarCamino(capilla, odontologia, 100);
        grafo.agregarCamino(capilla, comedor, 200);
        grafo.agregarCamino(capilla, derecho, 300);
        
        // 11. Facultad de Ciencias Humanas (301)
        grafo.agregarCamino(cienciasHumanas, derecho, 150);
        grafo.agregarCamino(cienciasHumanas, cienciasEconomicas, 120);
        
        // 12. Facultad de Derecho, Ciencias Políticas y Sociales (303)
        grafo.agregarCamino(derecho, cienciasHumanas, 150);
        grafo.agregarCamino(derecho, capilla, 300);
        grafo.agregarCamino(derecho, biblioteca, 250);
        
        // 13. Facultad de Ciencias Económicas (311)
        grafo.agregarCamino(cienciasEconomicas, cienciasHumanas, 120);
        grafo.agregarCamino(cienciasEconomicas, uriel, 250);
        
        // 14. Facultad de Ingeniería (401)
        grafo.agregarCamino(ingenieria, labIngenieria, 60);
        grafo.agregarCamino(ingenieria, centroComputo, 120);
        grafo.agregarCamino(ingenieria, aulasIngenieria, 80);
        
        // 15. Laboratorios de Ingeniería (402)
        grafo.agregarCamino(labIngenieria, ingenieria, 60);
        grafo.agregarCamino(labIngenieria, aulasIngenieria, 70);
        
        // 16. Edificio de Aulas de Ingeniería (403)
        grafo.agregarCamino(aulasIngenieria, ingenieria, 80);
        grafo.agregarCamino(aulasIngenieria, labIngenieria, 70);
        grafo.agregarCamino(aulasIngenieria, centroComputo, 70);
        
        // 17. Centro de Cómputo (404)
        grafo.agregarCamino(centroComputo, aulasIngenieria, 70);
        grafo.agregarCamino(centroComputo, ingenieria, 120);
        
        // 18. Facultad de Ciencias (501)
        grafo.agregarCamino(ciencias, institutoNaturales, 80);
        grafo.agregarCamino(ciencias, labQuimica, 50);
        grafo.agregarCamino(ciencias, biblioteca, 250);
        
        // 19. Instituto de Ciencias Naturales (502)
        grafo.agregarCamino(institutoNaturales, ciencias, 80);
        grafo.agregarCamino(institutoNaturales, labQuimica, 100);
        
        // 20. Laboratorios de Química (503)
        grafo.agregarCamino(labQuimica, ciencias, 50);
        grafo.agregarCamino(labQuimica, institutoNaturales, 100);
        grafo.agregarCamino(labQuimica, labFisica, 60);
        
        // 21. Laboratorios de Física (504)
        grafo.agregarCamino(labFisica, labQuimica, 60);
        grafo.agregarCamino(labFisica, biblioteca, 200);
        
        // 22. Facultad de Medicina Veterinaria y Zootecnia (601)
        grafo.agregarCamino(medVet, agronomia, 80);
        grafo.agregarCamino(medVet, institutoGenetica, 100);
        
        // 23. Facultad de Agronomía (602)
        grafo.agregarCamino(agronomia, medVet, 80);
        grafo.agregarCamino(agronomia, institutoGenetica, 120);
        
        // 24. Instituto de Genética (603)
        grafo.agregarCamino(institutoGenetica, medVet, 100);
        grafo.agregarCamino(institutoGenetica, agronomia, 120);
        
        // 25. Estadio (701)
        grafo.agregarCamino(estadio, coliseo, 100);
        grafo.agregarCamino(estadio, gimnasio, 180);
        
        // 26. Coliseo (702)
        grafo.agregarCamino(coliseo, estadio, 100);
        grafo.agregarCamino(coliseo, gimnasio, 80);
        
        // 27. Gimnasio (703)
        grafo.agregarCamino(gimnasio, coliseo, 80);
        grafo.agregarCamino(gimnasio, piscina, 50);
        
        // 28. Piscina (704)
        grafo.agregarCamino(piscina, gimnasio, 50);
        grafo.agregarCamino(piscina, estadio, 200);
        
        // 29. Biblioteca Central (561)
        grafo.agregarCamino(biblioteca, labFisica, 200);
        grafo.agregarCamino(biblioteca, derecho, 250);
        grafo.agregarCamino(biblioteca, comedor, 150);
        
        // 30. Comedor Central (CC)
        grafo.agregarCamino(comedor, capilla, 200);
        grafo.agregarCamino(comedor, biblioteca, 150);
        grafo.agregarCamino(comedor, aulasIngenieria, 300);
        
        // 31. Portería Calle 26 (Entrada Norte)
        grafo.agregarCamino(porteriaCalle26, uriel, 300);
        grafo.agregarCamino(porteriaCalle26, hospital, 200);
        
        // 32. Portería Carrera 30 (Entrada Oriental)
        grafo.agregarCamino(porteriaCarrera30, observatorio, 250);
        grafo.agregarCamino(porteriaCarrera30, facArqArtes, 200);
    }
    
    // Método para obtener la ruta más corta entre dos lugares a partir de sus IDs
    public ListaEnlazada<Lugar> obtenerRutaMasCorta(String idOrigen, String idDestino) {
        Lugar origen = tablaLugares.get(idOrigen);
        Lugar destino = tablaLugares.get(idDestino);
        if (origen != null && destino != null) {
            return grafo.rutaMasCorta(origen, destino);
        }
        return null;
    }
}
