package it.uniba.app.interfaccia;

import it.uniba.app.exceptions.ComandoNonFormattatoException;
import it.uniba.app.gioco.Partita;
import it.uniba.app.util.Util;

/**
 * Classe che gestisce i comandi inseriti dall'utente e li esegue.
 * @author Gruppo Hamming
 */
public class GestioneComandi {
    private static Partita partita = null;
    private static Boolean continua = true;
    private static String livello = null;

    /**
     * Restituisce la partita.
     */
    public static Partita getPartita() {
        return partita;
    }

    /**
     * Restituisce il livello di difficoltà impostato.
     */
    public static String getLivello() {
        return livello;
    }

    /**
     * Imposta il livello di difficoltà.
     */
    public static void setLivello(final String livelloParam) {
        livello = livelloParam;
    }

    /**
     * Imposta il valore di cotinua.
     */
    public static void setContinua(final Boolean continuaParam) {
        continua = continuaParam;
    }

    /**
     * Restituisce il valore di continua.
     */
    public Boolean getContinua() {
        return continua;
    }

    /**
     * Ciclo principale del menu.
     */
    public static void mainLoop() {
        continua = true;
        while (continua) {
        }
    }

    /**
     * Legge un comando da tastiera.
     */
    public static String leggiComando() throws ComandoNonFormattatoException {
        System.out.println("\nInserisci un comando: ");
        System.out.print("> ");

        String input = Util.getString();
        if (!input.startsWith("/")) {
            throw new ComandoNonFormattatoException(input);
        }
        input = input.substring(1).toLowerCase();
        return input;
    }


}