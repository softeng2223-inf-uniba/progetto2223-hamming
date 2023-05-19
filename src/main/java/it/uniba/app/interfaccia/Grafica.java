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
     * Restituisce il simbolo della cella a partita in corso.
     * Le celle non colpite sono rappresentate da uno spazio vuoto.
     * Le celle colpite e vuote sono rappresentate dal carattere ~.
     * Le celle colpite e contenenti una nave affondata sono rappresentate
     * da un carattere diverso per tipo di nave.
     * Le celle colpite e contenenti una nave non affondata sono rappresentate da una X.
     *
     * @param cella cella da stampare
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
     * Restituisce il simbolo della cella quando si svela la griglia.
     * Le celle vuote sono rappresentate da uno spazio vuoto.
     * Le celle contenenti una nave affondata sono rappresentate
     * da un carattere diverso per tipo di nave.
     *
     * @param cella cella di cui restituire il simbolo
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
     * Mostra le celle già colpite facendo differenza tra quelle contenenti una nave (affondata e non) e quelle vuote.
     *
     * @param griglia griglia da stampare
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


    /**
     * Stampa l'elenco delle navi presenti nel gioco con il numero di esemplari per tipologia
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
            String riga = "" + tipologiaNave + ":   ";
            for (int i = 0; i < lunghezzaTipologia - tipologiaNave.length(); i++) {
                riga += " ";
            }
            for (int i = 0; i < Configurazioni.getLunghezzaNavi(tipologiaNave); i++) {
                riga += "[X]";
            }
            for (int i = 0; i < lunghezzaSimboli - Configurazioni.getLunghezzaNavi(tipologiaNave); i++) {
                riga += "   ";
            }
            riga = riga.concat("    Numero esemplari: " + Configurazioni.getNumeroNaviPerTipologia(tipologiaNave));
            System.out.println(riga);
        }
    }

    /**
     * Stampa la griglia svelata delle navi.
     *
     * @param griglia griglia della partita
     */
    public static void svelaGrigliaNavi(final Griglia griglia) {
        System.out.println("Posizione delle navi:\n");
        System.out.println("     A   B   C   D   E   F   G   H   I   J");
        System.out.println("   +---+---+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            System.out.print((i + 1) + (Integer.toString(i + 1).length() == 2 ? " " : "  "));
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                System.out.print("| " + getSimboloCellaSvelata(griglia.getCella(i, j)) + " ");
            }
            System.out.println("|\n   +---+---+---+---+---+---+---+---+---+---+");
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
}
