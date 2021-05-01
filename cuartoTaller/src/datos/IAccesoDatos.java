package datos;

import dominio.Publicacion;
import excepciones.ExcepcionAccesoDatos;

import java.util.List;


public interface IAccesoDatos {
    void insertarPublicacion(Publicacion publicacion) throws ExcepcionAccesoDatos;

    Publicacion buscarPublicaciones(Publicacion publicacion) throws ExcepcionAccesoDatos;

    Publicacion eliminarPublicacion(Publicacion publicacion) throws ExcepcionAccesoDatos;
}
