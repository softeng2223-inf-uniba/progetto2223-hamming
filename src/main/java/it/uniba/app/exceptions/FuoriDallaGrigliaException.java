package it.uniba.app.exceptions;
/**
 * Eccezione lanciata quando vengono inserite coordinate errate.
 *
 * @author Gruppo Hamming
 */
public class FuoriDallaGrigliaException extends Exception {
    /**
     * Costruttore senza parametri.
     */
    public FuoriDallaGrigliaException() {
        super("Coordinate errate, sono fuori dalla griglia.");
    }

    /**
     * Costruttore con parametro stringa.
     *
     * @param riga   numero righe
     * @param colonna numero colonne
     */
    public FuoriDallaGrigliaException(final int riga, final int colonna) {
        super("Le coordinate " + ((char) (colonna + 'a')) + "-" + (String.valueOf(riga + 1))
                + " inserite sono fuori dalla griglia");
    }

}

