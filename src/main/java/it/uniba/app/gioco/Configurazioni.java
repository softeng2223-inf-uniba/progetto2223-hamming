package it.uniba.app.gioco;

/**
 * Classe che contiene le configurazioni del gioco.
 * @author Gruppo Hamming
 */
public final class Configurazioni {

    private Configurazioni() { }

    //SEZIONE GRIGLIA
    private static final int RIGHE_GRIGLIA = 10;
    private static final int COLONNE_GRIGLIA = 10;

    public static int getRigheGriglia() {
        return RIGHE_GRIGLIA;
    }

    public static int getColonneGriglia() {
        return COLONNE_GRIGLIA;
    }

}
