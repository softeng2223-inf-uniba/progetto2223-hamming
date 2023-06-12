package it.uniba.app.exceptions;


/**
 * Generalizzazione delle eccezioni lanciabili da un comando.
 */
public class ComandiException extends Exception {
    /**
     * Costruttore senza parametri.
     */
    public ComandiException() {
        super();
    }

    /**
     * Costruttore con parametro stringa.
     * @param message messaggio da visualizzare
     */

    public ComandiException(final String message) {
        super(message);
    }

}
