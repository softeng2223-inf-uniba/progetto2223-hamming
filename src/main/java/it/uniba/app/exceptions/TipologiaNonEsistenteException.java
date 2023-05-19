package it.uniba.app.exceptions;

/**
    Eccezione lanciata quando si cerca di accedere ad una tipologia non esistente
    all'interno del sistema.
    @author Gruppo Hamming
*/
public class TipologiaNonEsistenteException extends RuntimeException {

    /**
        Costruttore di default. Chiama il costruttore della superclasse RuntimeException.
     */
    public TipologiaNonEsistenteException() {
        super();
    }

    /**
        Costruttore con parametri. Chiama il costruttore della superclasse RuntimeException
        e passa il messaggio di errore.
        @param msg Messaggio da mostrare all'utente.
     */
    public TipologiaNonEsistenteException(final String msg) {
        super(msg);
    }
}
