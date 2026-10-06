package es.promehub;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorXML {

    // Java -> XML (marshal)
    public static void exportar(String ruta, List<Videojuego> lista) {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Catalogo.class);
            Marshaller m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // XML indentado
            m.marshal(new Catalogo(lista), new File(ruta));
            System.out.println("XML generado en: " + new File(ruta).getAbsolutePath());
        } catch (JAXBException e) {
            System.out.println("ERROR durante la generación del XML: " + e.getMessage());
        }
    }

    // XML -> Java (unmarshal)
    public static List<Videojuego> importar(String ruta) {
        File fichero = new File(ruta);
        if (!fichero.exists()) {
            System.out.println("ERROR: el fichero XML '" + ruta + "' no existe.");
            return new ArrayList<>();
        }
        try {
            JAXBContext contexto = JAXBContext.newInstance(Catalogo.class);
            Catalogo catalogo = (Catalogo) contexto.createUnmarshaller().unmarshal(fichero);
            System.out.println("Videojuegos importados desde XML: " + catalogo.getVideojuegos().size());
            return catalogo.getVideojuegos();
        } catch (JAXBException e) {
            System.out.println("ERROR durante el procesamiento del XML (¿formato incorrecto?): " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
