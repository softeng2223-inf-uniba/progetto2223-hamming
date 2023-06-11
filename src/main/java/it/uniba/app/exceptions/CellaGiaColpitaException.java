package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando viene attaccata una cella già colpita in precedenza.
 *
 * @author Gruppo Hamming
 */
public class CellaGiaColpitaException extends Exception {
    /**
     * Costruttore senza parametri.
     */
    public CellaGiaColpitaException() {
        super("La cella è già colpita.");
    }

    /**
     * Costruttore con parametro stringa.
     *
     * @param riga numero righe
     */
    public CellaGiaColpitaException(final int riga, final int colonna) {
        super("La cella delle coordinate " + ((char) (colonna + 'a')) + "-" + (String.valueOf(riga + 1))
                + " è stata già colpita");
    }
}
