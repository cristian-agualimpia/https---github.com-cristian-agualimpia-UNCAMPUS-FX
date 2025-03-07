package com.uncampus2;

import java.util.Scanner;

import com.uncampus2.estructuras.ListaEnlazada;
import com.uncampus2.mapeo.Lugar;
import com.uncampus2.mapeo.MapaGlobal;

public class InterfazConsola {
    public static void main(String[] args) {
        MapaGlobal mapa = new MapaGlobal();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido al sistema de rutas del campus.");
        
        while (true) {
            System.out.println("\nIngrese 'exit' en cualquier momento para terminar.");
            
            // Validación del lugar de origen
            Lugar origen = null;
            while (origen == null) {
                System.out.print("Ingrese el ID del lugar de origen: ");
                String idOrigen = scanner.nextLine().trim();
                if (idOrigen.equalsIgnoreCase("exit")) {
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    return;
                }
                origen = mapa.getLugarById(idOrigen);
                if (origen == null) {
                    System.out.println("El ID ingresado no corresponde a ningún lugar. Intente de nuevo.");
                } else {
                    System.out.println("Has seleccionado '" + origen.getNombre() + "' como lugar de origen.");
                    System.out.print("¿Es correcto? (S/N): ");
                    String confirm = scanner.nextLine().trim();
                    if (!confirm.equalsIgnoreCase("S")) {
                        origen = null; // Reinicia la selección
                    }
                }
            }
            
            // Validación del lugar de destino
            Lugar destino = null;
            while (destino == null) {
                System.out.print("Ingrese el ID del lugar de destino: ");
                String idDestino = scanner.nextLine().trim();
                if (idDestino.equalsIgnoreCase("exit")) {
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    return;
                }
                destino = mapa.getLugarById(idDestino);
                if (destino == null) {
                    System.out.println("El ID ingresado no corresponde a ningún lugar. Intente de nuevo.");
                } else {
                    System.out.println("Has seleccionado '" + destino.getNombre() + "' como lugar de destino.");
                    System.out.print("¿Es correcto? (S/N): ");
                    String confirm = scanner.nextLine().trim();
                    if (!confirm.equalsIgnoreCase("S")) {
                        destino = null; // Reinicia la selección
                    }
                }
            }
            
            // Obtener y mostrar la ruta
            ListaEnlazada<Lugar> ruta = mapa.obtenerRutaMasCorta(origen.getId(), destino.getId());
            if (ruta != null) {
                System.out.println("La ruta más corta es:");
                for (Lugar paso : ruta) {
                    System.out.println(" -> " + paso.getNombre());
                }
            } else {
                System.out.println("No se encontró una ruta válida entre los lugares indicados.");
            }
            
            // Preguntar si se desea otro recorrido
            System.out.print("¿Desea buscar otra ruta? (S/N): ");
            String continuar = scanner.nextLine().trim();
            if (!continuar.equalsIgnoreCase("S")) {
                break;
            }
        }
        
        scanner.close();
        System.out.println("Gracias por usar el sistema de rutas del campus.");
    }
}
