package dominio;

public abstract class Publicacion {
    private String isbn;
    private String titulo;
    private int año;
    private String autor;
    private double costo;

    public Publicacion() {
    }

    public Publicacion(String isbn) {
        this.isbn = isbn;
    }

    public Publicacion(String isbn, String titulo, int año, String autor, double costo) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.año = año;
        this.autor = autor;
        this.costo = costo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public abstract String getInfo();

    /**
     * @return: Un string con los datos de la publicacion.
     */
    @Override
    public String toString() {
        return "ISBN: " + isbn + "\n"
                + "Titulo: " + titulo + "\n"
                + "Año: " + año + "\n"
                + "Autor: " + autor + "\n"
                + "Costo: " + costo + "\n"
                + getInfo();
    }
}
