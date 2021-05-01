package dominio;

public class Libro extends Publicacion {
    private int paginas;
    private int edicion;

    public Libro() {
    }

    public Libro(String isbn) {
        super(isbn);
    }

    public Libro(String isbn, String titulo, int año, String autor, double costo, int nPaginas, int edicion) {
        super(isbn, titulo, año, autor, costo);
        this.paginas = nPaginas;
        this.edicion = edicion;
    }



    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    @Override
    public String getInfo() {
        return "Numero de paginas: " + paginas + "\n"
                + "Edicion: " + edicion + "\n";
    }
}
