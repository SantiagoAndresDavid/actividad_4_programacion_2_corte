package negocio;


import dominio.Publicacion;
import excepciones.ExcepcionAccesoDatos;
import datos.ArraysAccesoDatos;

import java.util.ArrayList;
import java.util.List;

public class RegistroPublicacion {
    private ArraysAccesoDatos datos;


    public RegistroPublicacion() {
        this.datos = new ArraysAccesoDatos();
    }

    public void agregarPublicacion(Publicacion publicacion) throws ExcepcionAccesoDatos {
        datos.insertarPublicacion(publicacion);
    }

    public Publicacion buscarPublicacion(Publicacion publicacion) throws ExcepcionAccesoDatos {
        return datos.buscarPublicaciones(publicacion);
    }

    public Publicacion eliminarPublicacion(Publicacion publicacion) throws ExcepcionAccesoDatos {
        return datos.eliminarPublicacion(publicacion);
    }

    public ArraysAccesoDatos getDatos() {
        return datos;
    }

    public void setDatos(ArraysAccesoDatos datos) {
        this.datos = datos;
    }
}
