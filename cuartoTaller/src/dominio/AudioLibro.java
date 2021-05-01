package dominio;

public class AudioLibro extends Publicacion {
    private double duracion;
    private String formato;
    private double peso;

    public AudioLibro() {
    }

    public AudioLibro(String isbn) {
        super(isbn);
    }

    public AudioLibro(String isbn, String titulo, int año, String autor, double costo, double duracion, String formato, double peso) {
        super(isbn, titulo, año, autor, costo);
        this.duracion = duracion;
        this.formato = formato;
        this.peso = peso;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String getInfo() {
        return "Duracion: " + duracion + "\n"
                + "Formato: " + formato + "\n"
                + "Peso: " + peso + "\n";
    }

}
