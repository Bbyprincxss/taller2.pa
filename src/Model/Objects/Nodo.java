package Model.Objects;

public class Nodo {

    private Nodo anterior;
    private Nodo siguiente;
    private Perfil elemento;

    public Nodo(Perfil elemento){
        this.elemento = elemento;
        this.anterior = null;
        this.siguiente = null;
    }

    public Perfil getElemento() {
        return elemento;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }






}
