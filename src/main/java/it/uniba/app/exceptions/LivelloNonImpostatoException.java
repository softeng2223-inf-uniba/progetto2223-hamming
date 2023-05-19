package it.uniba.app.exceptions;

/**
    Eccezione lanciata quando si cerca di eseguire un comando che richiede un livello attualmente non impostato.
    @author Gruppo Hamming
*/
public class LivelloNonImpostatoException extends Exception {

    /**
        Costruttore di default. Chiama il costruttore della superclasse Exception.
     */
    public LivelloNonImpostatoException() {
        super();
    }

    /**
        Costruttore con parametri. Chiama il costruttore della superclasse Exception
        e passa il messaggio di errore.
        @param msg Messaggio da mostrare all'utente.
     */
    public LivelloNonImpostatoException(final String msg) {
        super(msg);
    }
}
