import jakarta.xml.bind.annotation.*;

// FIELD: JAXB lee los atributos directamente (sin necesitar getters/setters)
@XmlAccessorType(XmlAccessType.FIELD)
// propOrder: fija el orden de los elementos dentro de <videojuego>
@XmlType(propOrder = {"titulo", "plataforma", "genero", "precio", "stock"})
public class Videojuego {

    @XmlAttribute               // sale como <videojuego id="1">
    private int id;

    @XmlElement private String titulo;
    @XmlElement private String plataforma;
    @XmlElement private String genero;
    @XmlElement private double precio;
    @XmlElement private int stock;

    @XmlTransient               // NO aparece en el XML
    private String codigoProveedor;

    // JAXB necesita un constructor vacío para reconstruir objetos desde XML
    public Videojuego() {
    }

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

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getPlataforma() { return plataforma; }
    public String getGenero() { return genero; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCodigoProveedor() { return codigoProveedor; }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s | %.2f € | stock: %d | prov: %s",
                id, titulo, plataforma, genero, precio, stock,
                codigoProveedor == null ? "-" : codigoProveedor);
    }
}
