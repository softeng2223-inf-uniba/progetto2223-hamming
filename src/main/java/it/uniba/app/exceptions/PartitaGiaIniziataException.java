package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando un comando richiesto non può essere
 * eseguito mentre una partita è già iniziata.
 *
 * @author Gruppo Hamming
 */
public class PartitaGiaIniziataException extends ComandiException {
    /**
     * Costruttore di default.
     *
     */
    public PartitaGiaIniziataException() {
        super();
    }

    /**
     * Costruttore che richiama il costruttore della super-classe e passa il messaggio di errore.
     *
     * @param msg messaggio stampato
     */
    public PartitaGiaIniziataException(final String msg) {
        super(msg);
    }

}
