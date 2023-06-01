package it.uniba.app.interfaccia;

import java.util.LinkedHashMap;
import java.util.Map;

import it.uniba.app.exceptions.TipologiaNonEsistenteException;

/**
 * Classe che contiene le configurazioni dell'interfaccia del gioco.
 * @author Gruppo Hamming
 */
public final class ConfigurazioniInterfaccia {

    private ConfigurazioniInterfaccia() {
    }

    //SEZIONE COMANDI
    private static final Map<String, Comando> COMANDI = new LinkedHashMap<String, Comando>() {
        {
            put("esci", new Esci());
            put("facile", new Facile());
            put("medio", new Medio());
            put("difficile", new Difficile());
            put("mostralivello", new MostraLivello());
            put("gioca", new Gioca());
            put("mostranavi", new MostraNavi());
            put("svelagriglia", new SvelaGriglia());
            put("help", new Help());
        }
    };

    /**
     * Metodo che restituisce l'oggetto comando associato alla stringa (chiave) passata come parametro.
     *
     * @param comando stringa che rappresenta il comando (chiave della LinkedHashMap)
     */
    public static Comando getComando(final String comando) {
        return COMANDI.get(comando);
    }

    /**
     * Metodo che restituisce la mappa dei comandi.
     * @return mappa dei comandi
     */
    public static Map<String, Comando> getComandi() {
        return new LinkedHashMap<>(COMANDI);
    }

    //SEZIONE NAVI
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_MAGENTA = "\u001B[35m";
    public static final String SIMBOLO_DEFAULT = "\u25A0";

    private static final LinkedHashMap<String, String> SIMBOLI_NAVI = new LinkedHashMap<String, String>();

    static {
        SIMBOLI_NAVI.put("portaerei", "P");
        SIMBOLI_NAVI.put("corazzata", "C");
        SIMBOLI_NAVI.put("incrociatore", "I");
        SIMBOLI_NAVI.put("cacciatorpediniere", "T");
    }

    /**
     * Restituisce il simbolo di una tipologia di navi.
     */
    public static String getSimboloNavi(final String tipologia) {
        if (!SIMBOLI_NAVI.containsKey(tipologia)) {
            throw new TipologiaNonEsistenteException("Tipologia di nave non valida");
        }
        return SIMBOLI_NAVI.get(tipologia);
    }

}
