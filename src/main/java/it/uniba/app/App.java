package it.uniba.app;

import it.uniba.app.exceptions.ComandoNonEsistenteException;
import it.uniba.app.exceptions.InputNonFormattatoException;
import it.uniba.app.exceptions.PartitaNonIniziataException;
import it.uniba.app.exceptions.ParametriNonCorrettiException;
import it.uniba.app.interfaccia.GestioneComandi;

/**
 * Main class of the application.
 */
public final class App {

    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public String getGreeting() {
        return "Hello World!!!";
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        if (args.length == 1 && ("--help".equals(args[0]) || "-h".equals(args[0]))) {
            try {
                GestioneComandi.chiamaComando("/help", new String[0]);
            } catch (ComandoNonEsistenteException | InputNonFormattatoException
            | PartitaNonIniziataException | ParametriNonCorrettiException e) {
                System.out.println(e.getMessage());
            }
        }
        GestioneComandi.mainLoop();
    }
}
