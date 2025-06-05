package Vista;

import Controlador.SesionActiva;
import Modelo.DatosLogin;
import Controlador.Login;
import Modelo.Usuario;

import java.util.Scanner;

/**
 * Clase responsable de interactuar con el usuario por consola.
 * Controla el menú principal y el flujo de login.
 */
public class ConsolaLogin {
    private final Scanner sc = new Scanner(System.in);
    private final DatosLogin datos = new DatosLogin();
    private final Login login = new Login();


    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = stringToint(sc.next());
            ejecutarOpcion(opcion);
        }  while (opcion != 2);
    }

    /**
     * Muestra las opciones disponibles para el usuario.
     */
    private void mostrarOpciones() {
        System.out.println("Menu Principal");
        System.out.println("1- Iniciar sesión");
        System.out.println("2- Salir");
    }

    /**
     * Ejecuta la opción seleccionada por el usuario.
     *
     * @param opcion opción ingresada por el usuario
     */
    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                manejarLogin();
                break;
            case 2:
                System.out.println("Cerrando Programa");
                System.exit(2);
            default:
                System.out.println("Opcion invalida");
                break;
        }
    }

    /**
     * Solicita usuario y contraseña, y muestra el resultado.
     */
    private void manejarLogin() {
        System.out.println("Ingrese Nombre de Usuario");
        String nombre = sc.next();
        System.out.println("Ingrese Contraseña");
        String clave = sc.next();

        DatosLogin datosLogin = datos;

        if (login.autenticar(nombre, clave, datosLogin)) {
            System.out.println("Inicio de sesión exitoso.");
            SesionActiva sesion = new SesionActiva(new Usuario(nombre, clave));
            sesion.menuSesion();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }

        sc.nextLine();
        menu();
    }



    private static int stringToint(String number){
        int num = 0;

        if(isInt(number)){
            num = Integer.parseInt(number);
        }else {
            System.out.println("Argumento Invalido");
        }

        return num;
    }

    private static boolean isInt(String number){
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}