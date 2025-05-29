package org.ejemplo;

import Modelo.DatosLogin;
import Controlador.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    DatosLogin datos = new DatosLogin();

    @BeforeEach
    void testStart(){
        datos.credenciales = new ArrayList<>(List.of("admin;1234"));
    }
    @Test
    void testLoginValido() {
        Assertions.assertTrue(new Login().autenticar("admin", "1234", datos));
    }

    @Test
    void testUsuarioInexistente() {
        Assertions.assertFalse(new Login().autenticar("noexiste", "1234", datos));
    }

    @Test
    void testContraseñaIncorrecta() {
        Assertions.assertFalse(new Login().autenticar("admin", "0000", datos));
    }

    @Test
    void testUsuarioRepetido() {
        datos.credenciales = new ArrayList<>(List.of("admin;1234", "admin;abcd"));
        Assertions.assertTrue(new Login().autenticar("admin", "1234", datos));
        Assertions.assertTrue(new Login().autenticar("admin", "abcd", datos));
    }

    @Test
    void testArchivoNoEncontrado() {
        Assertions.assertThrows(IOException.class, () -> {
            new DatosLogin("archivo_que_no_existe.txt");
        });
    }
}
