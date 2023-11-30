import Model.Objects.Perfil;
import Model.Objects.SistemaImpl;
import Services.Sistema;
import ucn.StdIn;
import ucn.StdOut;


public class Main {
    public static void main(String[] args) {
        configuracion();

    }
    
    /**
     * Metodo de configuracion
     */
    public static void configuracion(){
        Sistema sistemaApp = instalarSistema();
        menuInicio(sistemaApp);
    }

    /**
     * Metodo para instalar el sistema
     * @return un nuevo sistema
     */
    public static Sistema instalarSistema(){
        return new Instalador().InyectorSistema();
    }

    /**
     * Metodo que contiene el menu de inicio del programa
     * @param sistemaApp de tipo Sistema
     */
    public static void menuInicio (Sistema sistemaApp){
        SistemaImpl sistema = new SistemaImpl();

        boolean SalirMenuInicio = false;

        while (!SalirMenuInicio){

            StdOut.println("""
                    .:: Menu Aplicación ::.
                    [1] - Iniciar Sesion
                    [2] - Registrarse
                    [3] - Salir de la aplicacion
                    
                    Ingrese una opcion
                    """);
            String opcionMenu = StdIn.readString();
            int opcionInt;

            try{
                opcionInt = Integer.parseInt(opcionMenu);
            }catch (Exception e){
                StdOut.println("Ingresó una opcion invalida, intente nuevamente...");
                continue;
            }

            switch (opcionInt){
                case 1:
                    sistema.iniciarSesion();
                    break;
                case 2:
                    if (sistema.registrarse()){
                        StdOut.println(":::Se ha registrado correctamente a la aplicacion:::");
                        StdOut.println();
                        sistema.menu();
                        break;

                    }else{
                        StdOut.println("El correo que ingreso ya esta registrado, intente con otro...");
                        StdOut.println(":::Volviendo al inicio:::");
                        break;
                    }

                case 3:
                    StdOut.println(":::Apagando aplicacion:::");
                    StdOut.println("Hasta luego...");
                    SalirMenuInicio = true;
                    break;

                default:
                    StdOut.println("Ingresó una opcion invalida, intente nuevamente...\n");
            }
        }
    }
}


/*
ERRORES:
SIEMPRE SALE QUE NO TIENES NINGUN MSJ KSDJFSDLJFDSJKFKJSKFLKSDJFKSDLFLJSDJLFJSDJKFKSDLFDSL

 */