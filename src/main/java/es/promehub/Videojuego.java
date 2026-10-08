package es.promehub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

// Clase que representa un videojuego y sus datos
@XmlAccessorType(XmlAccessType.FIELD)

// Fijamos el orden de los elementos dentro de <videojuego>
@XmlType(propOrder = {"titulo", "plataforma", "genero", "precio", "stock"})
public class Videojuego {

    // El id aparece como atributo dentro de <videojuego>
    @XmlAttribute        // sale como <videojuego id="1">
    private int id;

    // Datos del videojuego que apareceran en el XML
    @XmlElement private String titulo;
    @XmlElement private String plataforma;
    @XmlElement private String genero;
    @XmlElement private double precio;
    @XmlElement private int stock;

    // Este dato no se incluye en el XML
    @XmlTransient               // NO aparece en el XML
    private String codigoProveedor;

    // Constructor vacio necesario para que JAXB pueda crear objetos desde el XML
    public Videojuego() {
    }

    // Constructor para crear un videojuego con todos sus datos
    public Videojuego(int id, String titulo, String plataforma, String genero,
        double precio, int stock, String codigoProveedor) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.precio = precio;
        this.stock = stock;
        this.codigoProveedor = codigoProveedor;
    }

    // Getters para obtener los datos del videojuego
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getPlataforma() { return plataforma; }
    public String getGenero() { return genero; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCodigoProveedor() { return codigoProveedor; }

    // Metodo para mostrar los datos del videojuego en formato de texto
    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s | %.2f € | stock: %d | prov: %s",
                id, titulo, plataforma, genero, precio, stock,
                codigoProveedor == null ? "-" : codigoProveedor);
    }
}
