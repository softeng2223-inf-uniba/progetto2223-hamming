package it.uniba.app.gioco;

import it.uniba.app.interfaccia.Comando;

import java.util.Map;

import java.util.LinkedHashMap;

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

    //SEZIONE COMANDI
    private static final Map<String, Comando> COMANDI = new LinkedHashMap<String, Comando>() { };

    /**
     * Metodo che restituisce l'oggetto comando associato alla stringa (chiave) passata come parametro.
     * @param comando stringa che rappresenta il comando (chiave della LinkedHashMap)
     */
    public static Comando getComando(final String comando) {
        return COMANDI.get(comando);
    }
}
