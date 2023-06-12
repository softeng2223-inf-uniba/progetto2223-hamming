package it.uniba.app;

import it.uniba.app.exceptions.ComandiException;
import it.uniba.app.exceptions.ComandoNonEsistenteException;
import it.uniba.app.exceptions.InputNonFormattatoException;
import it.uniba.app.interfaccia.GestioneComandi;
import it.uniba.app.interfaccia.Grafica;

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
                GestioneComandi.chiamaComando("/help");
            } catch (ComandiException | ComandoNonEsistenteException e) {
                System.out.println(e.getMessage());
            }
        }
        mainLoop();
    }

    /**
     * Ciclo principale del menu.
     */
    public static void mainLoop() {
        GestioneComandi.setContinua(true);
        while (GestioneComandi.getContinua()) {
            try {
                String input = leggiInput();
                if (GestioneComandi.eComando(input)) {
                    GestioneComandi.chiamaComando(input);
                } else {
                    GestioneComandi.attacco(input);
                }
            } catch (ComandiException | ComandoNonEsistenteException e) {
                Grafica.stampaWarning(e.getMessage());
            }
        }
    }

    /**
     * Legge un comando da tastiera.
     */
    public static String leggiInput() throws InputNonFormattatoException {
        String comandoRegex = "^[A-z]-[0-9]{1,2}$";

        System.out.println(GestioneComandi.partitaIniziata() ? "\nInserisci un comando o un attacco: " : "\nInserisci un comando: ");
        System.out.print("> ");

        String input = Grafica.getString();
        if (!GestioneComandi.eComando(input) && !input.matches(comandoRegex)) {
            throw new InputNonFormattatoException(input);
        }
        input = input.toLowerCase();
        return input;
    }
}
