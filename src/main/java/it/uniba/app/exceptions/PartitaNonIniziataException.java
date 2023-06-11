package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando si inserisce un attacco e non è in corso nessuna partita.
 *
 * @author Gruppo Hamming
 */
public class PartitaNonIniziataException extends ComandiException {
    /**
     * Costruttore senza parametri.
     */
    public PartitaNonIniziataException() {
        super("Non è in corso nessuna partita.");
    }
}
