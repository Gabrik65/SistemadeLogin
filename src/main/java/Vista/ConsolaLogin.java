package Vista;

import Modelo.DatosLogin;
import Controlador.Login;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase responsable de interactuar con el usuario por consola.
 * Controla el menú principal y el flujo de login.
 */
public class ConsolaLogin {
    Scanner sc = new Scanner(System.in);
    DatosLogin datos = new DatosLogin("login.txt");
    Login login = new Login();

    public ConsolaLogin() throws IOException {
    }

    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = stringToint(sc.next());
            ejecutarOpcion(opcion);
        }  while (opcion == 2);
    }

    /**
     * Muestra las opciones disponibles para el usuario.
     */
    private void mostrarOpciones() {
        // TODO: Mostrar "1. Iniciar sesión", "2. Salir"
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
            case 1 -> manejarLogin();
            case 2 -> System.out.println("Cerrando Programa");
            default -> System.out.println("Opcion invalida");
        }
    }

    /**
     * Solicita usuario y contraseña, y muestra el resultado.
     */
    private void manejarLogin() {
        // TODO: Pedir usuario y contraseña por consola
        // TODO: Llamar a login.autenticar() y mostrar mensaje según resultado
        System.out.println("Ingrese Nombre de Usuario");
        String usuario = sc.next();
        System.out.println("Ingrese Contraseña");
        String pw = sc.next();
        DatosLogin datosLogin = datos;

        if(login.autenticar(usuario,pw,datosLogin)){
            System.out.println("Usuario " + usuario + " ingresó correctamente");
        }else {
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