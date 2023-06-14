package it.uniba.app.interfaccia;

import it.uniba.app.gioco.Cella;
import it.uniba.app.gioco.Configurazioni;
import it.uniba.app.gioco.Griglia;

/**
 * Classe che gestisce le stampe a schermo.
 * Si occupa delle stampe che riguardano la griglia di gioco.
 *
 * @author Gruppo Hamming
 */
public final class GestioneStampe {

    private GestioneStampe() {
    }

    /**
     * Restituisce il simbolo della cella a partita in corso.
     * Le celle non colpite sono rappresentate da uno spazio vuoto.
     * Le celle colpite e vuote sono rappresentate dal carattere ~.
     * Le celle colpite e contenenti una nave affondata sono rappresentate
     * da un carattere diverso per tipo di nave.
     * Le celle colpite e contenenti una nave non affondata sono rappresentate da
     * una X.
     *
     * @param cella cella da stampare
     * @throws CloneNotSupportedException
     */
    public static String getSimboloCella(final Cella cella) {
        if (!cella.eColpita()) {
            return " ";
        } else {
            if (cella.eVuota()) {
                return "~";
            } else {
                if (cella.getNave().eAffondata()) {
                    return ConfigurazioniInterfaccia.getSimboloNavi(cella.getNave().getTipologia());
                } else {
                    return ConfigurazioniInterfaccia.getSimboloDefault();
                }
            }
        }
    }

    /**
     * Restituisce il simbolo della cella quando si svela la griglia.
     * Le celle vuote sono rappresentate da uno spazio vuoto.
     * Le celle contenenti una nave affondata sono rappresentate
     * da un carattere diverso per tipo di nave.
     *
     * @param cella cella di cui restituire il simbolo
     * @throws CloneNotSupportedException
     */
    public static String getSimboloCellaSvelata(final Cella cella) {
        if (cella.eVuota()) {
            return " ";
        } else {
            return ConfigurazioniInterfaccia.getSimboloNavi(cella.getNave().getTipologia());
        }
    }

    /**
     * Stampa la griglia della partita in corso.
     * Mostra le celle già colpite facendo differenza tra quelle contenenti una nave
     * (affondata e non) e quelle vuote.
     *
     * @param griglia griglia da stampare
     */
    public static void stampaGrigliaColpita(final Griglia griglia) {
        int righe = Configurazioni.getRigheGriglia();
        int colonne = Configurazioni.getColonneGriglia();

        final int margine = 3;

        String[] lettereColonne = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        // A B C D E F G H I J
        String stampa = "";
        StringBuilder riga = new StringBuilder(" ".repeat(margine + 2));
        for (int i = 0; i < colonne; i++) {
            riga.append(lettereColonne[i]).append("   ");
        }
        stampa += riga + "\n";

        // +---+---+---+---+---+---+---+---+---+---+
        String divisore = " ".repeat(margine) + "+---".repeat(colonne) + "+";
        stampa += divisore + "\n";

        for (int i = 0; i < righe; i++) {
            // 1 | | | | | | | | | | |
            riga = new StringBuilder(Integer.toString(i + 1));
            riga.append(" ".repeat(margine - riga.length()));
            for (int y = 0; y < colonne; y++) {
                riga.append("| ").append(getSimboloCella(griglia.getCella(i, y))).append(" ");
            }
            riga.append("|");
            stampa += riga + "\n";
            stampa += divisore + "\n";
        }

        Grafica.stampaMessaggio(stampa);
    }

    /**
     * Stampa la griglia svelata delle navi.
     *
     * @param griglia griglia della partita
     */
    public static void svelaGrigliaNavi(final Griglia griglia) {
        int righe = Configurazioni.getRigheGriglia();
        int colonne = Configurazioni.getColonneGriglia();

        final int margine = 3;
        String stampa = "";

        String[] lettereColonne = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        stampa += "Posizione delle navi:\n\n";

        // A B C D E F G H I J
        StringBuilder riga = new StringBuilder(" ".repeat(margine + 2));
        for (int i = 0; i < colonne; i++) {
            riga.append(lettereColonne[i]).append("   ");
        }
        stampa += riga + "\n";

        // +---+---+---+---+---+---+---+---+---+---+
        String divisore = " ".repeat(margine) + "+---".repeat(colonne) + "+";
        stampa += divisore + "\n";

        for (int i = 0; i < righe; i++) {
            // 1 | | | | | | | | | | |
            riga = new StringBuilder(Integer.toString(i + 1));
            riga.append(" ".repeat(margine - riga.length()));
            for (int y = 0; y < colonne; y++) {
                riga.append("| ").append(getSimboloCellaSvelata(griglia.getCella(i, y))).append(" ");
            }
            riga.append("|");
            stampa += riga + "\n";
            stampa += divisore + "\n";
        }

        Grafica.stampaMessaggio(stampa);
    }

    /**
     * Metodo che stampa il messaggio di fine partita.
     */
    public static void stampaFinePartita(final String esito, final Griglia griglia) {
        Grafica.stampaMessaggio("Abbandono della partita...\n");
        GestioneStampe.svelaGrigliaNavi(griglia);
        Grafica.stampaMessaggio("\nPartita " + esito);
    }
}
