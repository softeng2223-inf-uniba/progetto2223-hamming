package it.uniba.app.interfaccia;

import it.uniba.app.gioco.Partita;

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
}
