package it.uniba.app.interfaccia;

import it.uniba.app.exceptions.LivelloNonEsistenteException;
import it.uniba.app.gioco.Cella;
import it.uniba.app.gioco.Configurazioni;
import it.uniba.app.gioco.Griglia;

/**
 * Classe che gestisce la grafica, l'interfaccia utente e la stampa a video.
 *
 * @author Gruppo Hamming
 */
public final class Grafica {
    private Grafica() {
    }

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
        Stampa la griglia della partita.
        Le celle non colpite sono rappresentate da uno spazio vuoto.
        Le celle colpite e vuote sono rappresentate dal carattere ~.
        Le celle colpite e contenenti una nave affondata sono rappresentate
        da un carattere diverso per tipo di nave.
        Le celle colpite e contenenti una nave non affondata sono rappresentate da una X.
        @param cella cella da stampare
    */
    public static String getSimboloCella(final Cella cella) {
        if (!cella.eColpita()) {
            return " ";
        } else {
            if (cella.eVuota()) {
                return "~";
            } else {
                if (!cella.getNave().eAffondata()) {
                    return "X";
                } else {
                    return ConfigurazioniInterfaccia.getSimboloNavi(cella.getNave().getTipologia());
                }
            }
        }
    }

    /**
        Stampa la griglia della partita in corso.
        Mostra le celle già colpite facendo differenza tra quelle contenenti una nave (affondata e non) e quelle vuote.
        @param griglia griglia da stampare
    */
    public static void stampaGrigliaColpita(final Griglia griglia) {
        System.out.println("     A   B   C   D   E   F   G   H   I   J");
        System.out.println("   +---+---+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            System.out.print((i + 1) + (Integer.toString(i + 1).length() == 2 ? " " : "  "));
            for (int y = 0; y < Configurazioni.getColonneGriglia(); y++) {
                System.out.print("| " + getSimboloCella(griglia.getCella(i, y)) + " ");
            }
            System.out.println("|\n   +---+---+---+---+---+---+---+---+---+---+");
        }
    }
}
