package datos;


import dominio.Publicacion;
import excepciones.ExcepcionAccesoDatos;

import java.util.ArrayList;
import java.util.List;

public class ArraysAccesoDatos implements IAccesoDatos {
    private List<Publicacion> listaPublicaciones;


    public ArraysAccesoDatos() {
        this.listaPublicaciones = new ArrayList<>();
    }

    @Override
    public void insertarPublicacion(Publicacion publicacion) throws ExcepcionAccesoDatos {
        if (publicacion == null) {
            throw new ExcepcionAccesoDatos("Publicacion invalida");
        }

        listaPublicaciones.add(publicacion);
    }


    @Override
    public Publicacion buscarPublicaciones(Publicacion publicacionAEncontrar) throws ExcepcionAccesoDatos {
        if (listaPublicaciones.size() == 0) {
            throw new ExcepcionAccesoDatos("Lista vacia");
        }
        if (publicacionAEncontrar == null) {
            throw new ExcepcionAccesoDatos("\nPublicacion no registrada");
        }
        if (publicacionAEncontrar.getIsbn() == null) {
            throw new ExcepcionAccesoDatos("El ISBN es invalido");
        }

        for (Publicacion publicacion : listaPublicaciones) {
            if (publicacionAEncontrar.getIsbn().equals(publicacion.getIsbn())) {
                Publicacion encontrado = publicacion;
                return encontrado;
            }
        }
        return null;
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion publicacion) throws ExcepcionAccesoDatos {
        Publicacion publicacionABorrar = buscarPublicaciones(publicacion);
        listaPublicaciones.remove(publicacionABorrar);
        return publicacionABorrar;
    }

    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<Publicacion> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }
}