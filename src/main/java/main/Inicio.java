package main;

import consolecontroller.ConsolaLogin;

import java.io.IOException;

public class Inicio {
    public static void main(String[] args) throws IOException {
        ConsolaLogin consola = new ConsolaLogin();
        consola.menu();
    }
}