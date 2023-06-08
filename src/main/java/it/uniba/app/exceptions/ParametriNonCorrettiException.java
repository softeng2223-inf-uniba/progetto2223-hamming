package it.uniba.app.exceptions;

public class ParametriNonCorrettiException extends Exception {
    
    public ParametriNonCorrettiException(final String message) {
        super(message);
    }

    public ParametriNonCorrettiException() {
        super("Parametri non corretti per il comando.");
    }

}
