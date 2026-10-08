package es.promehub;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

// Clase contenedora: JAXB necesita una raiz que contenga la lista
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

    // Getter de la lista de videojuegos
    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }
}
