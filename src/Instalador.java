import Model.Objects.SistemaImpl;
import Services.Sistema;

public class Instalador {
    private Sistema sistema;

    public  Instalador(){
        this.sistema = new SistemaImpl();
    }

    public Sistema InyectorSistema(){
        return this.sistema;
    }
}
