package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando i parametri passati al comando non sono corretti.
 *
 * @author Gruppo Hamming
 */
public class ParametriNonCorrettiException extends ComandiException {

    /**
     * Costruttore con parametri. Chiama il costruttore della superclasse e passa il messaggio di errore.
     * @param message Messaggio da mostrare all'utente.
     */
    public ParametriNonCorrettiException(final String message) {
        super(message);
    }

    /**
     * Costruttore di default. Chiama il costruttore della superclasse.
     */
    public ParametriNonCorrettiException() {
        super("Parametri non corretti per il comando.");
    }

}
