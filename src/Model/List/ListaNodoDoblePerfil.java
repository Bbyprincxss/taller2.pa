package Model.List;

import Model.Objects.Nodo;
import Model.Objects.Perfil;
import Services.BaseAlmacenamiento;

public class ListaNodoDoblePerfil implements BaseAlmacenamiento {

    /**
     * Atributos para la lista nodo doble
     */
    private Nodo cola;
    private Nodo cabeza;

    /**
     * Constructor
     */
    public ListaNodoDoblePerfil() {
        this.cola = null;
        this.cabeza = null;
    }

    /**
     * Metogo getCola
     * @return cola
     */
    public Nodo getCola() {
        return cola;
    }

    /**
     * Metodo setCola
     * @param cola de la lista
     */
    public void setCola(Nodo cola) {
        this.cola = cola;
    }

    /**
     * Metodo getCabeza tipo Nodo
     * @return cabeza
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Metodo setCabeza
     * @param cabeza de la lista
     */
    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }


    /**
     * Metodo para agregar un nuevo Perfil
     * @param nuevoPerfil a agregar
     * @return "funciono" si este fue agregado correctamente
     */
    @Override
    public boolean agregar(Perfil nuevoPerfil) {

        boolean funciono = false;

        Nodo nodoNuevo = new Nodo(nuevoPerfil);
        //agregar a la cabeza
        if (this.cabeza == null) {
            this.cabeza = nodoNuevo;
            this.cola = nodoNuevo;
            funciono = true;
            return funciono;
        }
        //agregar al final
        for (Nodo aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {
            if (aux.getSiguiente() == null) {
                aux.setSiguiente(nodoNuevo);
                nodoNuevo.setAnterior(aux);
                nodoNuevo.setSiguiente(null);
                funciono = true;
            }
        }
        return funciono;
    }

    /**
     * Metodo que verifica si un Prfil contiene un elemento
     * @param elemento tipo Perfil
     * @return true si lo contiene, false si no lo contiene
     */
    @Override
    public boolean contiene(Perfil elemento) {
        for (Nodo aux = this.cabeza;aux != null;aux = aux.getSiguiente()){
            if (aux.getElemento().esIgual(elemento)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para vaciar
     */
    @Override
    public void vaciar() {
        this.cabeza = null;
    }

    /**
     * Metodo para verificar si la lista esta vacia
     * @return la cabeza se vuelve null
     */
    @Override
    public boolean estaVacia() {
        return this.cabeza == null;
    }

    /**
     * Metodo para eliminar un Perfil
     * @param eliminar tipo Perfil
     * @return true si esta correctamente eliminada, false si no
     */
    @Override
    public boolean eliminar(Perfil eliminar) {

        //si el perfil es nulo
        if(eliminar != null) {

            //si la lista esta vacia
            if (this.estaVacia()) {
                return false;
            }

            //si el perfil es la cabeza
            if (this.cabeza.getElemento().esIgual(eliminar)) {
                this.cabeza = this.cabeza.getSiguiente();
                this.cabeza.setSiguiente(null);
                return true;
            }

            //buscar el perfil en la lista de nodos
            for (Nodo aux = this.cabeza; aux != null; aux = aux.getSiguiente()) {

                if (aux.getSiguiente().getSiguiente() != null && aux.getSiguiente().getElemento().esIgual(eliminar)) {

                    aux.setSiguiente(aux.getSiguiente().getSiguiente());

                    if (aux.getSiguiente() == null) {
                        this.cola = aux;
                    }
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Metodo que recorre la lista para ver el tamanio de esta
     * @return contador
     */
    @Override
    public int tamanio() {
        int contador = 0;
        for (Nodo aux = this.cabeza; aux != null ; aux = aux.getSiguiente()){
            contador++;
        }
        return contador;
    }

    /**
     * Metodo que busca un perfil por un nombre
     * @param nombre tipo String
     * @return false si la lista esta vacia o si no se encontro, true si este fue encontrado
     */
    @Override
    public boolean buscar(String nombre, String contrasenia) {
        //verificar
        if(estaVacia()){
            return false;
        }

        for (Nodo aux = this.cabeza; aux !=null; aux = aux.getSiguiente()){

            if(aux.getElemento().getNombreUsuario().equalsIgnoreCase(nombre)){
                if (aux.getElemento().getContrasenia().equalsIgnoreCase(contrasenia)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Metodo para obtener un nombre
     * @param nombre tipo String
     * @return null si no se obtiene, el elemento si se obtuvo
     */
    @Override
    public Perfil obtener(String nombre) {

        for (Nodo aux = this.cabeza; aux !=null; aux = aux.getSiguiente()){

            if(aux.getElemento().getNombreUsuario().equalsIgnoreCase(nombre)){
                return aux.getElemento();
            }

        }

        return null;
    }

    /**
     * Metodo para buscar un nombre
     * @param nombre tipo String
     * @return true si fue encontrado, false si no
     */
    @Override
    public boolean buscarNombre(String nombre) {
        if(estaVacia()){
            return false;
        }
        for (Nodo aux = this.cabeza; aux !=null; aux = aux.getSiguiente()){
            if(aux.getElemento().getNombreUsuario().equalsIgnoreCase(nombre)){
                return true;
            }
        }
        return false;
    }

    /**
     * Obtener un perfil
     * @param correoElectronico tipo String
     * @return el correo o null si no se obtuvo
     */
    public Perfil obtenerPerfilA (String correoElectronico){

        for (Nodo aux = this.cabeza; aux !=null; aux = aux.getSiguiente()){

            if(aux.getElemento().getCorreoElectronico().equalsIgnoreCase(correoElectronico)){
                return aux.getElemento();
            }

        }
        return null;
    }
}
