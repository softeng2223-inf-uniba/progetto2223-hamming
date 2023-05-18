package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando si cerca di eseguire un comando non esistente.
 * @author Gruppo Hamming
 */
public class ComandoNonEsistenteException extends Exception {
    /**
     * Costruttore senza parametri.
     */
    public ComandoNonEsistenteException() {
        super();
    }

    /**
     * Costruttore con parametro stringa.
     * @param message messaggio da visualizzare
     */
    public ComandoNonEsistenteException(final String message) {
        super("Il comando /" + message + " non esiste.");
    }
}
