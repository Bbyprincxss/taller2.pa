package Model.Objects;

public class Mensajeria {

    /**
     * persona quien envio el mensaje
     */
    private String remitente;
    /**
     * persona a quien va el mensaje
     */
    private String destinatario;
    /**
     * contenido del mensaje a enviar
     */
    private String contenidoMensaje;
    /**
     * hora de envio del mensaje
     */
    private String horaEnvio;

    /**
     * verifica si la persona tiene un mensaje neuvo
     */
    private boolean mensajeNuevo;

    /**
     * Constructor
     * @param remitente persona quien envio el mensaje
     * @param destinatario persona a quien se le dirige el mensaje
     * @param contenidoMensaje el mensaje en sí
     * @param horaEnvio hora del envio del mensaje
     */
    public Mensajeria(String remitente, String destinatario, String contenidoMensaje, String horaEnvio, boolean mensajeNuevo) {

        this.remitente = remitente;
        this.destinatario = destinatario;
        this.contenidoMensaje = contenidoMensaje;
        this.horaEnvio = horaEnvio;
        this.mensajeNuevo = mensajeNuevo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getContenidoMensaje() {
        return contenidoMensaje;
    }

    public void setContenidoMensaje(String contenidoMensaje) {
        this.contenidoMensaje = contenidoMensaje;
    }

    public String getHoraEnvio() {
        return horaEnvio;
    }

    public boolean getMensajeNuevo() {
        return mensajeNuevo;
    }

    public void setMensajeNuevo(boolean mensajeNuevo) {
        this.mensajeNuevo = mensajeNuevo;
    }

    public void setHoraEnvio(String horaEnvio) {
        this.horaEnvio = horaEnvio;
    }


}
