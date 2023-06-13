package it.uniba.app.interfaccia;

import java.util.Scanner;

import it.uniba.app.exceptions.LivelloNonEsistenteException;
import it.uniba.app.gioco.Configurazioni;

/**
 * <<Boundary>>
 * Classe che gestisce la grafica, l'interfaccia utente e la stampa a video.
 *
 * @author Gruppo Hamming
 */
public final class Grafica {
    private static Scanner scan = new Scanner(System.in, "UTF-8");

    private Grafica() {
    }

    // INPUT

    /**
     * Metodo che resttuisce la stringa inserita dall'utente.
     *
     * @return input dell'utente
     */
    public static String getString() {
        return scan.nextLine();
    }

    /**
     * Metodo che stampa il messaggio in input e chiede all'utente
     * una risposta in formato s/n, che viene restituita come booleano.
     */
    public static boolean chiediConferma(final String messaggio) {
        String input;
        while (true) {
            System.out.print(messaggio);
            input = getString();
            if ("s".equals(input) || "n".equals(input)) {
                break;
            }
            System.out.println("Inserire solo s o n");
        }
        return "s".equals(input);
    }

    // OUTPUT

    /**
     * Stampa il livello di difficoltà attuale.
     *
     * @param livello livello di difficoltà attuale
     */
    public static void mostraLivello(final String livello) {
        int tentativi;
        try {
            tentativi = Configurazioni.getTentativi(livello);
            System.out.println("Livello di difficoltà: " + livello
                    + " (" + Integer.toString(tentativi) + " tentativi falliti massimi)");
        } catch (LivelloNonEsistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Stampa l'elenco delle navi presenti nel gioco con il numero di esemplari per
     * tipologia
     * e la loro lunghezza.
     */
    public static void stampaNavi() {
        int lunghezzaTipologia = 0;
        for (String tipologiaNave : Configurazioni.getTipologieNavi()) {
            lunghezzaTipologia = Math.max(lunghezzaTipologia, tipologiaNave.length());
        }
        int lunghezzaSimboli = 0;
        for (String tipologiaNave : Configurazioni.getTipologieNavi()) {
            lunghezzaSimboli = Math.max(lunghezzaSimboli, Configurazioni.getLunghezzaNavi(tipologiaNave));
        }
        System.out.println("Navi presenti sulla griglia:\n");
        for (String tipologiaNave : Configurazioni.getTipologieNavi()) {
            StringBuilder riga = new StringBuilder(tipologiaNave + ":   ");
            for (int i = 0; i < lunghezzaTipologia - tipologiaNave.length(); i++) {
                riga.append(" ");
            }
            for (int i = 0; i < Configurazioni.getLunghezzaNavi(tipologiaNave); i++) {
                riga.append(ConfigurazioniInterfaccia.getSimboloNavi(tipologiaNave)).append(" ");
            }
            for (int i = 0; i < lunghezzaSimboli - Configurazioni.getLunghezzaNavi(tipologiaNave); i++) {
                riga.append("  ");
            }
            riga.append("    Numero esemplari: ").append(Configurazioni.getNumeroNaviPerTipologia(tipologiaNave));
            System.out.println(riga);
        }
    }

    /**
     * Stampa l'elenco dei comandi del gioco.
     */
    public static void stampaHelp() {
        System.out.print("\nComandi utilizzabili in gioco:\n\n");
        String utility = "Comandi utility:\n";
        String gioco = "Comandi di gioco:\n";
        String difficolta = "Comandi di difficoltà:\n";
        for (String c : ConfigurazioniInterfaccia.getComandi().keySet()) {
            Comando comando = ConfigurazioniInterfaccia.getComando(c);
            if (comando.getCategoria().equals("utility")) {
                utility += "  " + comando.getNome() + " - " + comando.getDescrizione() + "\n";
            } else if (comando.getCategoria().equals("gioco")) {
                gioco += "  " + comando.getNome() + " - " + comando.getDescrizione() + "\n";
            } else if (comando.getCategoria().equals("difficolta")) {
                difficolta += "  " + comando.getNome() + " - " + comando.getDescrizione() + "\n";
            }
        }
        System.out.print(utility + "\n" + gioco + "\n" + difficolta + "\n");
    }

    /**
     * Metodo che stampa un messaggio a video.
     */
    public static void stampaMessaggio(final String messaggio) {
        System.out.println(messaggio);
    }

    /**
     * Metodo che stampa un messaggio di errore a video.
     */
    public static void stampaErrore(final String messaggio) {
        System.out.println(ConfigurazioniInterfaccia.ANSI_RED
                + "ERRORE: " + messaggio + ConfigurazioniInterfaccia.ANSI_RESET);
    }

    /**
     * Metodo che stampa un messaggio di warning a video.
     */
    public static void stampaWarning(final String messaggio) {
        System.out.println(ConfigurazioniInterfaccia.ANSI_YELLOW
                + "ATTENZIONE: " + messaggio + ConfigurazioniInterfaccia.ANSI_RESET);
    }
}
