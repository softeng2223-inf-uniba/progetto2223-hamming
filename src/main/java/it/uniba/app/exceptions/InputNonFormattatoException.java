package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando viene inserito un comando che non inizia con /.
 *
 * @author Gruppo Hamming
 */
public class InputNonFormattatoException extends Exception {
    /**
     * Costruttore senza parametri.
     */
    public InputNonFormattatoException() {
        super();
    }

    /**
     * Costruttore con parametro stringa.
     * @param input comando o attacco inserito
     */
    public InputNonFormattatoException(final String input) {
        super("Il comando \"" + input
                + "\" non è formattato correttamente.\n"
                + "Inserire il carattere \"/\" all'inizio se si tratta di un comando;\n"
                + "Scriverlo nella forma <colonna>-<riga> (es. B-4) se si tratta di un attacco.");
    }
}
