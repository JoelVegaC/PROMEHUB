package es.promehub;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// Clase contenedora: JAXB necesita una raíz que contenga la lista
@XmlRootElement(name = "catalogo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalogo {

    // name = "videojuego": cada elemento de la lista se escribe como <videojuego>
    // sin wrapper extra alrededor de la lista
    @XmlElement(name = "videojuego")
    private List<Videojuego> videojuegos = new ArrayList<>();

    public Catalogo() {
    }

    public Catalogo(List<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }
}
