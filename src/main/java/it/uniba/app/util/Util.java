package it.uniba.app.util;

import java.util.Scanner;

/**
 * Classe di utilità contenente dei metodi generici utili per l'applicazione.
 */
public final class Util {
    private static Scanner scan = new Scanner(System.in, "UTF-8");

    private Util() { }

    public static String getString() {
        return scan.nextLine();
    }
}
