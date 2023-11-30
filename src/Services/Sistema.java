package Services;

import Model.Objects.Perfil;

public interface Sistema {

    void ordenarPorNombre();
    void menu();
    void solicitudes();
    void buscarTipoPerfil();
    void buscarPorNombre();
    boolean iniciarSesion();
    boolean registrarse();

}
