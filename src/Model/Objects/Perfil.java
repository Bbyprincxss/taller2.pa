package Model.Objects;
import ucn.StdOut;

public class Perfil {

    /**
     * nombre del usuario
     */
    private String nombreUsuario;
    /**
     * contrasenia del usuario
     */
    private String contrasenia;
    /**
     * tipo de perfil
     */
    private String tipo;

    /**
     * correo electronico del usuario
     */
    private String correoElectronico;

    /**
     * lista de mensajes de un perfil
     */
    private Mensajeria[] listaMensajeria; //lista de mensajes

    /**
     * cantidad maxima de conexiones y mensajes
     */
    private int cantMaxima; //cantidad maxima para conexiones y mensajes

    /**
     * cantidad actual de mensajes
     */
    private int cantActualMensajes;
    /**
     * cantidad actual de perfil
     */
    private int cantActualPerfiles;
    /**
     * lista de conexiones de la persona
     */
    private Perfil[] listaPerfiles; //la lista de conexiones de la persona
    /**
     * lista de datos de una solicitud
     */
    private String[] solicitudes; // los datos de una solicitud en 3 espacios(1.-nueva solicitud ; 2.- si acepto o rechazo ; 3.- nombre de la persona)

    /**
     * Constructor
     *
     * @param nombreUsuario     de quien ingresa
     * @param contrasenia       de quien ingresa
     * @param tipo              de perfil
     * @param correoElectronico de quien ingresa
     */
    public Perfil(String nombreUsuario, String contrasenia, String tipo, String correoElectronico) {

        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
        this.correoElectronico = correoElectronico;

        //datos de listas
        this.cantMaxima = 500;
        this.cantActualMensajes = 0;
        this.cantActualPerfiles = 0;


        /* con 3 espacios (espacio1 guarda 0 o 1 indicando si llego una solicitud,
        espacio2 guarda 0 o 1 indicando si la acepto o no,
        espacio3 guarda el nombre de la persona para buscarla yagregarla.)
         */
        this.solicitudes = new String[3];
        this.solicitudes[0] = "0"; // 1 si hay solicitud, 0 si no
        this.solicitudes[1] = "0"; // 1 si acepto la solicitud, 0 si no
        this.solicitudes[2] = "null"; // null si no hay nombre

        this.listaPerfiles = new Perfil[this.cantMaxima];
        this.listaMensajeria = new Mensajeria[this.cantMaxima];

    }

    /**
     * Metodo para agregar un Perfil
     *
     * @param nuevoPerfil de tipo Perfil
     * @return true si fue agregado a la lista, false si no
     */
    public boolean agregarPerfil(Perfil nuevoPerfil) {

        if (this.cantActualPerfiles < this.cantMaxima) {

            for (int i = 0; i <= cantMaxima; i++) {
                if (this.listaPerfiles[i] == null) {
                    this.listaPerfiles[i] = nuevoPerfil;
                    this.cantActualPerfiles++;
                    return true;
                }
            }
        } else {
            StdOut.println("No puedes añadir más conexiones. (Alcanzaste el límite)");
            return false;
        }
        return false;
    }

    /**
     * Metodo para eliminar un perfil de la lista
     *
     * @param eliminar : de perfil
     * @return
     */
    //TODO ARREGLAR (hay que correr los perfiles)
    public boolean eliminarPerfil(Perfil eliminar) {
        if (eliminar != null && buscarPerfil(eliminar.getNombreUsuario()) != -1) {
            listaPerfiles[buscarPerfil(eliminar.getNombreUsuario())] = null;
            return true;
        }
        return false;
    }

    /**
     * Metodo para buscar perfil
     *
     * @param nombre tipo String
     * @return la posicion al encontrarlo, -1 si no
     */
    public int buscarPerfil(String nombre) {
        if (!nombre.equals("")) {

            for (int i = 0; i < this.cantActualPerfiles; i++) {
                if (this.listaPerfiles[i].getNombreUsuario().equalsIgnoreCase(nombre)) {
                    return i;
                }
            }
            return -1;
        } else {
            StdOut.println("No se puede buscar ese nombre de usuario.");
        }
        return -1;
    }

    /**
     * Metodo de obtener un perfil mediante su correo
     *
     * @param correoElectronico tipo String
     * @return retorna su correo, si no null si no se obtuvo
     */
    public Perfil obtenerPerfil(String correoElectronico) {
        if (buscarPerfil(correoElectronico) != -1) {
            return this.listaPerfiles[buscarPerfil(correoElectronico)];
        }
        return null;
    }

    //TODO LO DE ABAJO
    /**
     * Metodo que agrega mensajes
     * @param mensajeNuevo enviado por un perfil
     * @return
     */
    public boolean agregarMensaje(Mensajeria mensajeNuevo) {




        return false;
    }

    /**
     * Metodo que elimina el mensaje de alguien
     * @return true si se elimino, false si no
     */
    public boolean eliminarMensaje() {
        return false;
    }

    /**
     * Metodo que busca un mensaje mediante un correo
     * @param correoElectronico correo de la persona a buscar
     * @return true si está, false si no
     */
    public int buscarMensaje(String correoElectronico) {

        for (int i = 0; i < this.listaMensajeria.length; i++) {
            //si el correo de destino es el mismo que estoy buscando
            if(this.listaMensajeria[i].getRemitente().equalsIgnoreCase(correoElectronico)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Metodo que despliega el mensaje de una persona
     * @param correoElectronico correo electronico de la persona buscar el mensaje
     */
    public void obtenerMensaje(String correoElectronico) {

        //si se encuentra el mensaje voy a desplegarlo
        if(buscarMensaje(correoElectronico) != -1){
            StdOut.println("\nMensaje de: " + this.listaMensajeria[buscarMensaje(correoElectronico)].getRemitente());
            StdOut.println("|MENSAJE| -->  " + this.listaMensajeria[buscarMensaje(correoElectronico)].getContenidoMensaje());
            StdOut.println("\n Hora de envío: " + this.listaMensajeria[buscarMensaje(correoElectronico)].getHoraEnvio());
        }else{
            StdOut.println("Ésta persona no existe.");
        }

    }


    /**
     * metodo que devuelve el nombre de usuario
     * @return nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Metodo que devuelve la contraseña del perfil
     * @return la contraseña del usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Metodo que devuelve el tipo de perfil del usuario
     * @return tipo del perfil
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo que devuelve la cantidad maxima de las lista
     * @return cantidad maxima
     */
    public int getCantMaxima() {
        return cantMaxima;
    }

    public Mensajeria[] getListaMensajeria() {
        return listaMensajeria;
    }

    public Perfil[] getListaPerfiles() {
        return listaPerfiles;
    }

    public int getCantActualMensajes() {
        return cantActualMensajes;
    }

    public void setCantActualMensajes(int cantActualMensajes) {
        this.cantActualMensajes = cantActualMensajes;
    }

    public int getCantActualPerfiles() {
        return cantActualPerfiles;
    }

    public void setCantActualPerfiles(int cantActualPerfiles) {
        this.cantActualPerfiles = cantActualPerfiles;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String[] getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(String[] solicitudes) {
        this.solicitudes = solicitudes;
    }

    /**
     * Metodo para comparar un perfil
     * @param perfilComparar de un usuario
     * @return true si son iguales, false si no
     */
    public boolean esIgual(Perfil perfilComparar) {
        if (this.nombreUsuario.equalsIgnoreCase(perfilComparar.getNombreUsuario())) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que dice si la lista de mensajes esta vacia
     * @return true si esta vacia, false si no
     */
    public boolean estaVaciaMensajeria(){

        boolean n = false;
        int j = 0;

        for (int i = 0; i < this.listaMensajeria.length; i++) {

            if(this.listaMensajeria[i] == null){
                j++;
            }
        }
        //si la cantidad maxima es igual a j significa que todos los espacios de la lista están vacios
        if(j == this.cantMaxima){
            return true;
        }
        return false;

    }
}

