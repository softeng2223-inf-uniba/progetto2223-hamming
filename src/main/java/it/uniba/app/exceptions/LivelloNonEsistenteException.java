package it.uniba.app.exceptions;
/**
 Eccezione lanciata quando si cerca di accedere a un livello non esistente.

 @author Gruppo Hamming
 */
public class LivelloNonEsistenteException extends RuntimeException {
    /**
     Costruttore di default. Chiama il costruttore della superclasse.
     */
    public LivelloNonEsistenteException() {
        super();
    }

    /**
     Costruttore con parametri. Chiama il costruttore della superclasse e passa il messaggio di errore.

     @param msg Messaggio da mostrare all'utente.
     */
    public LivelloNonEsistenteException(final String msg) {
        super(msg);
    }
}
