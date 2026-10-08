package es.promehub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV {

    private static final String SEPARADOR = ";";

    // Lectura secuencial: BufferedReader + readLine() hasta null
    public static List<Videojuego> leer(String ruta) {
        List<Videojuego> lista = new ArrayList<>();
        File fichero = new File(ruta);

        if (!fichero.exists()) {
            System.out.println("ERROR: el fichero '" +ruta+ "' no existe.");
            return lista;
        }

        int procesados = 0, erroneos = 0, numLinea = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            br.readLine(); // ignoramos la cabecera
            String linea;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                if (linea.isBlank()) continue;

                // -1 en split para no perder campos vacios al final
                String[] c = linea.split(SEPARADOR, -1);
                if (c.length != 7) {
                    System.out.println("Linea " + numLinea + " incorrecta: se esperaban 7 campos y hay " + c.length);
                    erroneos++;
                    continue;
                }
                try {
                    lista.add(new Videojuego(
                            Integer.parseInt(c[0].trim()),
                            c[1].trim(), c[2].trim(), c[3].trim(),
                            Double.parseDouble(c[4].trim()),
                            Integer.parseInt(c[5].trim()),
                            c[6].trim()));
                    procesados++;
                } catch (NumberFormatException e) {
                    System.out.println("Linea " + numLinea + " ignorada: error de conversion numerica (id, precio o stock).");
                    erroneos++;
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR de lectura del CSV: " + e.getMessage());
        }

        System.out.println("Registros validos: " + procesados + " | Registros erróneos: " + erroneos);
        return lista;
    }

    public static void escribir(String ruta, List<Videojuego> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("id;titulo;plataforma;genero;precio;stock;codigoProveedor");
            for (Videojuego v : lista) {
                // Locale.US para que el decimal sea punto y no coma
                pw.println(String.format(java.util.Locale.US, "%d;%s;%s;%s;%.2f;%d;%s",
                        v.getId(), v.getTitulo(), v.getPlataforma(), v.getGenero(),
                        v.getPrecio(), v.getStock(),
                        v.getCodigoProveedor() == null ? "" : v.getCodigoProveedor()));
            }
            System.out.println("CSV generado en: " + new File(ruta).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("ERROR al escribir el CSV: " + e.getMessage());
        }
    }
}
