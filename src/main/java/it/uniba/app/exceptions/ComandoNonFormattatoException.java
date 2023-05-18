package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando si cerca di eseguire un comando non esistente.
 * @author Gruppo Hamming
 */
public class ComandoNonFormattatoException extends Exception {
    /**
     * Costruttore senza parametri.
     */
    public ComandoNonFormattatoException() {
        super();
    }

    /**
     * Costruttore con parametro stringa.
     * @param message messaggio da visualizzare
     */
    public ComandoNonFormattatoException(final String message) {
        super("Il comando \"" + message
                + "\" non è formattato correttamente, manca il carattere \"/\" all'inizio del comando.");
    }
}
