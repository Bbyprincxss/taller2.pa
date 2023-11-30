package Model.Objects;

import Model.List.ListaNodoDoblePerfil;
import Services.Sistema;
import ucn.StdIn;
import ucn.StdOut;

public class SistemaImpl implements Sistema {

    /**
     * Atributos
     */
    private ListaNodoDoblePerfil listaPerfiles;
    private Perfil perfilActual;

    /**
     * Constructor
     */
    public SistemaImpl() {
        this.listaPerfiles = new ListaNodoDoblePerfil();
    }

    /**
     * Metodo que inicia sesion
     * @return true si se inicio, false si no
     */
    public boolean iniciarSesion() {
        StdOut.println("Ingrese su nombre: ");
        String nombre = StdIn.readString();
        StdOut.println("Ingrese su contraseña: ");
        String contrasenia = StdIn.readString();

        //comprobar que este en la lista nexo
        if (this.listaPerfiles.buscar(nombre, contrasenia)) {

            //se dice quien está usando el programa ahora mismo
            this.perfilActual = listaPerfiles.obtener(nombre);

            menu();
        }
        StdOut.println("Datos erroneos, intente de nuevo...");
        StdOut.println("-----------------------------------");
        return false;
    }

    /**
     * Metodo registrar nuevo perfil
     *
     * @return true si esta correctamente registrado
     */
    public boolean registrarse() {
        StdOut.println(":::Formulario de datos:::");

        String nombreNuevo;
        //validar que el nombre no tenga numeros
        while (true) {
            String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

            StdOut.println("Ingrese su nombre: ");
            nombreNuevo = StdIn.readString();

            try {
                for (int i = 0; i < nombreNuevo.length(); i++) {
                    for (String numero : numeros) {
                        if (nombreNuevo.charAt(i) == numero.charAt(0)) {
                            throw new IllegalArgumentException();
                        }
                    }
                }
            } catch (IllegalArgumentException e) {

                StdOut.println("""
                        \n--------------------------------
                        El nombre no puede tener números
                        --------------------------------\n
                        """);
                continue;
            }
            break;
        }

        StdOut.println("Ingrese una contraseña: ");
        String contraseniaNueva = StdIn.readString();

        StdOut.println("Ingrese el tipo de cuenta (personal o empresa)");
        String tipo = StdIn.readString();

        while (!(tipo.equalsIgnoreCase("personal") || tipo.equalsIgnoreCase("empresa"))) {
            mensajeError();
            StdOut.println("Ingrese el tipo de cuenta (personal o empresa)");
            tipo = StdIn.readString();
        }

        String correo = "";
        boolean correoUsado = false;
        while (!correoUsado) {
            StdOut.println("Ingrese un correo electronico: ");
            correo = StdIn.readString();
            if (!this.listaPerfiles.estaVacia()) {
                for (Nodo aux = this.listaPerfiles.getCabeza(); aux != null; aux = aux.getSiguiente()) {
                    //si el correo ya existe
                    if (aux.getElemento().getCorreoElectronico().equalsIgnoreCase(correo)) {
                        StdOut.println("Ese correo electronico ya esta en uso, intente con otro...\n");
                        break;
                    } else {
                        correoUsado = true;
                        break;
                    }
                }
            } else {
                break;
            }
        }

        //Agregar un nuevo perfil
        Perfil perfilNuevo = new Perfil(nombreNuevo, contraseniaNueva, tipo, correo);
        this.listaPerfiles.agregar(perfilNuevo);
        //setear perfil actual
        this.perfilActual = perfilNuevo;
        return true;
    }

    @Override
    public void ordenarPorNombre() {
        StdOut.println("|0| Nombres ordenados |0|");

        String[] ABECEDARIO = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        ListaNodoDoblePerfil listaAuxiliar = new ListaNodoDoblePerfil();

        for (String letraAbecedario : ABECEDARIO) {
            organizarPerfilesPorLetra(letraAbecedario, listaAuxiliar);
        }

        for (Nodo aux = listaAuxiliar.getCabeza(); aux != null; aux = aux.getSiguiente()) {
            System.out.println(aux.getElemento().getNombreUsuario());
            StdOut.println();
        }

    }

    private void organizarPerfilesPorLetra(String letra, ListaNodoDoblePerfil listaAuxiliar) {
        for (Nodo aux = this.listaPerfiles.getCabeza(); aux != null; aux = aux.getSiguiente()) {
            char primeraLetra = aux.getElemento().getNombreUsuario().toLowerCase().charAt(0);
            char letraAbecedario = letra.charAt(0);

            if (primeraLetra == letraAbecedario) {
                listaAuxiliar.agregar(aux.getElemento());
            }
        }
    }

    /**
     * Metodo MENU PRINCIPAL
     */
    @Override
    public void menu() {

        boolean terminarMenuPrincipal = false;

        while (!terminarMenuPrincipal) {

            StdOut.println("""
                    \n****** Menu principal ******
                                         
                        1) Gestionar perfil
                        2) Mensajes
                        3) Gestionar solicitudes  
                        4) Lista de conexiones
                        5) Cerrar sesion
                                      
                    Ingrese su opción:             
                     """);
            //opcion 1 : buscar perfil y ordenar perfiles
            //opcion 2 : enviar mensajes a amigos
            //opcion 3 : agregar amigos, rechazar, o enviar
            //opcion 4 : volver al menuInicio
            String opcionMenu = StdIn.readString();
            int opcionInt;

            try {
                opcionInt = Integer.parseInt(opcionMenu);
            } catch (Exception e) {
                mensajeError();
                continue;
            }
            switch (opcionInt) {
                case 1:
                    gestionarPerfil();
                    break;
                case 2:
                    menuMensajes();
                    break;
                case 3:
                    solicitudes();
                    break;
                case 4:
                    listaConexiones();
                    break;
                case 5:
                    terminarMenuPrincipal = true;
                    break;
                default:
                    mensajeError();
            }
        }
    }

    public void listaConexiones() {
        boolean hayAmigos = false;
        for (int i = 0; i < perfilActual.getListaPerfiles().length; i++) {
            if (perfilActual.getListaPerfiles()[i] != null) {
                StdOut.println("|+| " + perfilActual.getListaPerfiles()[i].getNombreUsuario() + " | " + perfilActual.getListaPerfiles()[i].getCorreoElectronico() + " | " + perfilActual.getListaPerfiles()[i].getTipo());
                hayAmigos = true;
            }
        }
        if(!hayAmigos){
            //si no tengo amigos
            StdOut.println("No tienes amigos aún.");
        }

    }



    /**
         * Metodo para la gestion de solicitudes
         */
    @Override
    public void solicitudes() {

        boolean terminarGestionarSolicitudes = false;

        while (!terminarGestionarSolicitudes) {

            StdOut.println("""
                                        
                    ****** Gestion de solicitudes ******
                                    
                    1) Mostrar solicitudes 
                    2) Enviar solicitud 
                    3) Volver
                         
                    Ingrese su opción: 
                    """);
            String opcionString = StdIn.readString();
            int opcionInt;

            try {
                opcionInt = Integer.parseInt(opcionString);
            } catch (Exception e) {
                mensajeError();
                continue;
            }

            switch (opcionInt) {
                case 1:

                    StdOut.println("..:: Solicitudes ::.. ");

                    //verificar si hay una solicitud
                    if (perfilActual.getSolicitudes()[0].equalsIgnoreCase("1")) {

                        //si hay solicitud pero es un rechazo
                        if (perfilActual.getSolicitudes()[1].equalsIgnoreCase("1")) {

                            StdOut.println("SOLICITUD RECHAZADA DE " + this.listaPerfiles.obtenerPerfilA(perfilActual.getSolicitudes()[2]).getCorreoElectronico());
                            String[] setearDe0 = new String[3];
                            setearDe0[0] = "0";
                            setearDe0[1] = "0";
                            setearDe0[2] = "null";
                            this.perfilActual.setSolicitudes(setearDe0);

                            //si hay una solicitud para agregar
                        } else {
                            StdOut.println("De: " + perfilActual.getSolicitudes()[2]);
                            StdOut.println("¿Aceptar o Rechazar?");
                            String opcion = StdIn.readString();

                            switch (opcion) {

                                case "aceptar", "ACEPTAR":

                                    //se agrega a mi lista de amigos
                                    perfilActual.agregarPerfil(this.listaPerfiles.obtenerPerfilA(perfilActual.getSolicitudes()[2]));

                                    //modificar mi lista de solicitudes para que no se repita la misma
                                    String[] solicitudNuevaA = new String[3];
                                    solicitudNuevaA[0] = "0";
                                    solicitudNuevaA[1] = "0";
                                    solicitudNuevaA[2] = "null";

                                    perfilActual.setSolicitudes(solicitudNuevaA);

                                    StdOut.println("~~~~ Se ha aceptado la solicitud ~~~~");


                                    break;

                                case "rechazar", "RECHAZAR":

                                    String[] solicitudNuevaR = new String[3];
                                    solicitudNuevaR[0] = "1";
                                    solicitudNuevaR[1] = "1";
                                    solicitudNuevaR[2] = perfilActual.getCorreoElectronico();

                                    //se le envia el rechazo a la persona que la envió
                                    this.listaPerfiles.obtenerPerfilA(perfilActual.getSolicitudes()[2]).setSolicitudes(solicitudNuevaR);
                                    this.perfilActual.eliminarPerfil(listaPerfiles.obtenerPerfilA(perfilActual.getSolicitudes()[2]));

                                    //se me elimina de la lista de la persona a quien rechazé.
                                    this.listaPerfiles.obtenerPerfilA(perfilActual.getSolicitudes()[2]).eliminarPerfil(this.perfilActual);

                                    String[] setearDe0 = new String[3];
                                    setearDe0[0] = "0";
                                    setearDe0[1] = "0";
                                    setearDe0[2] = "null";
                                    this.perfilActual.setSolicitudes(setearDe0);

                                    StdOut.println("Se ha rechazado la solicitud.");

                                    break;

                                default:
                                    mensajeError();
                            }
                        }
                        //si no hay solicitud
                    } else {
                        StdOut.println("\n++++++ No tienes ninguna notificación ++++++");
                        StdOut.println();
                    }
                    break;

                case 2:
                    enviarSolicitud();
                    break;

                case 3:
                    terminarGestionarSolicitudes = true;
                    break;

                default:
                    mensajeError();

            }
        }

    }

    /**
     * Metodo para buscar un perfil segun el tipo de este(personal o de empresa)
     */
    @Override
    public void buscarTipoPerfil() {

        boolean terminarBuscar = false;
        while (!terminarBuscar) {
            StdOut.println("""
                    \n..::: POR TIPO DE PERFIL :::..
                                    
                    1) Por persona
                    2) Por empresa
                    3) Volver
                                    
                    Ingrese su opción: 
                    """);
            String opcionString = StdIn.readString();
            int opcionInt;
            try {
                opcionInt = Integer.parseInt(opcionString);
            } catch (Exception e) {
                StdOut.println("Ingrese un dato válido");
                continue;
            }

            switch (opcionInt) {


                case 1:
                    boolean verificarSiExistePerfilPersonal = false;
                    //se debe desplegar los perfiles que solo son de personas
                    for (Nodo aux = this.listaPerfiles.getCabeza(); aux != null; aux = aux.getSiguiente()) {
                        if (aux.getElemento().getTipo().equalsIgnoreCase("Personal")) {
                            //supuestamente arreglado
                            if (this.perfilActual.getTipo().equalsIgnoreCase("Personal")) {
                                StdOut.println("|+| " + aux.getElemento().getNombreUsuario() + " | (TÚ)");
                            } else {
                                StdOut.println("|+| " + aux.getElemento().getNombreUsuario());

                            }
                            verificarSiExistePerfilPersonal = true;
                        }
                    }
                    if (!verificarSiExistePerfilPersonal) {
                        StdOut.println("\nNo hay perfiles de tipo personal.");
                    }
                    break;


                case 2:

                    boolean verificarSiExistePerfilEmpresa = false;
                    //se debe desplegar los perfiles que solo son de empresas
                    for (Nodo aux = this.listaPerfiles.getCabeza(); aux != null; aux = aux.getSiguiente()) {
                        if (aux.getElemento().getTipo().equalsIgnoreCase("Empresa")) {
                            if (aux.getElemento().getTipo().equalsIgnoreCase(this.perfilActual.getTipo())) {
                                StdOut.println("|+| " + aux.getElemento().getNombreUsuario() + " | (TÚ)");
                            } else {
                                StdOut.println("|+| " + aux.getElemento().getNombreUsuario());

                            }
                            verificarSiExistePerfilEmpresa = true;
                        }

                    }
                    if (!verificarSiExistePerfilEmpresa) {
                        StdOut.println("\nNo hay perfiles de tipo empresa.");
                    }
                    break;

                case 3:
                    terminarBuscar = true;
                    break;

                default:
                    mensajeError();


            }
        }

    }


    /**
     * Metodo para buscar un perfil por un nombre
     */
    @Override
    public void buscarPorNombre() {

        boolean terminar = false;

        while (!terminar) {

            StdOut.println("\nIngrese el nombre para buscar (0 para volver)");
            String buscarNombre = StdIn.readString();

            //verificar si se quiso volver
            if (buscarNombre.equalsIgnoreCase("0")) {
                return;
            }

            //sirve para verificar si realmente existe el nombre en la lista
            boolean verificarNombre = false;

            for (Nodo aux = this.listaPerfiles.getCabeza(); aux != null; aux = aux.getSiguiente()) {
                if (aux.getElemento().getNombreUsuario().equalsIgnoreCase(buscarNombre)) {

                    //si la persona que sale soy yo le agrego un mensaje
                    if (aux.getElemento().getCorreoElectronico().equalsIgnoreCase(this.perfilActual.getCorreoElectronico())) {
                        StdOut.println("|+| " + aux.getElemento().getNombreUsuario() + " | " + aux.getElemento().getCorreoElectronico() + " | " + aux.getElemento().getTipo() + " | (TÚ)");
                    } else {
                        StdOut.println("|+| " + aux.getElemento().getNombreUsuario() + " | " + aux.getElemento().getCorreoElectronico() + " | " + aux.getElemento().getTipo());
                    }
                    verificarNombre = true;
                }
            }

            if (!verificarNombre) {
                StdOut.println("|*| No hay ningún perfil con el nombre ingresado.");
            }
        }
    }

    /**
     * Metodo que arroja un mensaje de error
     */
    public void mensajeError() {
        StdOut.println("""
                ---------------------------------------------------
                Ingresó una opcion invalida, intente nuevamente...
                ---------------------------------------------------\n
                """);
    }

    /**
     * Metodo que gestiona los perfiles
     */
    public void gestionarPerfil() {

        boolean terminarGestionarPerfil = false;

        while (!terminarGestionarPerfil) {

            StdOut.println("""
                    \n****** Gestion de perfiles ******
                                    
                    1) Buscar Perfil
                    2) Ordenar perfiles  
                    3) Volver
                         
                    Ingrese su opción: 
                    """);
            String opcionGestionPerfil = StdIn.readString();
            int opcionInt;

            try {
                opcionInt = Integer.parseInt(opcionGestionPerfil);
            } catch (Exception e) {
                mensajeError();
                continue;
            }

            switch (opcionInt) {
                case 1:
                    buscarPerfiles();
                    break;
                case 2:
                    ordenarPerfiles();
                    break;
                case 3:
                    terminarGestionarPerfil = true;
                    break;
                default:
                    mensajeError();
            }
        }
    }

    /**
     * Metodo que busca perfiles; por nombres o por tipos
     */
    public void buscarPerfiles() {

        boolean terminarBuscar = false;

        while (!terminarBuscar) {

            StdOut.println("""
                    \n****** Busqueda de perfiles ******
                                    
                    1) Por nombres
                    2) Por tipos
                    3) Volver
                         
                    Ingrese su opción: 
                    """);
            String opcionGestionPerfil = StdIn.readString();
            int opcionInt;

            try {
                opcionInt = Integer.parseInt(opcionGestionPerfil);
            } catch (Exception e) {
                mensajeError();
                continue;
            }

            switch (opcionInt) {
                case 1:
                    buscarPorNombre();
                    break;
                case 2:
                    buscarTipoPerfil();
                    break;
                case 3:
                    terminarBuscar = true;
                    break;
                default:
                    mensajeError();
            }
        }
    }

    /**
     * Metodo que ordena los perfiles segun su nombre o tipo
     */
    public void ordenarPerfiles() {
        boolean terminarOrdenarPerfil = false;

        while (!terminarOrdenarPerfil) {

            StdOut.println("""
                    ****** Ordenar perfiles ******
                                    
                    1) Por nombre
                    2) Por tipo de perfil  
                    3) Volver
                         
                    Ingrese su opción: 
                    """);
            String opcionOrdenarPerfil = StdIn.readString();
            int opcionInt;

            try {
                opcionInt = Integer.parseInt(opcionOrdenarPerfil);
            } catch (Exception e) {
                mensajeError();
                continue;
            }

            switch (opcionInt) {
                case 1:
                    ordenarPorNombre();
                    break;
                case 2:
                    buscarTipoPerfil();
                    break;
                case 3:
                    terminarOrdenarPerfil = true;
                    break;
                default:
                    mensajeError();
            }


        }
    }

    /**
     * Metodo para enviar solicitudes
     *
     * @return true para enviar una solicitur, si no retorna false
     **/
    public boolean enviarSolicitud() {

        StdOut.println("Escriba el correo de la persona que desea enviar una solicitud");
        String correoSolicitud = StdIn.readString();

        //sirve para verificar si el correo a quien le envio la solicitud ya está agregado en mi lista
        boolean yaEstaAgregado = false;

        //se recorre los amigos del perfil actual
        for (int i = 0; i < perfilActual.getListaPerfiles().length; i++) {


            if (perfilActual.getCorreoElectronico().equalsIgnoreCase(correoSolicitud)) {
                StdOut.println("\n~~~~ No te puedes enviar una solicitud a ti mismo :) ~~~~\n");
                yaEstaAgregado = true;
                break;

            } else if (perfilActual.getListaPerfiles()[i] != null) {
                if (perfilActual.getListaPerfiles()[i].getCorreoElectronico().equalsIgnoreCase(correoSolicitud)) {
                    yaEstaAgregado = true;
                }
            }
        }

        //ya existe en la lista de amigos
        if (yaEstaAgregado) {
            if (!perfilActual.getCorreoElectronico().equalsIgnoreCase(correoSolicitud)) {
                StdOut.println("Ya tiene agregada a la persona");
                return false;
            }

        } else {

            boolean existeCorreo = false;

            for (Nodo aux = this.listaPerfiles.getCabeza(); aux != null; aux = aux.getSiguiente()) {

                if (aux.getElemento().getCorreoElectronico().equalsIgnoreCase(correoSolicitud)) {

                    //persona a quien le envio una solicitud
                    Perfil personaEncontrada = aux.getElemento();

                    //creo una nueva lista para mandarsela a la persona
                    String[] solicitudNueva = new String[3];
                    solicitudNueva[0] = "1";
                    solicitudNueva[1] = "0";
                    solicitudNueva[2] = perfilActual.getCorreoElectronico();

                    //se cambia la lista de solicitudes nueva por la anterior
                    personaEncontrada.setSolicitudes(solicitudNueva);
                    this.perfilActual.agregarPerfil(personaEncontrada);
                    StdOut.println("\n~~~~ Se envio correctamente ~~~~\n");
                    existeCorreo = true;
                    return true;

                }

            }
            if(!existeCorreo){
                StdOut.println("\n~~~~ Correo no encontrado en el registro de la aplicacion ~~~~\n");
                return false;
            }
            return false;
        }

        return false;
    }


    /**
     * metodo que contiene un menu de gestion de mensajes
     */
    public void menuMensajes(){
        boolean terminarMensajes = false;

        while(!terminarMensajes) {

            StdOut.println("""
                    ****** Gestion de mensajes ******
                                    
                    1) Enviar mensajes
                    2) Mensajes pendientes
                    3) Volver
                         
                    Ingrese su opción: 
                    """);
            String opcionMensajes = StdIn.readString();
            int opcionInt;

            try{
                opcionInt = Integer.parseInt(opcionMensajes);
            }catch (Exception e){
                mensajeError();
                continue;
            }

            switch (opcionInt) {
                case 1:
                    StdOut.println("Escriba el correo de la persona que desea enviar un mensaje");
                    String correoMensaje = StdIn.readString();
                    boolean encotrado = false;

                    //se busca a la persona para verificar si existe su correo
                    for (Nodo aux = this.listaPerfiles.getCabeza(); aux != null; aux = aux.getSiguiente()) {

                        //si el correo existe en la lista de perfiles
                        if (aux.getElemento().getCorreoElectronico().equalsIgnoreCase(correoMensaje)) {
                            encotrado = true;
                        }
                    }
                    //si existe la persona para enviarle un mensaje
                    if(encotrado) {

                        StdOut.println("\n.::Escriba un mensaje para " + correoMensaje + "::.");
                        String mensajeNuevo = StdIn.readString();
                        StdOut.println("\nIngrese la hora de envío: ");
                        String hora = StdIn.readString();

                        //se crea un mensaje con los datos sacados
                        Mensajeria mensaje = new Mensajeria(perfilActual.getCorreoElectronico(), correoMensaje, mensajeNuevo, hora,true);

                        //se envia el mensaje a la persona
                        listaPerfiles.obtenerPerfilA(correoMensaje).agregarMensaje(mensaje);
                        StdOut.println("\nMensaje enviado");

                        //si no existe la persona con ese correo
                    }else {
                        StdOut.println("El correo no existe.");
                        continue;
                    }
                            break;

                case 2:

                    if (!this.perfilActual.estaVaciaMensajeria()) {
                        //verificar si hay mensajes
                        for (int i = 0; i <= this.perfilActual.getListaMensajeria().length; i++) {

                            if (this.perfilActual.getListaMensajeria()[i].getMensajeNuevo()) {
                                StdOut.println("Tienes un mensaje de: " + this.perfilActual.getListaMensajeria()[i].getRemitente());
                                StdOut.println("\n::: Mensaje :::\n");
                                StdOut.println(this.perfilActual.getListaMensajeria()[i].getContenidoMensaje());

                                //se setea como que ya no tiene mensaje nuevo
                                this.perfilActual.getListaMensajeria()[i].setMensajeNuevo(false);
                            }
                        }
                    }else {
                        StdOut.println("\n ::: No tienes mensajes:c :::\n");
                    }
                    break;
                case 3:
                    terminarMensajes = true;
                    break;
                default:
                    mensajeError();
            }
        }
    }
}

