package Services;

import Model.Objects.Nodo;
import Model.Objects.Perfil;

public interface BaseAlmacenamiento {

     boolean agregar(Perfil nuevoPerfil);
     boolean contiene(Perfil elemento);
     void vaciar();
     boolean estaVacia();
     boolean eliminar(Perfil perfil);
     int tamanio();

     boolean buscar(String nombre, String contrasenia);

     Perfil obtener(String nombre);

     boolean buscarNombre(String nombre);
}
