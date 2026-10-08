package es.promehub;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String CSV_ENTRADA;
    static final String XML = "catalogo.xml";
    static final String CSV_SALIDA = "catalogo_exportado.csv";

    static List<Videojuego> catalogo = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedimos al usuario que introduzca la ruta de su csv
        System.out.print("Introduce la ruta del archivo videojuegos.csv: ");
        CSV_ENTRADA = sc.nextLine();
        int opcion = -1;

        do {
            // Mostramos el menu
            mostrarMenu();
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                // Validamos que el usuario introduzca un valor correcto
                System.out.println("Opcion incorrecta: introduce un numero del 0 al 7.");
                continue;
            }

            switch (opcion) {
                case 1 -> catalogo = GestorCSV.leer(CSV_ENTRADA); // Cargamos el catalogo desde el csv
                case 2 -> mostrarCatalogo(); // Mostramos el catalogo
                case 3 -> GestorXML.exportar(XML, catalogo); // Exportar a xml
                case 4 -> catalogo = GestorXML.importar(XML); // Cargamos el catalogo desde xml
                case 5 -> GestorCSV.escribir(CSV_SALIDA, catalogo); // Exportamos  el catalogo a csv
                case 6 -> buscar(sc); // Metodo de busqueda de un videojuego
                case 7 -> infoFicheros(); // Mostramos informacion de los ficheros 
                case 0 -> System.out.println("Hasta pronto."); // Mostramos mensaje de despedida
                default -> System.out.println("Opcion incorrecta: elige entre 0 y 7."); //Mostramos un mensaje si introduce una opcion no valida
            }
        } while (opcion != 0);
    }
    // Metodo para mostrar el menu
    static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println(" PROMEHUB DATA EXCHANGE");
        System.out.println("========================================");
        System.out.println("1. Cargar catalogo desde CSV");
        System.out.println("2. Mostrar catalogo");
        System.out.println("3. Exportar catalogo a XML");
        System.out.println("4. Cargar catalogo desde XML");
        System.out.println("5. Exportar catalogo a CSV");
        System.out.println("6. Buscar videojuego");
        System.out.println("7. Informacion de ficheros");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }
    // Metodo para mostrar el catalogo
    static void mostrarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("El catalogo esta vacio. Carga primero un CSV o un XML.");
            return;
        }
        catalogo.forEach(System.out::println);
    }
    // Metodo para buscar un videojuego
    static void buscar(Scanner sc) {
        System.out.print("Introduce id o titulo (o parte del titulo): ");
        String texto = sc.nextLine().trim();
        boolean encontrado = false;
    
        for (Videojuego v : catalogo) {
            // Coincide si el id es igual o si el titulo contiene el texto (sin mayusculas)
            if (String.valueOf(v.getId()).equals(texto)
                    || v.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                System.out.println(v);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No se ha encontrado ningún videojuego.");
    }
    // Metodo para mostrar informacion de los ficheros
    static void infoFicheros() {
        for (String ruta : new String[]{CSV_ENTRADA, XML, CSV_SALIDA}) {
            File f = new File(ruta);
            System.out.println("- " + ruta);
            System.out.println("    Existe: " + (f.exists() ? "si" : "no"));
            System.out.println("    Tamaño: " + (f.exists() ? f.length() + " bytes" : "-"));
            System.out.println("    Ruta:   " + f.getAbsolutePath());
        }
    }
    }
